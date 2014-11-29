<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular.js"></script>
		<script src="/js/ng/app.js"></script>
		<script>
               app.value("loadUrl", "${postModel.urlLoadCommentary}");
		</script>
		<script src="/js/ng/controllers.js"></script>
    </jsp:attribute>
    <jsp:attribute name="footer">

    </jsp:attribute>
    <jsp:body>



        		<div class="row-fluid" ng-app="myApp">
        			<div class="span12">

        				<div id="" class="">
        					<h3>
								<a href="/post/${sentence.id}">
									${postModel.post.title}
								</a>
							</h3>
							  <p>${postModel.post.message}</p>
        				</div>

					<div ng-controller="postCommentsCtrl" >
						<ul>
							<li ng-repeat="commentary in commentaries">
								<small>{{commentary.creator.username}}</small>
								{{commentary.message}}
							</li>
						<ul>
					</div>


					<c:if test="${postModel.canAddCommnetary}">
        				<form id="form" method="post" action="${postModel.urlAddCommentary}" >

        					<spring:message code="message.typeMessage" var="typeMessage" />
        				  	<input cssClass="input-block-level"  placeholder="" autocomplete="off" name="title" />
							<textarea name="message">
							</textarea>

        				  	<button type="submit" class="btn">Submit</button>

        				</form>
					</c:if>
        			</div>
        		</div>


    </jsp:body>
</t:genericpage>
