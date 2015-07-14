/**
 * Created by SasithaG on 7/12/2015.
 */

app.factory("masterServices", ["$resource",
function($resource){
    var host,
        categories;

    host = app.general.host;
    categories = "/v1/category";
    
    return{
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
