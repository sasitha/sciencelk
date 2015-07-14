/**
 * Created by SasithaG on 7/9/2015.
 */
fullArticleModule.factory("fullArticleFactory", ["$route", "fullArticleServices","$location",
function($route, fullArticleServices, $location){
    
    var fullArticleFactory;
    
    fullArticleFactory = {};
    
    function loadFullArticle(articleId){
        var service = fullArticleServices.getDetailedArticle();
        service.query({id:articleId},{})
            .$promise.then(function(responce){
                console.log(responce);               
                fullArticleFactory.detailedArticle = responce;
            });
    }
    
    return{
        init: function(article){
            if (article !== null && article !== undefined) {
                loadFullArticle(article.id);
                fullArticleFactory.selectedArticle = article;
            }else{
                $location.path("/");
            }
            return fullArticleFactory;            
        }
    }
}]);