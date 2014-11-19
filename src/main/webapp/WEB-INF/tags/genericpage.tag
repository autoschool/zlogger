<%@tag description="Overall Page template" pageEncoding="UTF-8" language="java" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">

        <title> title </title>

        <link href="/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
			body {
				padding-top: 60px;
		        padding-bottom: 40px;
			}
			.sidebar-nav {
				padding: 9px 0;
			}
		</style>
        <!-- See http://twitter.github.com/bootstrap/scaffolding.html#responsive -->
        <link href="/css/bootstrap-responsive.min.css" rel="stylesheet" />

		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
      		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    	<![endif]-->

    	<!-- Fav and touch icons
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/ico/apple-touch-icon-144-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="/ico/apple-touch-icon-114-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="/ico/apple-touch-icon-72-precomposed.png">
		<link rel="apple-touch-icon-precomposed" href="/ico/apple-touch-icon-57-precomposed.png">
		<link rel="shortcut icon" href="/ico/favicon.png">
		-->

		<jsp:invoke fragment="header"/>
    </head>
    <body>

        <div class="navbar navbar-inverse navbar-fixed-top">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </a>
                        <a class="brand" href="#">Mvc base</a>
                        <div class="nav-collapse collapse">
                            <p class="navbar-text pull-right">
                                Logged in as <a href="#" class="navbar-link">Username</a>
                            </p>
                            <ul class="nav">
                                <li class="active"><a href="#">Home</a></li>
                                <li><a href="#about">About</a></li>
                                <li><a href="#contact">Contact</a></li>
                            </ul>
                        </div> <!--/.nav-collapse -->
                    </div>
                </div>
            </div>

		<div id="message" class="alert alert-info">
			<spring:message code="message.lang.instructions"/>
		</div>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="span3">
                   <div class="well sidebar-nav">
                           <ul class="nav nav-list">
                               <li class="nav-header"><spring:message code="layout.lang" /> </li>
                               <li><a href="?lang=fr"><spring:message code="layout.lang.french" /></a></li>
                               <li><a href="?lang=en"><spring:message code="layout.lang.english" /></a></li>
                           </ul>
                       </div>
				</div> <!--/span-->

				<div class="span9">
					<!--Body content-->
					<jsp:doBody/>
				</div>

			</div>

			<hr>
			<footer>
			  <p></p>
			</footer>

		</div><!--/.container-fluid-->

		<script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>
		<script src="/js/bootstrap.min.js" type="text/javascript"></script>

		<div id="pagefooter">
              <jsp:invoke fragment="footer"/>
        </div>
	</body>
</html>