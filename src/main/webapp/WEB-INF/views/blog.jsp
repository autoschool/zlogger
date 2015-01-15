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
		<script src="/js/ng/controllers.js"></script>
    </jsp:attribute>
	<jsp:body>

        <div class="row" >

            <div class="col-sm-8 blog-main">
				<div>

					<c:forEach items="${blogModel.posts}" var="post">
						<div class="blog-post" >
							<h2 class="blog-post-title"><a href="/post/{{post.id}}">${post.title}</a></h2>
							<p class="blog-post-meta">${post.creationDate} by <a href="#">${post.creator.username}</a></p>
							<p>${post.message}</p>
						</div>
					</c:forEach>


				</div>

				<nav>
					<ul class="pager">
						<c:if test="${blogModel.hasPreviousPage}">
							<li><a href="${blogModel.linkPreviousPage}">Previous</a></li>
						</c:if>
						<c:if test="${blogModel.hasNextPage}">
							<li><a href="${blogModel.linkNextPage}">Next</a></li>
						</c:if>

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
