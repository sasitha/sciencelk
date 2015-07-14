/**
 * Created by SasithaG on 3/26/2015.
 */
/*global $, jQuery, angular, window*/
var routes,
    app,
    routeElement,
    managementApp,
    managementRoutes;
routes = ['fullArticleModule'];
app = angular
    .module('portalApp', [
        'ngCookies',
        'ngResource',
        'ngSanitize',
        'ngRoute',

        //library modules
        '720kb.socialshare',
        
        // Feature Modules
        'landingViewModule',
        'masterModule',
        'fullArticleModule'
    ])
    .config(function ($routeProvider) {
        for (routeElement in routes) {

            $routeProvider.when(app.meta[routes[routeElement]].rootRoute, {
                templateUrl: app.meta[routes[routeElement]].templateUrl,
                controller: app.meta[routes[routeElement]].controller,
                activeClass: app.meta[routes[routeElement]].activeNavigationClass
            });

        }
        $routeProvider
            .when('/', {
                templateUrl: 'app/modules/landing_view/view/landing_view.html',
                controller: 'landingViewController'
            })
            .otherwise({redirectTo: '/'});
    })
/*    .run(function($rootScope, $location){
        $rootScope.$watch("cegegoryType", function(){
            console.log($location.path());
            console.log($rootScope.cegegoryType);
        })
    });*/
