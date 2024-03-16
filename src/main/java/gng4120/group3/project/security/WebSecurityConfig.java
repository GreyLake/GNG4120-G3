package gng4120.group3.project.security;

import gng4120.group3.project.security.jwt.AuthEntryPointJwt;
import gng4120.group3.project.security.jwt.AuthTokenFilter;
import gng4120.group3.project.security.services.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.AbstractRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException ) -> {

                            // Redirect users to the login page for unauthorized requests
                            OrRequestMatcher requestMatchers = new OrRequestMatcher(
                                    Arrays.stream(WebSecurityAccess.FOR_AUTHORIZED_USERS)
                                    .map(AntPathRequestMatcher::new)
                                    .toArray(RequestMatcher[]::new));

                            if(requestMatchers.matches(request)){
                                response.setStatus(HttpServletResponse.SC_FOUND); // 302 status code for redirection
                                response.sendRedirect("/account/auth");
                            }
                            else{
                                // Delegate to the default unauthorizedHandler
                                unauthorizedHandler.commence(request, response, authException);
                            }
                        }))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WebSecurityAccess.FOR_ADMINS).hasAnyAuthority(WebSecurityAccess.ADMIN_ROLES)
                        .requestMatchers(WebSecurityAccess.FOR_MODERATORS).hasAnyAuthority(WebSecurityAccess.MODERATOR_ROLES)
                        .requestMatchers(WebSecurityAccess.FOR_AUTHORIZED_USERS).authenticated()
                        .requestMatchers(WebSecurityAccess.FOR_ANONYMOUS).anonymous()
                        .requestMatchers(WebSecurityAccess.FOR_EVERYONE).permitAll()
                        // Main pages, and viewing can be done by anyone
                        .anyRequest().permitAll()
                );

        http.authenticationProvider(authenticationProvider());

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .sessionFixation().migrateSession()
        );

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.formLogin(form -> form
                        .loginPage("/account/auth")
                        .usernameParameter("email")
                        .failureUrl("/account/auth?error"));

        http.logout(logout -> logout
                        .logoutSuccessUrl("/?logoutSuccess=true")
                        .deleteCookies("JSESSIONID"));

        return http.build();
    }

}