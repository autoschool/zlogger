package zlogger.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zlogger.logic.dal.PostDao;
import zlogger.logic.models.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;


    @Override
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response addPost(Post post) {
        postDao.createPost(post);

        return Response.status(201).entity("A new post has been created").build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.TEXT_HTML})
    @Transactional
    public Response addPostFromForm(@FormParam("title") String title,
                                    @FormParam("message") String message) {
        Post post = new Post(title, message);
        postDao.createPost(post);

        return Response.status(201).entity("A new post has been created").build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Override
    public List<Post> listPosts() {
        List<Post> list = postDao.getPosts();

        return list;
    }

    @Override
    public void removePost(Integer id) {

    }
}
