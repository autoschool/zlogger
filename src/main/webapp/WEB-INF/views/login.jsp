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
                            <label>Login  <span class="icon-user-outline"></span></label>
                            <input class="form-control"  type="text" value="" name="username" ng-model="login.auth.username" required autofocus>
                            <div ng-messages="LoginForm.username.$error">
                                <div ng-message="required">You can't leave this empty.</div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Password  <span class="icon-key"></span></label>
                            <input class="form-control"  type="password" name="password" ng-model="login.auth.password" required>
                            <div ng-messages="LoginForm.password.$error">
                                <div ng-message="required">You can't leave this empty.</div>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <a class="underlined underlined-default" href="/signup">Sign up</a>
                            <button type="submit" ng-disabled="!LoginForm.$valid">
                                <div ng-show="!sent">
                                    Sign in
                                </div>
                                <div ng-show="sent">
                                    Signing in... <span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>
                                </div>
                            </button>
                        </div>
                        <alert type="danger" ng-show="message">{{message}}</alert>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>
