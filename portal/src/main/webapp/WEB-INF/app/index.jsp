<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"/>
<html ng-app="portalApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=9;IE=EDGE">
    <title>Portal</title>
    <link rel="stylesheet" href="app/bower_components/bootstrap/dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="app/bower_components/textAngular/dist/textAngular.css">
    <link rel="stylesheet" href="app/bower_components/font-awesome/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="app/styles/app.css">

    <script src="app/bower_components/jquery/dist/jquery.js"></script>
    <script src="app/bower_components/angular/angular.js"></script>
    <script src="app/bower_components/angular-route/angular-route.js"></script>
    <script src="app/bower_components/angular-cookies/angular-cookies.js"></script>
    <script src="app/bower_components/angular-sanitize/angular-sanitize.js"></script>
    <script src="app/bower_components/angular-resource/angular-resource.js"></script>
    <script src="app/bower_components/bootstrap/dist/js/bootstrap.js"></script>
    
    <script src="app/bower_components/textAngular/dist/textAngular-rangy.min.js"></script>
    <script src="app/bower_components/textAngular/dist/textAngular-sanitize.min.js"></script>
    <script src="app/bower_components/textAngular/dist/textAngular.min.js"></script>
    <script src="app/bower_components/angularjs-socialshare/src/js/angular-socialshare.js"></script>
    

    <script src="app/app.js"></script>

    <%--meta files--%>
    <script src="app/modules/general/model/general_meta.js"></script>
    <script src="app/modules/master/model/master_meta.js"></script>
    <script src="app/modules/landing_view/model/landing_view_meta.js"></script>
    <script src="app/modules/full_article/model/full-article-meta.js"></script>
    
    
    <%--service files--%>
    <script src="app/modules/landing_view/services/landing_view_services.js"></script>
    <script src="app/modules/master/services/master-services.js"></script>
    <script src="app/modules/full_article/services/full-article-service.js"></script>
    
    <%--factory files--%>
    <script src="app/modules/master/factories/master-factory.js"></script>
    <script src="app/modules/landing_view/factories/landing_view_factory.js"></script>
    <script src="app/modules/full_article/factories/full-article-factory.js"></script>
    
    <%--contollers--%>
    <script src="app/modules/master/controllers/master-controller.js"></script>
    <script src="app/modules/landing_view/controllers/landing_view_controller.js"></script>
    <script src="app/modules/full_article/controllers/full-article-controller.js"></script>
    
    
    <script>
        //app.authHeader = '${authHeader}';
        app.baseURL = '${baseURL}';
        //console.log(app.authHeader);
    </script>
</head>
<body ng-controller="masterController">
<!-- Navigation bar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid col-md-9 col-lg-offset-1">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#/">Science-lk</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <button type="button" class="btn btn-default navbar-btn" ng-click="navigateToLogin()">Sign In</button>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<div style="margin-top: 75px; margin-bottom: 30px;"  class="col-lg-9 col-lg-offset-1">
    <div class="col-lg-3">LOGO</div>
</div>
<div  class="row col-lg-10 col-lg-offset-1" style="margin-top: 30px;">
    <div class="col-md-2" style="  margin-top: 20px;">
        <div ng-if="master.displayAdds()">
            Advertising space available.
        </div>
    </div>
    <div class="col-md-8" ng-view="">

    </div>
    <div class="row col-md-2" style="  margin-top: 20px;">
        <button class="row btn btn-default btn-lg category-btn"
                ng-repeat="category in master.categoryList"
                ng-click="master.categoryChanged(category)">
            {{category.name}}
        </button>
    </div>
</div>

</body>
</html>
