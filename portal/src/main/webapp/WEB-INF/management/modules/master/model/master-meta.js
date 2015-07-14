/**
 * Created by SasithaG on 3/26/2015.
 */
/*global $, jQuery, app, angular*/

if (app.meta === undefined) {
    app.meta = {};
}
var masterModule = angular.module("masterModule", [])
    .config(function () {
        app.meta.master = {};
    });