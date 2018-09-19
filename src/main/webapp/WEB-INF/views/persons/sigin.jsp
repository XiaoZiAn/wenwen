<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/9/19
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>新增用户</title>
</head>
<body>
<div class="main">
    <h2 class="title"><span>新增用户</span></h2>
    <form:form action="addPerson" method="post">
        <fieldset>
            <legend>用户</legend>
            <table cellpadding="5" cellspacing="8">
                <tr>
                    <td><label for="personName">姓名：</label></td>
                    <td><form:input path="personName" size="40"/></td>
                    <td><form:errors path="personName" cssClass="error"></form:errors></td>
                </tr>
                <tr>
                    <td><label for="personSex">性别：</label></td>
                    <td>
                        <form:select path="personSex" style="width:100%">
                            <form:option value="">--请选择--</form:option>
                            <form:options items="${sexList}"  itemLabel="codedesc" itemValue="code"/>
                        </form:select>
                    </td>
                    <td><form:errors path="personSex" cssClass="error"></form:errors></td>
                </tr>
                <tr>
                    <td><label for="personBirthday">出生日期：</label></td>
                    <td><form:input path="personBirthday" size="40" class="Wdate" onClick="WdatePicker()"/></td>
                    <td><form:errors path="personBirthday" cssClass="error"></form:errors></td>
                </tr>
                <tr>
                    <td><label for="personEmail">邮箱：</label></td>
                    <td><form:input path="personEmail" size="40"/></td>
                    <td><form:errors path="personEmail" cssClass="error"></form:errors></td>
                </tr>
                <tr>
                    <td><label for="personPassword">密码：</label></td>
                    <td><form:input path="personPassword" size="40"/></td>
                    <td><form:errors path="personPassword" cssClass="error"></form:errors></td>
                </tr>
                <input type="submit" value="保存" class="btn out">
            </p>
        </fieldset>
        <!--<form:errors path="*"></form:errors> -->
    </form:form>
    <p style="color: red">${message}</p>
    <p>
        <a href="<c:url value="/user/list" />"  class="abtn out">返回列表</a>
    </p>
</div>
</body>
</html>
