'use strict';

var zloggerServices = angular.module('zlogger.services', []);

zloggerServices.service('fileUpload', ['$http', '$window', function ($http, $window) {
   this.uploadFileToUrl = function(file, uploadUrl){
       var fd = new FormData();
       fd.append('file', file);
       $http.post(uploadUrl, fd, {
           transformRequest: angular.identity,
           headers: {'Content-Type': undefined}
       })
       .success(function(){
            $window.location.reload();
       })
       .error(function(){
       });
   };
}]);