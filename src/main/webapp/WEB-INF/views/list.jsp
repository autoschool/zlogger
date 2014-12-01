<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">

    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
	<jsp:body>

        <div class="row">

            <div class="col-sm-8 blog-main">

				<c:forEach var="post" items="${indexModel.posts}" varStatus="i">
					<div class="blog-post">
						<h2 class="blog-post-title"><a href="/post/${post.id}">${post.title}</a></h2>
						<p class="blog-post-meta">${post.creationDate} by <a href="#">${post.creator.username}</a></p>
						<p>${post.message}</p>
					</div> <!-- /blog-post -->
				</c:forEach>

				<nav>
					<ul class="pager">
					  <li><a href="#">Previous</a></li>
					  <li><a href="#">Next</a></li>
					</ul>
				</nav>

			</div>

			<c:if test="${indexModel.canAddPost}">
				<form id="form" method="post" action="${indexModel.urlAddPost}" >

					<input cssClass="input-block-level"  placeholder="" autocomplete="off" name="title"/>
					<textarea name="message"></textarea>

					<button type="submit" class="btn">Submit</button>

				</form>
			</c:if>



		</div>


    </jsp:body>
</t:genericpage>
