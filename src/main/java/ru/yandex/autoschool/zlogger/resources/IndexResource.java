package ru.yandex.autoschool.zlogger.resources;

import org.glassfish.jersey.server.mvc.Template;
import org.javalite.activejdbc.Base;
import ru.yandex.autoschool.zlogger.models.Post;
import ru.yandex.autoschool.zlogger.models.Wall;
import ru.yandex.autoschool.zlogger.services.BlogService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.IOException;


/**
 * Created by alexwyrm on 11/14/14.
 */
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class IndexResource {

    private static Wall getWall() {
        BlogService service = new BlogService();
        return service.getWall();
    }

    @GET
    @Path("/")
    @Template(name = "/index.ftl")
    public Wall getIndex() {
        return getWall();
    }

    @POST
    @Path("/new_message")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newPost(@FormParam("content") String content,
                        @Context HttpServletResponse response) throws IOException {
        Post post = new Post();
        post.setContent(content);
        post.saveIt();
        response.sendRedirect("/");
    }

    @PostConstruct
    public void openConnection() {
        if (!Base.hasConnection()) {
            Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/zlogger_db", "zlogger", "zlogger");
        }
    }

    @PreDestroy
    public void closeConnection() {
        Base.close();
    }

}
