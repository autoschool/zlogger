package ru.yandex.autoschool.zlogger.services;

import org.javalite.activejdbc.LazyList;
import ru.yandex.autoschool.zlogger.models.Post;
import ru.yandex.autoschool.zlogger.models.Wall;

/**
 * Created by alexwyrm on 11/14/14.
 */
public class BlogService {

    public Wall getWall() {
        Wall wall = new Wall();
        LazyList<Post> posts = Post.where("wall_id is null");
        wall.setContent(posts);
        return wall;
    }

}
