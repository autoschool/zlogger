'use strict';

var zloggerControllers = angular.module('zlogger.controllers', []);

/* Controllers */

zloggerControllers.controller('postCommentsCtrl', ['$scope', '$http','commentsLoadUrl', function ($scope, $http, commentsLoadUrl) {
    $scope.commentaries = [];

    $http.get(commentsLoadUrl)
        .success(function (data) {
            $scope.commentaries = data;
        })
        .error(function () {
            alert("AJAX fail");
        });
}]);

zloggerControllers.controller('registrationController', ['$http', '$window', function($http, $window) {
    var model = this;

    model.message = "";

    model.user = {
        userName: "",
        password: "",
        confirmPassword: ""
    };

    model.submit = function(isValid) {
        if(isValid) {
            $http.post("/signup", model.user).
                success(function(data, status, headers, config) {
                    $window.location.href = "http://" + $window.location.host + "/";
                }).
                error(function(data, status, headers, config) {
                if(status == 409) {
                    model.message = "This username is already taken. Please try another one.";
                }
                });
        } else {
            model.message = "Not all required fields are valid";
        }
    };
}]);
