<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/9/28
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>用户登录</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<form action="/person/check" method="post">
    <div class="login-item">
        <label>用户名：</label>
        <input type="text" name="personName" id="personName" placeholder="点此输入用户名">
    </div>
    <div class="login-item">
        <label>密码：</label>
        <input type="password" name="personPassword" id="personPassword" placeholder="点此输入密码">
    </div>
    <div class="login-submit">
        <input type="submit" value="登录" class="dc-bt login-bt">
    </div>
</form>

</body>
</html>

