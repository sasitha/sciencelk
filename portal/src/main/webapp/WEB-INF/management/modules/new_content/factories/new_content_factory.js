/**
 * Created by sasithag on 7/8/2015.
 */
/*global $, jQuery, app, newContentModule*/
newContentModule.factory("newContentFactory", ["$route", "newContentServices",
    function($route, newContentServices){
        'use strict';
        var newContentFactory;
        
        newContentFactory = {};
        newContentFactory.htmlContent = "";
        newContentFactory.newArticle = {};
        function getCategoryList(){
            var service = newContentServices.getCategoryList();
            service.query({},{})
                .$promise.then(function(response){
                    console.log(response);
                    newContentFactory.categoryList = response;
                    if (response.length >0) {
                        newContentFactory.selectedCategory = response[0];
                    }
                })
        }
        newContentFactory.save = function(){
            console.log(newContentFactory.newArticle);
            var service,
                article;
            
            article = {};
            article.id = 0;
            article.author = null;
            article.moderator = null;
            article.title = angular.copy(newContentFactory.newArticle.title);
            article.body = angular.copy(newContentFactory.newArticle.htmlContent);
            article.tags = [];
            article.intro = angular.copy(newContentFactory.newArticle.intro);
            article.category = newContentFactory.selectedCategory.id;
            service = newContentServices.addNewArticle();
            service.create({},article, function(response){
                console.log(response);                
            });
        };
        newContentFactory.categorySelected = function(category){
            console.log(category);
            newContentFactory.selectedCategory = category;
        };
        return{
            init: function(){
                getCategoryList();
                return newContentFactory;
            }
        }
    }
]);
