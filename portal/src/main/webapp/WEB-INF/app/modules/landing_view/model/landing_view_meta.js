/**
 * Created by SasithaG on 7/1/2015.
 */

/*global $, jQuery, app, angular*/
if (app.meta === undefined) {
    app.meta = {};
}

var landingViewModule = angular.module("landingViewModule", [])
.config(function(){
        'use strict';
        
        app.meta.landingView = {};

    });
