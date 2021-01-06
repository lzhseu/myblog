<%--
  Created by IntelliJ IDEA.
  User: 卢卓桓
  Date: 2020/5/31
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="author" content="lzh, lzhseu@163.com">
    <link href="https://cdn.bootcdn.net/ajax/libs/normalize/8.0.1/normalize.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/blogger/login.css">
    <script src="${ctx}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/constant/url-constant.js"></script>
    <script src="${ctx}/static/js/common/common.js"></script>
    <title>登录</title>
</head>
<body>
    <div class="container">

        <form class="form-signin">
            <h2 class="form-signin-heading">登录</h2>
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="邮箱" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="密码" required>
            <button class="btn btn-lg btn-primary btn-block" type="button" id="login-btn">登录</button>
        </form>

    </div>

    <script src="${ctx}/static/js/blogger/login.js"></script>
</body>
</html>
