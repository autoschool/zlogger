package zlogger.logic.services.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dao.CommentaryDao;
import zlogger.logic.models.Commentary;
import zlogger.logic.models.Post;
import zlogger.logic.models.User;
import zlogger.logic.services.CommentaryService;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentaryServiceImpl implements CommentaryService {

    @Autowired
    private CommentaryDao commentaryDao;

    private static final Logger LOGGER = Logger.getGlobal();

    @Override
    @Transactional
    public List<Commentary> list() {
        return commentaryDao.list();
    }

    @Override
    @Transactional
    public List<Commentary> listForPost(Post post) {
        Objects.requireNonNull(post, "Can't get commentaries for null post");
        Objects.requireNonNull(post.getId(),
                "Can't get commentaries for post with null id");

        return commentaryDao.listByPost(post);
    }

    @Override
    @Transactional
    public List<Commentary> listForUser(User user) {
        Objects.requireNonNull(user, "Can't get commentaries for null user");
        Objects.requireNonNull(user.getUsername(),
                "Can't get commentaries for user with null username");

        return commentaryDao.listForUser(user);
    }

    @Override
    @Transactional
    public Long add(Commentary commentary, Post post, User user) {
        commentary.setPost(post);
        commentary.setCreator(user);
        return add(commentary);
    }

    @Override
    @Transactional
    public Long add(Commentary entity) {
        Objects.requireNonNull(entity, "Can't add null commentary");
        User user = entity.getCreator();
        Post post = entity.getPost();
        Objects.requireNonNull(post, "Can't add commentary with null post");
        Objects.requireNonNull(user, "Can't add commentary with null creator");
        Objects.requireNonNull(post.getId(), "Can't add commentary with null post_id");
        Objects.requireNonNull(user.getUsername(),
                "Can't add commentary with null creator_name");
        if (!user.getEnabled()) {
            throw new IllegalStateException("Can't add commentary with disabled creator");
        }

        entity.setCreationDate(new Date());
        try {
            return commentaryDao.create(entity);
        } catch (ConstraintViolationException e) {
            LOGGER.log(Level.WARNING, e.toString());
            throw new IllegalArgumentException("Commentary is malformed or it's " +
                    "dependencies are not persistent. " +
                    "Violated constraint: " + e.getConstraintName());

        }
    }

    @Override
    @Transactional
    public Commentary get(Long id) {
        Objects.requireNonNull(id, "Can't get commentary with null id");

        return commentaryDao.get(id);
    }

    @Override
    @Transactional
    public Long update(Commentary commentary) {
        Objects.requireNonNull(commentary, "Can't update with null commentary");
        Objects.requireNonNull(commentary.getId(), "Can't update commentary with null id");

        Commentary oldCommentary = get(commentary.getId());

        if (commentary.getMessage() == null) {
            commentary.setMessage(oldCommentary.getMessage());
        }
        commentary.setCreationDate(oldCommentary.getCreationDate());
        commentary.setPost(oldCommentary.getPost());
        commentary.setCreator(oldCommentary.getCreator());
        return commentaryDao.update(commentary);
    }

    @Override
    @Transactional
    public void delete(Commentary commentary) {
        Objects.requireNonNull(commentary, "Can't delete null commentary");

        commentaryDao.delete(commentary);
    }
}
