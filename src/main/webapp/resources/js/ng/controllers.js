'use strict';

var myAppControllers = angular.module('myApp.controllers', []);

/* Controllers */

myAppControllers.controller('postCommentsCtrl', ['$scope', '$http','commentsLoadUrl', function ($scope, $http, commentsLoadUrl) {
    $scope.commentaries = [];

    $http.get(commentsLoadUrl)
        .success(function (data) {
            $scope.commentaries = data;
        })
        .error(function () {
            alert("AJAX fail");
        });
}]);
