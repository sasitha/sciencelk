/**
 * Created by sasithag on 7/8/2015.
 */
/*global $, jQuery, app, newContentModule*/
newContentModule.controller("newContentController", ["$scope", "newContentFactory",
function($scope, newContentFactory){
    'use strict';
    $scope.newContent = newContentFactory.init();
}]);
