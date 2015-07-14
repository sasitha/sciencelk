/**
 * Created by sasithag on 7/3/2015.
 */


landingViewModule.factory("landingViewFactory", ["landingViewService", "$rootScope", "$location",
    function(landingViewService, $rootScope, $location){
        'use strict';
        
        var landingView;
        
        landingView = {};
        landingView.articleList = {};
        
        function getArticleList(){
            var articleService = landingViewService.getArticles();
            articleService.query({},{})
                .$promise.then(function(response){
                    console.log(response);
                    landingView.articleList = response;
                })
        }
        
        function getArticleListByCategory(category){
            var articleService,
                id = 0;
            if(category !== null && category !== undefined){
                id = category.id;
            }
            articleService = landingViewService.getArticlesByCategory();
            articleService.query({id: id,page: 1},{})
                .$promise.then(function(response){
                    console.log(response);
                    landingView.articleList = response;
                })
        }
        landingView.reloadArticleList = function(){
            getArticleListByCategory($rootScope.categoryType);
        };
        landingView.viewDetailedArticle = function(article){

            console.log(article);
            $rootScope.selectedArticle = angular.copy(article);
            $location.path('/article');
        };
        return {
            init: function(){
                getArticleListByCategory($rootScope.categoryType);
                return landingView;                
            }
        }
    }]);
