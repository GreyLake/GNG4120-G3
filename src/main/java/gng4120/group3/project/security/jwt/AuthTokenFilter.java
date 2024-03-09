package gng4120.group3.project.security.jwt;

import gng4120.group3.project.database.repository.account.UserRepository;
import gng4120.group3.project.models.user.User;
import gng4120.group3.project.models.user.UserRef;
import gng4120.group3.project.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.auth.login.AccountNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@Order(1) // Set the desired order value
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        // Check if user is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            logger.debug(request.getRequestURL().toString());
            if(authentication != null)
                logger.debug(authentication.toString());
            checkJwt(request, response, session);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null)
                logger.debug(auth.toString());
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        AntPathMatcher pathMatcher = new AntPathMatcher();
        List<String> excludeUrlPatterns = List.of(
                "/js/**",
                "/svg/**",
                "/css/**",
                "/api/auth/invalidate"
        );

        return excludeUrlPatterns
                .stream()
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

    private void checkJwt(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        try {
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);

                Optional<User> user = Optional.empty();

                if(userRepository.existsByEmail(username)){
                    user = userRepository.findByEmail(username);
                }
                else if(userRepository.existsByUsername(username)){
                    user = userRepository.findByUsername(username);
                }
  
                if(user.isPresent()) {
                    UserDetails userDetails = userDetailsService.loadUserByObject(user.get());
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
                            userDetails.getAuthorities());

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                    // Set currentUser to null if not authenticated
                    session.setAttribute("currentUser", new UserRef(user.get()));
                    return;
                }
                else{
                    throw new AccountNotFoundException();
                }
            }
        } catch (Exception e) {
            logger.warn("Cannot set user authentication: " + e.getCause());

            // Invalidate the user. (rid of cookies)
            try { response.sendRedirect("/api/auth/invalidate"); } catch (Exception ignored) {}
        }
        session.setAttribute("currentUser", null); // Set currentUser to null if not authenticated
    }

    private String parseJwt(HttpServletRequest request) {
        return jwtUtils.getJwtFromCookies(request);
    }
}
