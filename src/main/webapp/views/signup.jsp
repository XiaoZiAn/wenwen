<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/9/19
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<!--[if lt IE 7]>
<html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>
<html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>
<html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Free HTML5 Template by FreeHTML5.co"/>
    <meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive"/>


    <!-- Facebook and Twitter integration -->
    <meta property="og:title" content=""/>
    <meta property="og:image" content=""/>
    <meta property="og:url" content=""/>
    <meta property="og:site_name" content=""/>
    <meta property="og:description" content=""/>
    <meta name="twitter:title" content=""/>
    <meta name="twitter:image" content=""/>
    <meta name="twitter:url" content=""/>
    <meta name="twitter:card" content=""/>

    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <link rel="shortcut icon" href="favicon.ico">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700,300' rel='stylesheet' type='text/css'>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/animate.css">
    <link rel="stylesheet" href="/css/style.css">

    <!-- Modernizr JS -->
    <script src="/js/modernizr-2.6.2.min.js"></script>
    <!-- FOR IE9 below -->
    <!--[if lt IE 9]>
    <script src="/js/respond.min.js"></script>
    <![endif]-->

</head>
<body class="style-2">

<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <ul class="menu">
                <li class="active"><a href="/person/signup">Sign Up</a></li>
                <li><a href="/person/signin">Sign In</a></li>
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <!-- Start Sign Up Form -->
            <form class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
                <h2>Sign Up</h2>
                <div class="form-group">
                    <label for="name" class="sr-only">Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="email" class="sr-only">Email</label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Email"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="password" class="sr-only">Password</label>
                    <input type="password" class="form-control" id="password" name="password" placeholder="Password"
                           autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="re-password" class="sr-only">Re-type Password</label>
                    <input type="password" class="form-control" id="re-password" name="repassword"
                           placeholder="Re-type Password" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="remember"><input type="checkbox" id="remember">Remember Me</label>
                </div>
                <div class="form-group">i
                    <p>Already registered? <a href="/person/signin">Sign In</a></p>
                </div>
                <div class="form-group">
                    <input type="button" id="signup" value="Sign Up" class="btn btn-primary">
                </div>
            </form>
            <!-- END Sign Up Form -->
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="/js/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/js/bootstrap.min.js"></script>
<!-- Placeholder -->
<script src="/js/jquery.placeholder.min.js"></script>
<!-- Waypoints -->
<script src="/js/jquery.waypoints.min.js"></script>
<!-- Main JS -->
<script src="/js/main.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#signup").click(function () {
            var name = $("#name").val();
            var password = $("#password").val();
            var email = $("#email").val();
            var person = {
                personName: name,
                email: email,
                password: password
            };//拼装成json格式

            $.ajax({
                type: "POST",
                url: "/person/addPerson",
                data: JSON.stringify(person),
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if (data.rsCode == '0000' || data.rsCode =='0002') {
                        window.location.href = "/views/signin.jsp";
                    }
                    alert(data.rsMsg);
                },
                error: function (data) {
                    alert("错误：" + data.rsCode);
                }
            });
        })
    })
</script>

</body>
</html>
