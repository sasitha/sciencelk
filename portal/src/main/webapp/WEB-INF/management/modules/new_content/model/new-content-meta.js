/**
 * Created by sasithag on 7/8/2015.
 */
/*global $, jQuery, app, angular*/
if (app.meta === undefined) {
    app.meta = {};
}

var newContentModule = angular.module("newContentModule", [])
.config(function(){
        'use strict';
        
        app.meta.newContent = {};
        app.meta.newContent.title = "New Content";
        app.meta.newContent.rootRoute = "/new";
        app.meta.newContent.featureCode = "NEWCONTENT";
        app.meta.newContent.templateUrl = "management/modules/new_content/views/new_content.html";
        app.meta.newContent.controller = "newContentController";
        app.meta.newContent.activeNavigationClass = "";
        app.meta.newContent.contextIconClass = "class";
        
    });

