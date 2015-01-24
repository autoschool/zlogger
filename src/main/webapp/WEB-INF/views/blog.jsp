<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:attribute name="title">${blogModel.blogName}</jsp:attribute>
	<jsp:body>

        <div class="row" ng-app="zlogger">

            <div class="col-sm-8 blog-main">
				<div>

					<c:forEach items="${blogModel.posts}" var="post">
						<div class="blog-post" >
							<h2 class="blog-post-title"><a href="/post/${post.id}">${post.title}</a></h2>
							<p class="blog-post-meta">
								<fmt:formatDate type = "both" dateStyle="short" timeStyle="short" value="${post.creationDate}"/> by <a href="/blog/${post.creator.username}">${post.creator.username}</a>
							</p>
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
				<form id="form" method="post" action="/addpost" >
					<input type="text" name="title" placeholder="Title"></text>
					<textarea name="message" placeholder="Message"></textarea>

					<button type="submit" class="btn">Submit</button>
				</form>
			</c:if>

		</div>


    </jsp:body>
</t:genericpage>
