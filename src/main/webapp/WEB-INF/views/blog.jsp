<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.27/angular.min.js"></script>
		<script src="/js/ng/app.js"></script>
		<script>
			   app.value("postsLoadUrl", "${blogModel.urlLoadPost}");
			   app.value("postsAddUrl", "${blogModel.urlAddPost}");
		</script>
		<script src="/js/ng/controllers.js"></script>
    </jsp:attribute>
	<jsp:body>

        <div class="row" ng-app="myApp">

            <div class="col-sm-8 blog-main">
				<div ng-controller="postsCtrl">
					<div class="blog-post" ng-repeat="post in posts">
						<h2 class="blog-post-title"><a href="/post/{{post.id}}">{{post.title}}</a></h2>
						<p class="blog-post-meta">{{post.creationDate | date :'dd/MM/yy @ H:mm'}} by <a href="#">{{post.creator.username}}</a></p>
						<p>{{post.message}}</p>
					</div>
				</div>

				<nav>
					<ul class="pager">
					  <li><a href="#">Previous</a></li>
					  <li><a href="#">Next</a></li>
					</ul>
				</nav>

			</div>

			<c:if test="${blogModel.canAddPost}">
			<div ng-controller="postsFormCtrl">
				<form novalidate>
					<input type="text" ng-model="post.title"/><br/>
					<textarea ng-model="post.message"></textarea>

					<button ng-click="send(post)">Submit</button>
				</form>
			</div>
			</c:if>



		</div>


    </jsp:body>
</t:genericpage>
