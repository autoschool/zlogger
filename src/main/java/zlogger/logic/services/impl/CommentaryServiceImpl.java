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

public class CommentaryServiceImpl implements CommentaryService {

    @Autowired
    private CommentaryDao commentaryDao;

    @Override
    @Transactional
    public Long addCommentary(Commentary commentary, Post post, User user) {
        Objects.requireNonNull(commentary, "Can't add null commentary");
        Objects.requireNonNull(post, "Can't add commentary with null post");
        Objects.requireNonNull(user, "Can't add commentary with null creator");
        Objects.requireNonNull(post.getId(), "Can't add commentary with null post_id");
        Objects.requireNonNull(user.getUsername(),
                "Can't add commentary with null creator_name");
        if (!user.getEnabled()) {
            throw new IllegalStateException("Can't add commentary with disabled creator");
        }

        commentary.setPost(post);
        commentary.setCreator(user);
        commentary.setCreationDate(new Date());
        try {
            return commentaryDao.createCommentary(commentary);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Commentary is malformed or it's " +
                    "dependencies are not persistent. " +
                    "Violated constraint: " + e.getConstraintName()
            );
        }
    }


    @Override
    @Transactional
    public List<Commentary> listCommentaries() {
        return commentaryDao.getCommentaries();
    }

    @Override
    @Transactional
    public List<Commentary> listCommentariesForPost(Post post) {
        Objects.requireNonNull(post, "Can't get commentaries for null post");
        Objects.requireNonNull(post.getId(),
                "Can't get commentaries for post with null id");

        return commentaryDao.getCommentariesByPost(post);
    }

    @Override
    @Transactional
    public List<Commentary> listCommentariesForUser(User user) {
        Objects.requireNonNull(user, "Can't get commentaries for null user");
        Objects.requireNonNull(user.getUsername(),
                "Can't get commentaries for user with null username");

        return commentaryDao.getCommentariesByUser(user);
    }

    @Override
    @Transactional
    public void deleteCommentary(Long id) {
        Objects.requireNonNull(id, "Can't delete commentary with null id");

        commentaryDao.deleteCommentaryById(id);
    }

    @Override
    @Transactional
    public Commentary getCommentary(Long id) {
        Objects.requireNonNull(id, "Can't get commentary with null id");

        return commentaryDao.getCommentaryById(id);
    }

    @Override
    @Transactional
    public Long updateCommentary(Commentary commentary) {
        Objects.requireNonNull(commentary, "Can't update with null commentary");
        Objects.requireNonNull(commentary.getId(), "Can't update commentary with null id");

        Commentary oldCommentary = getCommentary(commentary.getId());

        if (commentary.getMessage() == null) {
            commentary.setMessage(oldCommentary.getMessage());
        }
        commentary.setCreationDate(oldCommentary.getCreationDate());
        commentary.setPost(oldCommentary.getPost());
        commentary.setCreator(oldCommentary.getCreator());
        return commentaryDao.updateCommentary(commentary);
    }
}
