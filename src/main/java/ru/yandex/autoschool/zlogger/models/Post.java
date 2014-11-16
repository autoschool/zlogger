package ru.yandex.autoschool.zlogger.models;

import org.javalite.activejdbc.Model;

import java.sql.Timestamp;

import static ru.yandex.autoschool.zlogger.utils.TimestampFormatter.humanize;

/**
 * Created by alexwyrm on 11/14/14.
 */
public class Post extends Model {

    public String getContent() {
        return this.getString("post");
    }

    public void setContent(String content) {
        this.set("post", content);
    }

    public Timestamp getCreationTime() {
        return this.getTimestamp("creation_time");
    }

    public void setCreationTime(Timestamp time) {
        this.set("creation_time", time);
    }

    public String getHumanizedCreationTime() {
        return humanize(this.getTimestamp("creation_time"));
    }

    public String getUserName() {
        return null;
    }

    public String getTitle() {
        return null;
    }

}
