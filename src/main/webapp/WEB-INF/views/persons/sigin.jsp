<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/9/19
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>用户注册</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $.fn.serializeObject = function()
            {
                var o = {};
                var a = this.serializeArray();
                $.each(a, function() {
                    if (o[this.name]) {
                        if (!o[this.name].push) {
                            o[this.name] = [o[this.name]];
                        }
                        o[this.name].push(this.value || '');
                    } else {
                        o[this.name] = this.value || '';
                    }
                });
                return o;
            };
            $("#submitSigin").click(function(){
                // var personName = $("#personName").val();
                // var personEmail = $("#personEmail").val();
                // var personPassword = $("#personPassword").val();
                // var person = {personName:personName,
                //     personEmail:personEmail,
                //     personPassword:personPassword}; //拼装成json格式
                var person = $("#personForm").serializeObject();
                $.ajax({
                    type:"POST",
                    url:"/wenwen/person/addPerson",
                    data:JSON.stringify(person),
                    dataType:"json",
                    contentType:"application/json;charset=utf-8",
                    success:function(data){
                        var jsonData = JSON.parse(data);
                        console.log(jsonData);
                        if (jsonData.rsCode == "0000") {//请求成功
                            alert("注册成功");
                        } else {
                            alert("注册失败:"+jsonData.rsMsg);
                        }
                    },
                    error:function() {
                        alert("注册失败：网络异常！");
                    }
                });
            });
        });
    </script>

</head>
<body>
<form action="/wenwen/person/addPerson" method="post">
    <div class="login-item">
        <label>用户名：</label>
        <input type="text" name="personName" id="personName" placeholder="点此输入用户名">
    </div>
    <div class="login-item">
        <label>密码：</label>
        <input type="password" name="personPassword" id="personPassword" placeholder="点此输入密码">
    </div>
    <div class="login-item">
        <label>邮箱：</label>
        <input type="text" name="personEmail" id="personEmail" placeholder="点此输入邮箱">
    </div>
    <div class="login-submit">
        <input type="submit" value="注册" class="dc-bt login-bt">
    </div>
</form>

</body>
</html>
