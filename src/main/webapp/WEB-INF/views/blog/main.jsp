<%--
  Created by IntelliJ IDEA.
  User: 卢卓桓
  Date: 2020/5/26
  Time: 15:10
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
    <link rel="stylesheet" href="${ctx}/static/css/blog/main.css">
    <script src="${ctx}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/bootstrap-paginator.js"></script>
    <script src="${ctx}/static/js/constant/url-constant.js"></script>
    <script src="${ctx}/static/js/common/common.js"></script>

    <title>${bloggerProfile.nickname} - 主页</title>
</head>
<body>

    <div class="col-md-2 side-bar">
        <div class="header">
            <a href="/home/page=1" class="logo">${bloggerProfile.nickname}</a>
            <div class="intro">${bloggerProfile.intro}</div>
        </div>
        <div class="about-me">
            <div class="self-tag"></div>
            <div class="email">${bloggerProfile.email}</div>
        </div>
        <div class="tag-list">
            <c:forEach var="item" items="${blogCategory}">
                <button type="button" class="btn btn-link category-btn"># ${item.title}</button>
            </c:forEach>
        </div>
    </div>
    <div class="col-md-7 main">
        <div class="article-list" id="article-list">
            <c:forEach var="item" items="${blogList}">
                <div class="item">
                    <a href="/blog/content/${item.id}" target="_blank" class="title">${item.title}</a>
                    <div class="status">发布于：${item.date}  |  ${item.nickname}  |  ${item.category}</div>
                    <div class="content">${item.intro}</div>
                </div>
            </c:forEach>
            <div id ="pager-root" style="text-align:center">
                <ul id="pager"></ul>
            </div>
        </div>
        <div class="footer">
            Powered by Lu Zhuohuan &nbsp;&nbsp;<a href="http://www.beian.miit.gov.cn/">粤ICP备20050301号</a>
        </div>
    </div>
    <div class="col-md-3 right-bar">
        <!-- <div class="search-bar">搜索框</div> -->
    </div>

    <script type="application/javascript">
        <c:if test="${not empty bloggerProfile.label}">
         var bloggerLabel = "${bloggerProfile.label}";
        </c:if>
        var totalPages = ${totalPages};
        var totalBlogs = ${totalBlogs};
        var pageIndex = ${pageIndex};

    </script>
    <script src="${ctx}/static/js/blog/main.js"></script>

</body>
</html>
