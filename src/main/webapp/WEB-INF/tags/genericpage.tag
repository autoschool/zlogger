<%@tag description="Overall Page template" pageEncoding="UTF-8" language="java" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@tag import = "java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("version");
    String version=resource.getString("version");
    String timestamp=resource.getString("build.date");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">

        <title><jsp:invoke fragment="title"/></title>

        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/blog.css" rel="stylesheet">
        <link href="/css/site.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/ng-dialog/0.3.7/css/ngDialog.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/ng-dialog/0.3.7/css/ngDialog-theme-default.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/ng-dialog/0.3.7/css/ngDialog-theme-plain.min.css" rel="stylesheet">

		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
      		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    	<![endif]-->

		<link rel="icon" href="/img/favicon.png">

		<jsp:invoke fragment="header"/>
    </head>
    <body>

        <div class="blog-masthead">
            <div class="container">
                <nav class="blog-nav">
                    <a class="blog-nav-item" href="/">Home</a>
                    <a class="blog-nav-item" href="#">About</a>
                    <a class="blog-nav-item navbar-right" href="/login">Login</a>
                </nav>
            </div>
        </div>

		<div class="container">

			<div class="blog-header">
                <div class="logo inline-div"></div>
                <p class="lead blog-description">A certain blog platform's evil twin</p>
            </div>

			<jsp:doBody/>

	    </div>

        <footer class="blog-footer">
            <p><%=version %> Build date: <%=timestamp %></p>
            <p>
                <a href="#">Back to top</a>
            </p>
        </footer>

		<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.10/angular.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.10/angular-messages.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/ng-dialog/0.3.7/js/ngDialog.min.js"></script>
		<script src="/js/ng/app.js"></script>
        <script src="/js/ng/controllers.js"></script>
        <script src="/js/ng/directives.js"></script>
        <jsp:invoke fragment="footer"/>

	</body>
</html>