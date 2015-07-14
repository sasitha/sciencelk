/**
 * Created by SasithaG on 3/26/2015.
 */
/*global $, jQuery, app, angular, resizeColumns*/

app.controller("masterController", ["$scope", "$rootScope", "masterFactory",
    function ($scope, $rootScope, masterFactory) {
        
        $scope.master = masterFactory.init();
        
        $scope.navigateToLogin = function(){
            window.location.href = app.baseURL + '/login';
        };
        
        $rootScope.categoryType = {};
    }]);