package zlogger.logic.services.impl;

import org.springframework.security.core.Authentication;
import zlogger.logic.models.AbstractPost;
import zlogger.logic.models.Wall;
import zlogger.logic.services.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

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
}
