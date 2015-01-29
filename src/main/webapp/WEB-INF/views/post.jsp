<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
        <link href="/css/blog.css" rel="stylesheet">
        <link href="/css/post.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="footer">
		<script>
			   app.value("postId", "${postModel.post.id}");
		</script>
    </jsp:attribute>
    <jsp:attribute name="title">${postModel.post.title} - Zlogger</jsp:attribute>
    <jsp:body>

        <div class="row blog-row">
            <div class="col-sm-8">
                <div class="full-post-space" ng-controller="postCommentsCtrl as ctrl">
                    <article class="post">
                        <h2 class="post-alone">
                            <c:if test="${postModel.post.title.equals(\"\")}">No title</c:if>
                            ${postModel.post.title}
                        </h2>
                        <p class="post-date">
                            <span class="glyphicon glyphicon-time"></span>
                            <fmt:formatDate type = "both" dateStyle="short" timeStyle="short" value="${postModel.post.creationDate}"/> by
                            <a class="post-owner" href="/blog/${postModel.post.creator.username}">${postModel.post.creator.username}</a></p>
                        <hr>
                        <p class="full-content">${postModel.post.message}</p>
                    </article>

                    <hr>

                    <c:if test="${postModel.canAddCommentary}">
                        <div class="leave-comment-block">
                            <form name="CommentaryForm" novalidate ng-submit="ctrl.submit(CommentaryForm.$valid)">
                                <div class="form-group">
                                    <label for="message">Leave a comment</label>
                                    <textarea class="form-control" name="message" minlength="3" maxlength="2000" rows="3"
                                          required ng-model="ctrl.commentary.message" autofocus></textarea>
                                    <div ng-messages="CommentaryForm.message.$error" ng-show="CommentaryForm.message.$dirty && !sent">
                                        <div ng-message="required">You can't leave this empty</div>
                                        <div ng-message="minlength">Please use between 3 and 2000 characters.</div>
                                        <div ng-message="maxlength">Please use between 3 and 2000 characters.</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="leave-comment-buttons">
                                        <button class="btn btn-lg btn-primary" type="submit" value="Submit"
                                            ng-disabled="!CommentaryForm.$valid && !sent">
                                            <div ng-show="sent">
                                                Submitting... <span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>
                                            </div>
                                            <div ng-show="!sent">
                                                Submit
                                            </div>
                                        </button>
                                    </div>
                                </div>
                                <alert type="danger" ng-show="message">{{message}}</alert>
                            </form>
                        </div>
                        <hr>
                    </c:if>
                    <h4>Commentaries: {{commentaries.length}}</h4>
                    <div class = "comment" ng-repeat="commentary in filteredComments">
                        <a class="comment-userpic-link" href="/blog/{{commentary.creator.username}}">
                            <div class="comment-userpic-block">
                                <img class="comment-userpic"
                                     src="/img/{{commentary.creator.username}}.png"
                                     onerror="this.src = '/img/default.png';"/>
                            </div>
                        </a>
                        <div class="comment-body">
                            <a href="/blog/{{commentary.creator.username}}">
                                <h4 class="comment-header">{{commentary.creator.username}}</h4>
                            </a>
                            <small>{{commentary.creationDate | date :'dd/MM/yy @ H:mm:ss'}}</small>
                            <div class="comment-content">
                                {{commentary.message}}
                            </div>
                        </div>
                    </div>
                    <pagination total-items="commentaries.length" 
                        ng-model="paginationProperties.currentPage"
                        max-size="paginationProperties.maxSize" class="pagination-sm" 
                        items-per-page="paginationProperties.numPerPage"
                        boundary-links="true">
                    </pagination>
                </div>
            </div>
        </div>

    </jsp:body>
</t:genericpage>