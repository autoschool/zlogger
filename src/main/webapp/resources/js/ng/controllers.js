'use strict';

var zloggerControllers = angular.module('zlogger.controllers', []);

/* Controllers */

zloggerControllers.controller('postCommentsCtrl', function ($scope, $http, $location, $interval, postId) {
    $scope.commentaries = [];
    var commentsLoadUrl = "/post/" + postId + "/commentaries";
    var commentsAddUrl = "/post/" + postId + "/addcomment";

     $http.get(commentsLoadUrl)
        .success(function (data) {
            $scope.commentaries = data;
     });

    var model = this;

    model.message = "";

    model.commentary = {
        message: "",
    };

    model.submit = function(isValid) {
        if(isValid) {
            $http.post(commentsAddUrl, model.commentary).
                success(function() {
                    model.message = "";
                    model.commentary.message = "";
                    $window.location.reload();
                }).
                error(function() {
                    model.message = "Could not save commentary. Please try again later.";
                });
        } else {
            model.message = "Commentary is invalid.";
        }
    };
});

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
                success(function() {
                    model.message = "";
                    $window.location.href = "http://" + $window.location.host + "/login";
                }).
                error(function(status) {
                if(status === 409) {
                    model.message = "This username is already taken. Please try another one.";
                }
                });
        } else {
            model.message = "Not all fields are valid";
        }
    };
}]);

zloggerControllers.controller('loginCtrl', function($http, $window) {
    var model = this;

    model.auth = {
        username: "",
        password: ""
    };

    model.submit = function(isValid) {
        if(isValid) {
            $http({
                method: 'POST',
                url : "/j_spring_security_check",
                params : model.auth,
                headers: {'Content-Type': 'application/x-www/form-urlencoded'}
            }).success(function() {
                $window.location.href = "http://" + $window.location.host + "/";
            }).
            error(function() {
                model.message = "This login/password pair is invalid.";
            });
        } else {
            model.message = "Not all fields are valid";
        }
    };
});

zloggerControllers.controller('blogCtrl', function($http, $window) {
    var model = this;

    model.message = "";

    model.post = {
        title: "",
        message: ""
    };

     model.submit = function(isValid) {
        if(isValid) {
            $http.post("/addpost", model.post).
                success(function() {
                    model.message = "";
                    model.post.title = "";
                    model.post.message = "";
                    $window.location.reload();
                }).
                error(function() {
                    model.message = "Saving post was unsuccessfull";
                });
        } else {
            model.message = "Message is invalid";
        }
     };
});

zloggerControllers.controller('avatarCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
  $scope.uploadFile = function(){
      var file = $scope.myFile;
      var uploadUrl = "/user/changeavatar";
      fileUpload.uploadFileToUrl(file, uploadUrl);
  };
}]);

zloggerControllers.controller('detailsCtrl', function($http, $window, email, about, site) {
    var model = this;

    model.message = "";

    model.details = {
        email: email,
        about: about,
        site: site
    };

     model.submit = function(isValid) {
        if(isValid) {
            $http.post("/user/changedetails", model.details).
                success(function() {
                    model.message = "";
                }).
                error(function() {
                    model.message = "Saving details was unsuccessfull.";
                });
        } else {
            model.message = "Some fields are invalid.";
        }
     };
});

zloggerControllers.controller('changePasswordCtrl', function($http, $window) {
    var model = this;

    model.message = "";

    model.submit = function(isValid) {
        if(isValid) {
            $http.post("/user/changepassword", model.password).
                success(function() {
                    model.message = "";
                    model.password.oldPassword = "";
                    model.password.newPassword = "";
                    model.password.confirmPassword = "";
                }).
                error(function(status) {
                    model.message = "Wrong current password.";
                });
        } else {
            model.message = "Not all fields are valid";
        }
    };
});