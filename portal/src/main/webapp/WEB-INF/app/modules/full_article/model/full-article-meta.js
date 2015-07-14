/**
 * Created by sasithag on 7/9/2015.
 */

if(app.meta === undefined){
    app.meta = {};    
}

var fullArticleModule = angular.module("fullArticleModule",[])
.config(function(){
        'use strict';
        app.meta.fullArticleModule = {};
        app.meta.fullArticleModule.rootRoute = "/article";
        app.meta.fullArticleModule.controller = "fullArticleController";
        app.meta.fullArticleModule.templateUrl = "app/modules/full_article/view/full-view.html"
        
    });
