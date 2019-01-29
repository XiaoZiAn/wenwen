<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/12/12
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!--[if lt IE 7]> <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]> <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]> <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Minimal and Clean Sign up / Login and Forgot Form by FreeHTML5.co</title>
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
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12 text-center">
            <ul class="menu">
            </ul>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <form class="fh5co-form animate-box" data-animate-effect="fadeIn">
                <h2>Forgot Password</h2>
                <div class="form-group">
                    <label for="name" class="sr-only">UserName/Email</label>
                    <input type="text" class="form-control" id="name" placeholder="UserName/Email" autocomplete="off">
                </div>
                <div class="form-group">
                    <label for="code" class="sr-only">UserName/Email</label>
                    <input type="text" class="form-control" id="code" placeholder="code" autocomplete="off">
                    <input type="button" id="sendpasswordcode" value="send Code" class="btn btn-primary">
                </div>
                <div class="form-group">
                    <p><a href="/person/signin">Sign In</a> or <a href="/person/signup">Sign Up</a></p>
                </div>
                <div class="form-group">
                    <input type="button" id="checkcode" value="Sure" class="btn btn-primary">
                </div>
            </form>
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
        $("#sendpasswordcode").click(function () {
            var name = $("#name").val();
            var param = {
                personName: name
            };//拼装成json格式

            $.ajax({
                type: "POST",
                url: "/person/sendPasswordCode",
                data: JSON.stringify(param),
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    alert(data.rsMsg);
                },
                error: function (data) {
                    alert("错误：" + data.rsCode);
                }
            });
        });

        $("#checkcode").click(function () {
            var name = $("#name").val();
            var code = $("#code").val();
            var param = {
                personName: name,
                passwordCode: code
            };//拼装成json格式

            $.ajax({
                type: "POST",
                url: "/person/checkCode",
                data: JSON.stringify(param),
                contentType: 'application/json;charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if (data.rsCode == '00000') {
                        window.location.href = "/views/changepassword.jsp?name=" + data.data.personName + "&passwordCode=" + data.data.passwordCode;
                    } else {
                        alert(data.rsMsg);
                    }
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