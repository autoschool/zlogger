'use strict';

var myAppControllers = angular.module('myApp.controllers', []);

/* Controllers */

myAppControllers.controller('postCommentsCtrl', ['$scope', '$http','commentsLoadUrl', function ($scope, $http, commentsLoadUrl) {
    $scope.commentaries = [];

    var responsePromise = $http({ method: 'GET', url: commentsLoadUrl });
    responsePromise.success(function (data, status, headers, config) {
        $scope.commentaries = data;
    });
    responsePromise.error(function (data, status, headers, config) {
        alert("AJAX fail");
    });
}]);

myAppControllers.controller('postsCtrl', ['$scope', '$http','postsLoadUrl', function ($scope, $http, postsLoadUrl) {

    $scope.posts = [];

    $http.get(postsLoadUrl)
        .success(function (data) {
            $scope.posts = data;
        })
        .error(function (data, status) {
            alert("AJAX fail");
        });


}]);

myAppControllers.controller('postsFormCtrl', ['$scope' , '$http', 'postsAddUrl', function($scope, $http, postsAddUrl) {

     $scope.send = function(data) {
        $http.post(postsAddUrl, data)
            .success(function(data) {
                $scope.posts.push(data);
            })
            .error(function(data) {
                alert("POST fail");
            });
     };

}]);
