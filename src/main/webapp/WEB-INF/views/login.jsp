<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
        <link href="/css/login.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="footer"></jsp:attribute>
    <jsp:attribute name="title">Login - Zlogger</jsp:attribute>
    <jsp:body>
        <div class="row blog-row">
            <div class="col-sm-12">
                <div class="main-space" ng-controller="loginCtrl as login">
                    <form name="LoginForm" class="form-signin" novalidate ng-submit="login.submit(LoginForm.$valid)">
                        <h2 class="form-signin-heading">Please sign in</h2>
                        <div class="form-group">
                            <label for="username">Login  <span class="icon-user-outline"></span></label>
                            <input class="form-control"  type="text" value="" id="username" ng-model="login.auth.username" required autofocus>
                            <div ng-messages="LoginForm.username.$error">
                                <div ng-message="required">You can't leave this empty.</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="password">Password  <span class="icon-key"></span></label>
                            <input class="form-control"  type="password" id="password" ng-model="login.auth.password" required>
                            <div ng-messages="LoginForm.password.$error">
                                <div ng-message="required">You can't leave this empty.</div>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <a href="/signup">Sign up</a>
                            <input class="btn btn-lg btn-primary" type="submit" value="Sign in"/>
                        </div>
                        <h3 style="color : red">{{login.message}}</h3>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
