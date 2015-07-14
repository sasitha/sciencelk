/**
 * Created by SasithaG on 3/26/2015.
 */

/*global $, jQuery, app, angular*/

app.factory("masterFactory", ["$route", "masterServices", "$rootScope", "$location",
    function ($route, masterServices,$rootScope, $location) {
        
        var master;
        
        master = {};
        master.categoryList = {};
        function changeLocation(){
            $location.path('/');
        }
        master.categoryChanged = function(category){
            $rootScope.categoryType = category;
            changeLocation();
        };
        master.displayAdds = function(){
            return $location.path() === "/article";
        };
        function getCategoryList(){
            var service = masterServices.getCategoryList();
            service.query({},{})
                .$promise.then(function(response){
                    master.categoryList = response;
                    if (response.length >0) {
                        $rootScope.categoryType = response[0]
                    }
                })
        }
        
        return {
            init: function(){
                getCategoryList();
                return master;                
            }
        }

    }]);
