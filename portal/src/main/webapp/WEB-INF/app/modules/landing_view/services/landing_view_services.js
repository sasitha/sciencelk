/**
 * Created by sasithag on 7/3/2015.
 */


landingViewModule.factory("landingViewService", ["$resource",
    function($resource){
        'use strict';
        
        var host,
            article,
            articleByCategory;
        
        host = app.general.host;
        article = "/v1/article/:id";
        articleByCategory = "/v1/category/:id/article";
        
        return {
            getArticles : function(){
                return $resource(host+article, {},{
                    query: {
                        method: 'GET',
                        params: {
                            id: '@id'
                        },
                        isArray: true
                    }                    
                })                
            },
            getArticlesByCategory: function(){
                return $resource(host+articleByCategory, {},{
                    query: {
                        method: 'GET',
                        params: {
                            id: '@id'                            
                        },
                        isArray: true
                    }                    
                })                
            }
        }
        
    }]);
