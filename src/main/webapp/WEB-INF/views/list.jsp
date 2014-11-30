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


        <div class="hero-unit">
					<div class="inline-div">
        				<h1>Home Zlogger  </h1>
					</div>
					<div class="logo inline-div"></div>
        			<p>
        				<spring:message code="message.welcome"/>
        			</p>

        		</div>

        		<div class="row-fluid">
        			<div class="span8">

        				<div id="message" class="alert alert-info">
        					<spring:message code="message.home.instructions"/>
        				</div>

						<div id="message" class="alert alert-success">
							<spring:message code="message.youWrote" arguments="${indexModel.welcome}" htmlEscape="true" />
						</div>
        				<div id="postsWall" class="posts">
							<c:forEach var="sentence" items="${indexModel.posts}" varStatus="i">

								<h3>
									<a href="/post/${sentence.id}">
								  		${sentence.title}
									</a>
								</h3>
								  <p>${sentence.message}</p>

							  </c:forEach>
        				</div>

						<c:if test="${indexModel.canAddPost}">
						<form id="form" method="post" action="${indexModel.urlAddPost}" >

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
