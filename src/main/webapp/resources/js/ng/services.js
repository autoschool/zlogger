'use strict';

var zloggerServices = angular.module('zlogger.services', []);

zloggerServices.service('fileUpload', function ($http, $window) {
   this.uploadFileToUrl = function(file, uploadUrl, scope){
       var fd = new FormData();
       fd.append('file', file);
       scope.sent = true;
       scope.message = "";
       $http.post(uploadUrl, fd, {
           transformRequest: angular.identity,
           headers: {'Content-Type': undefined}
       })
       .success(function(){
            $window.location.reload();
       })
       .error(function(status){
            scope.sent = false;
            scope.message = "Upload failer. Status code: " + status;
       });
   };
});