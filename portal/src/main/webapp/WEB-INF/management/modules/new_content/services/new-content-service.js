/**
 * Created by SasithaG on 7/12/2015.
 */

newContentModule.factory("newContentServices", ["$resource",
function($resource){
    'use strict';
    var host,
        newArticle,
        categories;
    
    host = app.general.host;
    newArticle = "/v1/article";
    categories = "/v1/category";
    return{
        addNewArticle:function(){
            return $resource(host+newArticle,{},{
                create:{
                    method: 'POST',
                    isArray: false
                }                
            })            
        },
        getCategoryList : function(){
            return $resource(host + categories, {}, {
                query:{
                    method: 'GET',
                    isArray: true
                }
            })
        }
    }
}]);
