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
              return modelValue === scope.otherModelValue;
            };

            scope.$watch("otherModelValue", function() {
              ngModel.$validate();
            });
          }
        };
});

zloggerDirectives.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

zloggerDirectives.directive('editable', function ($location, $anchorScroll) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.find('button').bind('click', function() {
                scope.post.message = element.find(".content").html().trim();
                scope.post.title = element.find(".post-title").text().trim();
                scope.edit = true;
                scope.post.id = attrs['id'];
                scope.buttonCaption = "Cancel";
                scope.labelCaption = "Edit post"
                scope.$apply();
                element.parent().find(".add-post-block").show();
                $location.hash("edit");
                $anchorScroll();
            });
        }
    };
});

zloggerDirectives.directive('edit', function($location) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            element.find("button").bind('click', function() {
                if(!scope.edit) {
                    element.find(".add-post-block").toggle();
                    if(scope.buttonCaption === "Add post") {
                        scope.buttonCaption = "Hide";
                    } else {
                        scope.buttonCaption = "Add post";
                    };
                } else {
                    scope.edit = false;
                    scope.post.message = "";
                    scope.post.title = "";
                    scope.id = "";
                    element.find(".add-post-block").toggle();
                    scope.buttonCaption = "Add post";
                    scope.labelCaption = "New post";
                    $location.hash("");
                };
                scope.$apply();
            });
        }
    };
});