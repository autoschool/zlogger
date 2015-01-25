<%@tag description="Overall Page template" pageEncoding="UTF-8" language="java" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="title" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@tag import = "java.util.ResourceBundle" %>
<% ResourceBundle resource = ResourceBundle.getBundle("version");
    String version=resource.getString("version");
    String timestamp=resource.getString("build.date");
%>
<!DOCTYPE html>
<html ng-app="zlogger" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">

        <title><jsp:invoke fragment="title"/></title>


        <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open Sans">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
        <link href="/css/glyphter.css" rel="stylesheet">
        <link href="/css/common.css" rel="stylesheet">
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
<header>
    <nav class="navbar">
        <div class="container-fluid">
            <ul class="navbar-nav nav">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <li><a class="navbar-brand" href="/common">Zlogger</a></li>
                <li class="logo-item">
                    <div class="logo-block">
                        <img class="logo" src="/img/newlogobluelight.png">
                    </div>
                </li>
            </ul>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li><a data-hover="Home" href="/"></a></li>
                    </sec:authorize>
                    <li><a data-hover="About" href="#"></a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li><a  data-hover="Settings" href="/user/profile"></a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_ANONYMOUS')">
                        <li><a data-hover="Login" href="/login"></a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li><a data-hover="Logout" href="<c:url value="/j_spring_security_logout" />"></a></li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
</header>
<main>
    <div class="motto">
         A certain blog platform's evil twin
    </div>
	<section class="container">
			<jsp:doBody/>
	</section>
</main>
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
<script src="/js/ng/app.js"></script>
<script src="/js/ng/controllers.js"></script>
<script src="/js/ng/services.js"></script>
<script src="/js/ng/directives.js"></script>
<script src="https://tinymce.cachefly.net/4.1/tinymce.min.js"></script>
<script src="/js/tinymce.js"></script>
<jsp:invoke fragment="footer"/>

<!--localStorageDemo-->
<script>
    if (window.localStorage) {
        var elements = document.querySelectorAll('[name]');

        for (var i = 0, length = elements.length; i < length; i++) {
            (function (element) {
                var name = element.getAttribute('name');

                element.value = localStorage.getItem(name) || '';

                element.onkeyup = function () {
                    localStorage.setItem(name, element.value);
                };
            })(elements[i]);
        }
    }
</script>

	</body>
</html>