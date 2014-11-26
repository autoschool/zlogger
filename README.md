Zlogger
========

Blog project

Spring MVC, AngularJS.

<hr/>

**Configure MySQL**

    `server=localhost:3306`

    `db_name=zlogger_db`

    `user=zlogger`

    `password=zlogger`

Execute `\src\main\resources\database\zlogger_db.sql`



<hr/>

**Start web-server**

Use
    `mvn jetty:run`

<hr/>

**Run all tests**

    `mvn integration-test`

Build test reports (allure)

    `mvn site`

results in `\target\site\allure-maven-plugin.html`