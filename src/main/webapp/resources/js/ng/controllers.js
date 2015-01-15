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

myAppControllers.controller('postsFormCtrl', ['$scope' , '$http', 'postsAddUrl', function($scope, $http, postsAddUrl) {

     $scope.send = function(data) {
        $http.post(postsAddUrl, data)
            .success(function(data) {
                $scope.posts.push(data);
            })
            .error(function() {
                alert("POST fail");
            });
     };

}]);
