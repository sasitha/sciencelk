/**
 * Created by SasithaG on 7/13/2015.
 */


fullArticleModule.factory("fullArticleServices",["$resource",
function($resource){
    'use strict';
    
    var host,
        articleDetails;

    host = app.general.host;
    articleDetails = "/v1/article/:id";
    
    return{
        getDetailedArticle : function(){
            return $resource(host+articleDetails, {},{
                query: {
                    method: 'GET',
                    params: {
                        id: '@id'
                    }
                }
            })
        }
    }
}]);
