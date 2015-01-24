'use strict';

var zloggerDirectives = angular.module('zlogger.directives', ["ngMessages"]);

/* Directives */

zloggerDirectives.directive('compareTo',function () {
     return {
          require: "ngModel",
          scope: {
            otherModelValue: "=compareTo"
          },
          link: function(scope, element, attributes, ngModel) {

            ngModel.$validators.compareTo = function(modelValue) {
              return modelValue == scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function() {
              ngModel.$validate();
            });
          }
        };
});