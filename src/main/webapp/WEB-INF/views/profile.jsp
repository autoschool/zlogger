<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="header">
        <link href="/css/profile.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="footer">
        <script>
            app.value("email", "${userModel.details.email}");
            app.value("about", "${userModel.details.about}");
            app.value("site", "${userModel.details.site}");
        </script>
    </jsp:attribute>
    <jsp:attribute name="title">${userModel.username}'s Profile - Zlogger</jsp:attribute>
    <jsp:body>

        <div class="row blog-row">
            <div class="col-sm-12">
                <div class="main-space">
                    <h2 class="uppercase">Edit Personal Information</h2>

                    <div class="thematic-block">
                        <div class="userpic-block">
                            <img src="/img/${userModel.username}.png" onerror="this.src = '/img/default.png';"/>
                        </div>
                    </div>
                    <div class="thematic-block ng-scope" ng-controller="avatarCtrl as ctrl">
                        <div class="form-group">
                            <label>Userpic <span class="glyphicon glyphicon-camera"/></label>
                            <input class="btn btn-default btn-file" type="file" name="userpic" file-model="myFile">

                        <div class="divide">
                            <button type="submit" ng-click="uploadFile()">
                                <div ng-show="sent">
                                    Uploading... <span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>
                                </div>
                                <div ng-show="!sent">
                                    Upload <span class="glyphicon glyphicon-upload"/></span>
                                </div>
                            </button>
                            <alert type="danger" ng-show="message">{{message}}</alert>
                        </div>
                        </div>
                    </div>
                    <div class="thematic-block" ng-controller="detailsCtrl as ctrl">
                        <form name="DetailsForm" novalidate ng-submit="ctrl.submit(DetailsForm.$valid)">
                            <div class="form-group">
                                <label>Email <span class="icon-at-sign"></span></label>
                                <input class="form-control" type="email" name="email" ng-model="ctrl.details.email"
                                    placeholder="zlo@evil.com">

                                <div ng-messages="DetailsForm.email.$error">
                                    <div ng-message="email">Invalid email address format</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>About <span class="glyphicon glyphicon-list-alt"/></label>
                            <textarea class="form-control" placeholder="e.g. Conquering the world"
                                      maxlength="500" name="about" ng-model="ctrl.details.about"></textarea>

                                <div ng-messages="DetailsForm.about.$error">
                                    <div ng-message="maxlength">Please use less than 500 characters.</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Web-Site <span class="glyphicon glyphicon-home"/></label>
                                <input class="form-control" type="url" placeholder="e.g. http://evil.com"
                                       maxlength="100" name="site" ng-model="ctrl.details.site">

                                <div ng-messages="DetailsForm.site.$error">
                                    <div ng-message="maxlength">Please use less than 100 characters.</div>
                                    <div ng-message="url">Please enter a valid url.</div>
                                </div>
                            </div>
                            <div class="form-group">
                                <button class="reset-button" ng-click="reset()">Reset <span class="glyphicon glyphicon-repeat"/>
                                </button>
                                <button type="submit">
                                    <div ng-show="sent">
                                        Submitting... <span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>
                                    </div>
                                    <div ng-show="!sent">
                                        Submit <span class="glyphicon glyphicon-ok"/>
                                    </div>
                                </button>
                            </div>
                            <alert type="danger" ng-show="message">{{message}}</alert>
                        </form>
                    </div>
                    <div class="thematic-block" ng-controller="changePasswordCtrl as ctrl">
                        <div>
                            <form name="PasswordForm" ng-submit="ctrl.submit(PasswordForm.$valid)" novalidate>
                                <div class="form-group">
                                    <label>Password <span class="icon-key"></span></label>
                                    <input class="form-control" type="password"
                                           placeholder="Current password" name="current"
                                           ng-model="ctrl.password.currentPassword" required/>
                                    <input class="form-control" type="password"
                                           placeholder="New password" minlength="8" maxlength="50"
                                           name="new" ng-model="ctrl.password.newPassword" required/>

                                    <div ng-messages="PasswordForm.new.$error">
                                        <div ng-message="required">You can't leave this empty.</div>
                                        <div ng-message="minlength">Please use between 8 and 50 characters.</div>
                                        <div ng-message="maxlength">Please use between 8 and 50 characters.</div>
                                    </div>
                                    <input class="form-control" type="password"
                                           placeholder="Confirm new password" minlength="8" maxlength="50"
                                           name="confirm" ng-model="ctrl.password.confirmPassword"
                                           required compare-to="ctrl.password.newPassword"/>

                                    <div ng-messages="PasswordForm.confirm.$error">
                                        <div ng-message="required">You can't leave this empty.</div>
                                        <div ng-message="compareTo">These passwords don't match. Try again?</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <button type="submit">
                                        <div ng-show="sent">
                                            Submitting... <span class="glyphicon glyphicon-refresh glyphicon-refresh-animate"></span>
                                        </div>
                                        <div ng-show="!sent">
                                            Submit <span class="glyphicon glyphicon-ok"/>
                                        </div>
                                    </button>
                                </div>
                                <alert type="danger" ng-show="message">{{message}}</alert>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:genericpage>