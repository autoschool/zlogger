package zlogger.logic.services.impl;

import org.springframework.security.core.Authentication;
import zlogger.logic.models.AbstractPost;
import zlogger.logic.models.Wall;
import zlogger.logic.services.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final int MINIMAL_PASSWORD_LENGTH = 8;
    private static final int MINIMAL_USERNAME_LENGTH = 3;
    private static final int MAXIMAL_USERNAME_LENGTH = 50;

    @Override
    public boolean isOwner(Authentication authentication, Wall wall) {
        return !(authentication == null || wall == null)
                && wall.getOwner().getUsername().equals(authentication.getName());
    }

    @Override
    public boolean isCreator(Authentication authentication, AbstractPost post) {
        return !(authentication == null || post == null)
                && post.getCreator().getUsername().equals(authentication.getName());
    }

    @Override
    public int getMinPasswordLength() {
        return MINIMAL_PASSWORD_LENGTH;
    }

    @Override
    public int getMinUsernameLength() {
        return MINIMAL_USERNAME_LENGTH;
    }

    @Override
    public int getMaxUsernameLength() {
        return MAXIMAL_USERNAME_LENGTH;
    }

    @Override
    public boolean isValidIdentity(String username, String password) {
        return !(username == null || username.isEmpty()
                || password == null || password.isEmpty()
                || password.length() < MINIMAL_PASSWORD_LENGTH
                || username.length() < MINIMAL_USERNAME_LENGTH
                || username.length() > MAXIMAL_USERNAME_LENGTH);
    }
}
