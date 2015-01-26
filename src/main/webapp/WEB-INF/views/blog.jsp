<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
        <link href="/css/blog.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:attribute name="title">${blogModel.blogName}</jsp:attribute>
    <jsp:body>

    <div class="row blog-row">
        <c:if test="${blogModel.owner.user.username.length() > 0}">
            <div class="col-sm-3">
                <div class="full-user-info">
                    <div class="nickname">
                        <span>${blogModel.owner.user.username}</span>
                    </div>
                    <section class="short-user-info">
                        <c:if test="${blogModel.owner.about != null && !blogModel.owner.about.equals(\"\")}">
                            <p class="caption-link" info="About"
                               content="${blogModel.owner.about}">
                               <img src="/img/${blogModel.owner.user.username}.png" onerror="this.src = '/img/Default.png';">
                            </p>
                        </c:if>
                        <c:if test="${blogModel.owner.about == null || blogModel.owner.about.equals(\"\")}">
                            <img src="/img/${blogModel.owner.user.username}.png" onerror="this.src = '/img/Default.png';">
                        </c:if>
                    </section>
                    <c:if test="${blogModel.owner.site != null && !blogModel.owner.site.equals(\"\")}">
                        <div class="social-networks-link">
                            <a href="${blogModel.owner.site}"><span class="icon-fontawesome-webfont"></span>
                                <span>Web-site</span></a>
                        </div>
                    </c:if>
                </div>
            </div>
            <div class="col-sm-8 col-sm-offset-1">
        </c:if>
        <c:if test="${!(blogModel.owner.user.username.length() > 0)}">
            <div class="col-sm-12">
        </c:if>
            <div class="main-space" ng-controller="blogCtrl as ctrl">
                <c:if test="${blogModel.canAddPost}">
                <div edit id="edit">
                    <button class="btn btn-block btn-primary" id="addPostButton">{{buttonCaption}}</button>
                    <div class="add-post-block" hidden>
                        <h2>{{labelCaption}}</h2>
                        <form name="PostForm" novalidate ng-submit="ctrl.submit(PostForm.$valid)">
                            <div class="form-group">
                                <label for="title">Title</label>
                                <input class="form-control"  type="text" maxlength="255" name="title" ng-model="post.title">
                                <div ng-messages="PostForm.title.$error">
                                    <div ng-message="maxlength">Please use less than 255 characters.</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="message">Post content</label>
                                <textarea ui-tinymce class="form-control" minlength="3" maxlength="2000" name="message" ng-model="post.message" rows="8" required></textarea>
                                <div ng-messages="PostForm.message.$error">
                                    <div ng-message="required">You can't leave this empty.</div>
                                    <div ng-message="minlength">Please use between 3 and 2000 characters.</div>
                                    <div ng-message="maxlength">Please use between 3 and 2000 characters.</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="add-post-buttons">
                                    <input type="submit" value="Submit" class="btn btn-lg btn-primary"/>
                                </div>
                            </div>
                            <h3>{{message}}</h3>
                        </form>
                    </div>
                    <hr>
                </div>
                </c:if>
                <c:forEach items="${blogModel.posts}" var="post">
                    <article class="post" id="${post.id}" editable>
                        <c:if test="${blogModel.canAddPost}">
                            <button class="post-edit-button"><div class="post-edit-img"></div></button>
                        </c:if>
                        <h2><a href="/post/${post.id}" class="post-title underlined underlined-default">
                            <c:if test="${post.title == null || post.title.equals(\"\")}">No title</c:if>${post.title}
                        </a></h2>
                        <p class="post-date">
                            <span class="glyphicon glyphicon-time"></span>
                            <fmt:formatDate type = "both" dateStyle="short" timeStyle="short" value="${post.creationDate}"/> by
                            <a class="underlined post-owner" href="/blog/${post.creator.username}">${post.creator.username}</a></p>
                        <hr>
                        <p class="content">
                            ${post.message}
                        </p>
                    </article>
                    <hr>
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
    </div>
    </jsp:body>
</t:genericpage>