/**
 * Created by SasithaG on 3/26/2015.
 */
/*global $, jQuery, angular, window*/
var routes,
    routeElement,
    app,
routes = ['newContent'];
app = angular.module("portalApp",[
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',

    'textAngular',
    'masterModule',
    'landingViewModule',
    'newContentModule'
])
.config(function($routeProvider){

        for (var routeElement in routes) {

            $routeProvider.when(app.meta[routes[routeElement]].rootRoute, {
                templateUrl: app.meta[routes[routeElement]].templateUrl,
                controller: app.meta[routes[routeElement]].controller,
                activeClass: app.meta[routes[routeElement]].activeNavigationClass
            });
            console.log(app.meta[routes[routeElement]].controller);
        }

        $routeProvider
            .when('/',{
                templateUrl: 'management/modules/landing_view/views/landing-view.html',
                controller: 'landingViewController'
            })
            .otherwise({redirectTo: '/'})
    });