/**
 * Created by SasithaG on 7/1/2015.
 */

landingViewModule.controller("landingViewController", ["$scope", "landingViewFactory", "$rootScope",
    function($scope, landingViewFactory, $rootScope){
        'use strict';
        $scope.landingView = landingViewFactory.init();
        $scope.$watch("categoryType", function(newVal, oldVal){
            $scope.landingView.reloadArticleList();
        });
    }]);
