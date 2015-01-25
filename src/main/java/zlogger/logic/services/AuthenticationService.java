package zlogger.logic.services;

import org.springframework.security.core.Authentication;
import zlogger.logic.models.AbstractPost;
import zlogger.logic.models.Wall;

public interface AuthenticationService {

    public boolean isOwner(Authentication authentication, Wall wall);

    public boolean isCreator(Authentication authentication, AbstractPost post);

}
