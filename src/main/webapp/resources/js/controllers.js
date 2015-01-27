'use strict';

var zloggerControllers = angular.module('zlogger.controllers', ['ui.bootstrap']);

/* Controllers */

zloggerControllers.controller('postCommentsCtrl', function ($scope, $http, $location, $window, postId) {
    $scope.commentaries = [];
    var commentsLoadUrl = "/post/" + postId + "/commentaries";
    var commentsAddUrl = "/user/addcomment/" + postId;

    $http.get(commentsLoadUrl)
       .success(function (data) {
           $scope.commentaries = data;
           $scope.filteredComments = [];
           $scope.currentPage = 1;
           $scope.numPerPage = 10;
           $scope.maxSize = 5;
           $scope.totalItems = $scope.commentaries.length;
    });

    $scope.$watch('currentPage + numPerPage', function() {
        var begin = (($scope.currentPage - 1) * $scope.numPerPage)
        , end = begin + $scope.numPerPage;
        $scope.filteredComments = $scope.commentaries.slice(begin, end);
    });

    var model = this;

    $scope.message = "";
    
    model.commentary = {
        message: ""
    };

    $scope.sent = false;

    model.submit = function(isValid) {
        if(!$scope.sent && isValid) {
            $scope.sent = true;
            $scope.message = "";
            $http.post(commentsAddUrl, model.commentary).
                success(function() {
                    model.commentary.message = "";
                    $window.location.reload();
                }).
                error(function() {
                    $scope.sent = false;
                    $scope.message = "Could not save commentary. Please try again later.";
                });
        }
    };
});

zloggerControllers.controller('registrationController', function($http, $window, $scope) {
    var model = this;

    $scope.message = "";
    $scope.sent = false;

    model.user = {
        userName: "",
        password: "",
        confirmPassword: ""
    };

    model.submit = function(isValid) {
        if(!$scope.sent && isValid) {
            $scope.sent = true;
            $scope.message = "";
            $http.post("/signup", model.user).
                success(function() {
                    $window.location.href = "http://" + $window.location.host + "/login";
                }).
                error(function() {
                    $scope.sent = false;
                    $scope.message = "This username is already taken. Please try another one.";
                });
        }
    };
});

zloggerControllers.controller('loginCtrl', function($http, $window, $scope) {
    var model = this;

    $scope.message = "";
    $scope.sent = false;

    model.auth = {
        username: "",
        password: ""
    };

    model.submit = function(isValid) {
        if(!$scope.sent && isValid) {
            $scope.message = "";
            $scope.sent = true;
            $http({
                method: 'POST',
                url : "/j_spring_security_check",
                params : model.auth,
                headers: {'Content-Type': 'application/x-www/form-urlencoded'}
            }).success(function() {
                $window.location.href = "http://" + $window.location.host + "/";
            }).
            error(function() {
                $scope.sent = false;
                $scope.message = "This login/password pair is invalid. Try again?";
            });
        }
    };
});

zloggerControllers.controller('blogCtrl', function($http, $window, $scope) {
    var model = this;

    $scope.buttonCaption = "Add post";
    $scope.labelCaption = "New post";
    $scope.message = "";
    $scope.edit = false;
    $scope.sent = false;

    $scope.post = {
        id : "",
        title: "",
        message: ""
    };

     model.submit = function(isValid) {
        if(!$scope.sent && isValid) {
            $scope.sent = true;
            $scope.message = "";
            if(!$scope.edit) {
                $http.post("/user/addpost", $scope.post)
                    .success(function() {
                        $scope.post.title = "";
                        $scope.post.message = "";
                        $window.location.reload();
                    })
                    .error(function() {
                        $scope.sent = false;
                        $scope.message = "Saving post was unsuccessfull";
                    });
            } else {
                $http.put("/user/editpost", $scope.post)
                    .success(function() {
                        $scope.post.title = "";
                        $scope.post.message = "";
                        $scope.post.id = "";
                        $window.location.reload();
                    })
                    .error(function() {
                        $scope.sent = false;
                        $scope.message = "Editing post was unsuccessfull";
                    });
            }
        }
     };

     model.del = function(id) {
        if(!$scope.sent) {
            $scope.sent = true;
            $http.delete("/user/deletepost/" + id)
                .success(function() {
                    $window.location.reload();
                })
                .error(function() {
                    $scope.sent = false;
                });
        }
     };
});

zloggerControllers.controller('avatarCtrl', function($scope, fileUpload){
    $scope.sent = false;
    $scope.message = "";

    $scope.uploadFile = function(){
        var file = $scope.myFile;
        var uploadUrl = "/user/changeavatar";
        fileUpload.uploadFileToUrl(file, uploadUrl, $scope);
    };
});

zloggerControllers.controller('detailsCtrl', function($http, $window, $scope, email, about, site) {
    var model = this;

    $scope.message = "";
    $scope.sent = "";

    model.details = {
        email: email,
        about: about,
        site: site
    };

    model.saved = angular.copy(model.details);

    $scope.reset = function() {
        model.details = angular.copy(model.saved);
    };

     model.submit = function(isValid) {
        if(!$scope.sent && isValid) {
            $scope.message = "";
            $scope.sent = true;
            $http.put("/user/changedetails", model.details).
                success(function() {
                    model.saved = angular.copy(model.details);
                    $scope.sent = false;
                }).
                error(function() {
                    $scope.sent = false;
                    $scope.message = "Saving details was unsuccessfull.";
                });
        }
     };
});

zloggerControllers.controller('changePasswordCtrl', function($http, $scope) {
    var model = this;

    $scope.message = "";
    $scope.sent = false;

    model.password = {
        currentPassword: "",
        newPassword: "",
        confirmPassword: ""
    };

    model.submit = function(isValid) {
        if(!$scope.sent && isValid) {
            $scope.message = "";
            $scope.sent = true;
            $http.post("/user/changepassword", model.password).
                success(function() {
                    model.password.currentPassword = "";
                    model.password.newPassword = "";
                    model.password.confirmPassword = "";
                    $scope.sent = false;
                }).
                error(function() {
                    $scope.message = "Wrong current password.";
                    $scope.sent = false;
                });
        }
    };
});