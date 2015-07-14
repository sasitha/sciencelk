<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="req" value="${pageContext.request}"/>
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}"/>
<html ng-app="portalApp">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=9;IE=EDGE">
    <title>Portal | Management</title>

    <link rel="stylesheet" href="management/bower_components/bootstrap/dist/css/bootstrap.css"/>
    <link rel="stylesheet" href="management/bower_components/textAngular/dist/textAngular.css">
    <link rel="stylesheet" href="management/bower_components/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="management/styles/management.css">

    <script src="management/bower_components/jquery/dist/jquery.js"></script>
    <script src="management/bower_components/angular/angular.js"></script>
    <script src="management/bower_components/angular-route/angular-route.js"></script>
    <script src="management/bower_components/angular-cookies/angular-cookies.js"></script>
    <script src="management/bower_components/angular-sanitize/angular-sanitize.js"></script>
    <script src="management/bower_components/angular-resource/angular-resource.js"></script>
    <script src="management/bower_components/bootstrap/dist/js/bootstrap.js"></script>

    <script src="management/bower_components/textAngular/dist/textAngular-rangy.min.js"></script>
    <script src="management/bower_components/textAngular/dist/textAngular-sanitize.min.js"></script>
    <script src="management/bower_components/textAngular/dist/textAngular.min.js"></script>


    <script src="management/app.js"></script>

    <%--meta files--%>
    <script src="management/modules/general/model/general_meta.js"></script>
    <script src="management/modules/master/model/master-meta.js"></script>
    <script src="management/modules/new_content/model/new-content-meta.js"></script>
    <script src="management/modules/landing_view/model/landing-view-model.js"></script>

    <%--service files--%>
    <script src="management/modules/new_content/services/new-content-service.js"></script>
    

    <%--factory files--%>
    <script src="management/modules/master/factories/master-factory.js"></script>
    <script src="management/modules/new_content/factories/new_content_factory.js"></script>

    <%--contollers--%>
    <script src="management/modules/master/controllers/master-controller.js"></script>
    <script src="management/modules/new_content/controllers/new_content_controller.js"></script>
    <script src="management/modules/landing_view/controllers/landing-view-controller.js"></script>

    <script>
        app.authHeader = '${authHeader}';
        app.baseURL = '${baseURL}';
        app.userName = '${userName}';
        //console.log(app.authHeader);
    </script>
</head>
<body ng-controller="masterController">
<!-- Navigation bar -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid col-md-11 col-lg-offset-1">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Science-lk</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <p class="navbar-text">Signed in as : ${userName}</p>
                <p class="navbar-text">|</p>
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <span class="glyphicon glyphicon-cog"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value='/j_spring_security_logout'/>">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="col-lg-12 m-page-wrapper">
    <div class="row" style="  margin: 80px 30px 0px 30px;">
        <div class="col-lg-2" ng-include="'management/modules/left-panel/views/left_panel.html'" style="  margin-top: 23px;">
        </div>
        <div class="col-lg-10" ng-view="">
        </div>
    </div>
</div>
</body>
</html>
