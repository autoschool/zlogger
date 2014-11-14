package ru.yandex.autoschool.zlogger.models;

import java.util.List;

/**
 * Created by alexwyrm on 11/14/14.
 */
public class Wall {

    private List<Post> content;

    public List<Post> getContent() {
        return content;
    }

    public void setContent(List<Post> content) {
        this.content = content;
    }
}
