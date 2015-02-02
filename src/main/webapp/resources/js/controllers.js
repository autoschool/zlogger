'use strict';

var zloggerControllers = angular.module('zlogger.controllers', ['ui.bootstrap']);

zloggerControllers.constant('REFRESH_INTERVAL', 300000);

/* Controllers */

zloggerControllers.controller('postCommentsCtrl', ['$scope', '$http', '$interval', 
    'postId', 'REFRESH_INTERVAL', function ($scope, $http, $interval, postId, REFRESH_INTERVAL) {

		$scope.commentaries = [];
		$scope.filteredComments = [];
		$scope.sent = false;
		$scope.message = "";
		$scope.paginationProperties = {
			numPerPage : 10,
			maxSize : 5,
			currentPage : 1
		};

		var COMMENTS_LOAD_URL = "/post/" + postId + "/commentaries";
		var COMMENTS_ADD_URL = "/user/addcomment/" + postId;
		var model = this;

		model.commentary = {
			message: ""
		};

		var applyPagination = function() {
			var begin = (($scope.paginationProperties.currentPage - 1) * 
				$scope.paginationProperties.numPerPage)
			, end = begin + $scope.paginationProperties.numPerPage;
			$scope.filteredComments = $scope.commentaries.slice(begin, end);		
		};

		var receive = function() {
			$http.get(COMMENTS_LOAD_URL)
			.success(function (data) {
				$scope.commentaries = data;
				applyPagination();    
			});
		};

		receive();
		var receivePromise = $interval(receive, REFRESH_INTERVAL);

		$scope.$watch('paginationProperties.currentPage + paginationProperties.numPerPage', applyPagination);

		this.submit = function(isValid) {	
			if(!$scope.sent && isValid) {
				$scope.sent = true;
				$scope.message = "";
				$http.post(COMMENTS_ADD_URL, model.commentary).
				success(function() {
					model.commentary.message = "";
					receive();
					$scope.currentPage = 1;
					$scope.sent = false;
				}).
				error(function() {
					$scope.sent = false;
					$scope.message = "Could not save commentary. Please try again later.";
				});
			}
		};
	}]);

zloggerControllers.controller('registrationController', ['$http', '$window', '$scope', function($http, $window, $scope) {
	
	var model = this;
	$scope.message = "";
	$scope.sent = false;

	model.user = {
		userName: "",
		password: "",
		confirmPassword: ""
	};

	this.submit = function(isValid) {
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
}]);

zloggerControllers.controller('loginCtrl', ['$http', '$window', '$scope', function($http, $window, $scope) {

	var model = this;
	$scope.message = "";
	$scope.sent = false;

	model.auth = {
		username: "",
		password: ""
	};

	this.submit = function(isValid) {
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
}]);

zloggerControllers.controller('blogCtrl', ['$http', '$window', '$scope', function($http, $window, $scope) {
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

	this.windowReload = function() {
		$window.location.reload();
	};

	this.submit = function(isValid) {
		if(!$scope.sent && isValid) {
			$scope.sent = true;
			$scope.message = "";
			if(!$scope.edit) {
				$http.post("/user/addpost", $scope.post)
				.success(function() {
					$scope.post.title = "";
					$scope.post.message = "";
					model.windowReload();
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
					model.windowReload();
				})
				.error(function() {
					$scope.sent = false;
					$scope.message = "Editing post was unsuccessfull";
				});
			}
		}
	};

	this.del = function(id) {
		if(!$scope.sent) {
			$scope.sent = true;
			$http.delete("/user/deletepost/" + id)
			.success(function() {
				model.windowReload();
			})
			.error(function() {
				$scope.sent = false;
			});
		}
	};
}]);

zloggerControllers.controller('avatarCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
	$scope.sent = false;
	$scope.message = "";

	$scope.uploadFile = function(){
		var file = $scope.myFile;
		var uploadUrl = "/user/changeavatar";
		fileUpload.uploadFileToUrl(file, uploadUrl, $scope);
	};
}]);

zloggerControllers.controller('detailsCtrl', 
	['$http', '$window', '$scope', 'email', 'about', 'site', 
	function($http, $window, $scope, email, about, site) {

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

		this.submit = function(isValid) {
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
	}]);

zloggerControllers.controller('changePasswordCtrl', ['$http', '$scope', function($http, $scope) {

	var model = this;
	$scope.message = "";
	$scope.sent = false;

	model.password = {
		currentPassword: "",
		newPassword: "",
		confirmPassword: ""
	};

	this.submit = function(isValid) {
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
}]);