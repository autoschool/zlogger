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

    model.message = "";

    model.commentary = {
        message: ""
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
                error(function() {
                    model.message = "This username is already taken. Please try another one.";
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

zloggerControllers.controller('blogCtrl', function($http, $window, $scope) {
    var model = this;

    $scope.buttonCaption = "Add post";
    $scope.labelCaption = "New post";
    $scope.message = "";
    $scope.edit = false;

    $scope.post = {
        id : "",
        title: "",
        message: ""
    };

     model.submit = function(isValid) {
        if(isValid) {
            if(!$scope.edit) {
                $http.post("/user/addpost", $scope.post)
                    .success(function() {
                        model.message = "";
                        $scope.post.title = "";
                        $scope.post.message = "";
                        $window.location.reload();
                    })
                    .error(function() {
                        $scope.message = "Saving post was unsuccessfull";
                    });
            } else {
                $http.put("/user/editpost", $scope.post)
                    .success(function() {
                        model.message = "";
                        $scope.post.title = "";
                        $scope.post.message = "";
                        $scope.post.id = "";
                        $window.location.reload();
                    })
                    .error(function() {
                        $scope.message = "Editing post was unsuccessfull";
                    });
            }
        } else {
            $scope.message = "Message is invalid";
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

zloggerControllers.controller('detailsCtrl', function($http, $window, $scope, email, about, site) {
    var model = this;

    model.message = "";

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
        if(isValid) {
            $http.put("/user/changedetails", model.details).
                success(function() {
                    model.message = "";
                    model.saved = angular.copy(model.details);
                }).
                error(function() {
                    model.message = "Saving details was unsuccessfull.";
                });
        } else {
            model.message = "Some fields are invalid.";
        }
     };
});

zloggerControllers.controller('changePasswordCtrl', function($http) {
    var model = this;

    model.message = "";

    model.password = {
        currentPassword: "",
        newPassword: "",
        confirmPassword: ""
    };

    model.submit = function(isValid) {
        if(isValid) {
            $http.post("/user/changepassword", model.password).
                success(function() {
                    model.message = "";
                    model.password.currentPassword = "";
                    model.password.newPassword = "";
                    model.password.confirmPassword = "";
                }).
                error(function() {
                    model.message = "Wrong current password.";
                });
        } else {
            model.message = "Not all fields are valid";
        }
    };
});