/**
 * Created by SasithaG on 7/9/2015.
 */

fullArticleModule.controller("fullArticleController", ["$scope", "$rootScope", "fullArticleFactory",
function($scope, $rootScope, fullArticleFactory){
    
    $scope.fullArticle = fullArticleFactory.init($rootScope.selectedArticle);
    
    
}]);
