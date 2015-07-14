<%--
  Created by IntelliJ IDEA.
  User: SasithaG
  Date: 3/27/2015
  Time: 10:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=10">
    <title>Portal | Login</title>
    <link href="app/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="app/styles/app.css">
    <script type="text/javascript" src="app/bower_components/jquery/dist/jquery.js"></script>
    <script type="text/javascript" src="app/bower_components/bootstrap/dist/js/bootstrap.js"></script>
</head>
<body onload='document.loginForm.username.focus();'>
<div class="loginPanel col-md-4 col-lg-offset-4">
    <form name="loginForm" action="<c:url value='/perform_login'/>" method='POST'
          onsubmit="return isFormValid()">
        <div class="form-group">
            <label for="username">Email address</label>
            <input type="email" class="form-control" id="username" placeholder="Email" name="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
        </div>
        <div class="form-group">
            <c:if test="${hasError==true}">
                <c:if test="${errorMessage!=null}">
                    <div id="loginFailedMsg" class="text_error">${errorMessage}</div>
                </c:if>
                <c:if test="${errorMessage==null}">
                    <div id="loginFailedMsg" class="text_error">Login failed</div>
                </c:if>
            </c:if>
            <div id="errorMsg" class="alert alert-danger" role="alert" style="display: none">
                <strong>Oh Snap!</strong>
            </div>
            <button type="submit" class="btn btn-default">Sign In</button>
        </div>
    </form>
    <div class="jumbotron">
        <h3>Hi There,</h3>
        Sciencelk.com login is for invitations only. If you are interested in joining with us just drop an email to us
        and we will ge back to you.
        <h4>Thanks for the interest. You can go back to your usual place from <a href="${baseURL}/Portal/app">here</a></h4>
    </div>
</div>

<script>
    isFormValid = function () {
        var userNameVal = $.trim($('#username').val());
        var passwordVal = $.trim($('#password').val());
        var regex = new RegExp("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$");

        if ($('#loginFailedMsg') != undefined) {
            $('#loginFailedMsg').text("");
        }

        if (userNameVal.length == 0 || passwordVal.length == 0) {
            $('#errorMsg').text("Oops! Looks like your login is incorrect. Try again.");
            $('#errorMsg').css("display", 'block');
            return false;
        } else if (!regex.test(userNameVal)) {
            $('#errorMsg').text("Oops! Looks like your email is not an actual email address.");
            $('#errorMsg').css("display", 'block');
            return false;
        } else {
            $('#errorMsg').text("");
            return true;
        }

    }
</script>
</body>
</html>
