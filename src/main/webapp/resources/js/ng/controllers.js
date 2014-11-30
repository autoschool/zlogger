'use strict';

/* Controllers */

angular.module('myApp.controllers', [])
.controller('postCommentsCtrl', ['$scope', '$http','loadUrl', function ($scope, $http, loadUrl) {
    $scope.commentaries = [];

    var responsePromise = $http({ method: 'GET', url: loadUrl });
    responsePromise.success(function (data, status, headers, config) {
        $scope.commentaries = data;
    });
    responsePromise.error(function (data, status, headers, config) {
        alert("AJAX fail");
    });

}])
;
