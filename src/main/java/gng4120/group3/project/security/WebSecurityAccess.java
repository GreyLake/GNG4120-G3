package gng4120.group3.project.security;

public class WebSecurityAccess {

    protected static final String[] FOR_EVERYONE = {
            // API requests must be authenticated (except for test)
            "/api/auth/**",
            "/api/test/**"
    };

    protected static final String[] FOR_ANONYMOUS = {
            // Account signin and isNew must be accessed by nonuser
            "/account/auth/**"
    };

    protected static final String[] FOR_AUTHORIZED_USERS = {
            // Account access must be authenticated
            "/account/0/**",
            // Forum Pages, Creation and Viewing
            "/forum/**"
    };

    protected static final String[] FOR_ADMINS = {"/admin/**",
            "/users/**",
            "/forum/*/edit",
            "/section/new"};

    protected static final String[] FOR_MODERATORS = {"/admin/**",
            "/users/**",
            "/forum/*/edit",
            "/section/new"};

    protected static final String[] ADMIN_ROLES = {
            "ROLE_ADMIN"
    };

    protected static final String[] MODERATOR_ROLES = {
            "ROLE_MODERATOR",
    };

}
