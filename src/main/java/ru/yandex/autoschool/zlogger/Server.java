package ru.yandex.autoschool.zlogger;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

/**
 * Created by alexwyrm on 11/14/14.
 */
public class Server extends ResourceConfig {

    public Server() {
        register(FreemarkerMvcFeature.class);
        packages(Server.class.getPackage().getName());
        //Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/zlogger_db", "zlogger", "zlogger");
    }
}
