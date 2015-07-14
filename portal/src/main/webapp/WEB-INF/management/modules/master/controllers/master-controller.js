/**
 * Created by SasithaG on 3/26/2015.
 */
/*global $, jQuery, app, angular, resizeColumns*/

masterModule.controller("masterController", ["$scope", "$location",
    function ($scope, $location) {
        $scope.navigateToLogin = function(){
            window.location.href = app.baseURL + '/login';
        };

        $scope.navigate = function (path) {
            console.log(path);
            $location.path(path);
        };
    }]);