<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.10/angular-messages.min.js"></script>
    </jsp:attribute>
    <jsp:attribute name="title">Registration - Zlogger</jsp:attribute>
    <jsp:body>

	<div class="container" ng-app="zlogger">
        <div ng-controller="registrationController as reg">
			<form name="SignUpForm" novalidate ng-submit="reg.submit(SignUpForm.$valid)">
                <div class="form-group">
                    <label>Choose your username</label>
                    <input type="text" class="form-control" name="userName" ng-model="reg.user.userName" ng-minlength="3" ng-maxlength="50" required>
                    <div ng-messages="SignUpForm.userName.$error">
                        <div ng-message="required">You can't leave this empty.</div>
                        <div ng-message="minlength">Please use between 3 and 50 characters.</div>
                        <div ng-message="maxlength">Please use between 3 and 50 characters.</div>
                    </div>
                </div>
                <div class="form-group">
                    <label>Create a password</label>
                    <input type="password" class="form-control" name="password" ng-model="reg.user.password" ng-minlength="8" ng-maxlength="50" required>
                    <div ng-messages="SignUpForm.password.$error">
                        <div ng-message="required">You can't leave this empty.</div>
                        <div ng-message="minlength">Please use between 8 and 50 characters.</div>
                        <div ng-message="maxlength">Please use between 8 and 50 characters.</div>
                    </div>
                </div>
                <div class="form-group">
                    <label>Confirm your password</label>
                    <input type="password" class="form-control" name="confirmPassword" ng-model="reg.user.confirmPassword" ng-minlength="8" ng-maxlength="50" required compare-to="reg.user.password">
                    <div ng-messages="SignUpForm.confirmPassword.$error">
                        <div ng-message="required">You can't leave this empty.</div>
                        <div ng-message="compareTo">These passwords don't match. Try again?</div>
                    </div>
                </div>
                <div class="form-group">
                    <label>Your email address (optional)</label>
                    <input type="email" class="form-control" name="email" ng-model="reg.user.email">
                    <div ng-messages="SignUpForm.email.$error">
                        <div ng-message="email">Invalid email address format</div>
                    </div>
                </div>
                <h3>{{reg.message}}</h3>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </form>
        </div>
	</div>


    </jsp:body>
</t:genericpage>