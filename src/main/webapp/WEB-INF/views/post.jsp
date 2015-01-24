<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header"></jsp:attribute>
    <jsp:attribute name="footer">
		<script>
			   app.value("commentsLoadUrl", "${postModel.urlLoadCommentary}");
		</script>
    </jsp:attribute>
    <jsp:attribute name="title">${postModel.post.title} - Zlogger</jsp:attribute>
    <jsp:body>



		<div class="row">
			
			<div class="col-sm-8 blog-main">

				<div class="blog-post">
					<h2 class="blog-post-title">${postModel.post.title}</h2>
					<p class="blog-post-meta">
						<fmt:formatDate type = "both" dateStyle="short" timeStyle="short" value="${post.creationDate}"/> by <a href="/blog/${post.creator.username}">${postModel.post.creator.username}</a>
					</p>
					<p>${postModel.post.message}</p>
				</div>

			<hr>

			<div ng-controller="postCommentsCtrl" >
				<div class = "blog-commentary" ng-repeat="commentary in commentaries">
					<p class="blog-post-meta">{{commentary.creationDate | date :'dd/MM/yy @ H:mm:ss'}} by <a href="/blog/{{commentary.creator.username}}">{{commentary.creator.username}}</a></p>
					<p>{{commentary.message}}</p>
				</div>
			</div>


			<c:if test="${postModel.canAddCommentary}">
				<form id="form" method="post" action="${postModel.urlAddCommentary}" >
					<textarea name="message"></textarea>

				  	<button type="submit" class="btn">Submit</button>
				</form>
			</c:if>
			</div>
		</div>


    </jsp:body>
</t:genericpage>
