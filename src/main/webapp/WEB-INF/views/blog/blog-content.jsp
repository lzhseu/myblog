<%--
  Created by IntelliJ IDEA.
  User: 卢卓桓
  Date: 2020/6/6
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="author" content="lzh, lzhseu@163.com">
    <link rel="stylesheet" href="${ctx}/static/plugin/editormd/css/editormd.preview.css" />
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/blog/blog-content.css" />
    <script src="${ctx}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/common/common.js"></script>
    <title>${blogContentDto.categoryTitle} - ${blogContentDto.blogTitle}</title>
</head>
<body>
<div id="wrapper">
    <div class="overlay"></div>

    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
        <div id="content-table">
            <h2>目录</h2>
            <div class="markdown-body editormd-preview-container" id="custom-toc-container">空</div>
        </div>
    </nav>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <button type="button" class="hamburger is-closed animated fadeInLeft" data-toggle="offcanvas">
            <span class="hamb-top"></span>
            <span class="hamb-middle"></span>
            <span class="hamb-bottom"></span>
        </button>
        <div class="container">
            <div class="col-lg-9 col-lg-offset-1 main-container" id="main-container">
                <div id="layout">
                    <div id="test-editormd-view">
                        <textarea style="display:none;" name="test-editormd-markdown-doc">###Hello world!</textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="col-lg-9 col-lg-offset-1">
                Powered by Lu Zhuohuan &nbsp;&nbsp;<a href="http://www.beian.miit.gov.cn/">粤ICP备20050301号</a>
            </div>
            </div>
        </div>
    </div>

</div>

<script src="${ctx}/static/plugin/editormd/lib/marked.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/prettify.min.js"></script>

<script src="${ctx}/static/plugin/editormd/lib/raphael.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/underscore.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/sequence-diagram.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/flowchart.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/jquery.flowchart.min.js"></script>

<script src="${ctx}/static/plugin/editormd/editormd.min.js"></script>
<script src="${ctx}/static/js/blog/blog-content.js"></script>
<script type="text/javascript">

    var layout = $('#layout');
    <c:choose>
        <c:when test="${not empty blogContentDto}">
            var headerDom = '<div class="header">\n' +
                '                <div class="blog-title"><h1>${blogContentDto.blogTitle}</h1></div>\n' +
                '                <div class="blog-info">${blogContentDto.author} &nbsp;&nbsp; ${blogContentDto.distrDate}</div>\n' +
                '            </div>\n' +
                '            <hr>';
            layout.prepend(headerDom);
            fillBlogContent('${blogContentDto.contentMd}');
        </c:when>
        <c:otherwise>
            layout.html("<div style='font-size: 20px; color: #454545; padding-top: 30px; padding-left: 30px'>没有此文章！</div>");

        </c:otherwise>
    </c:choose>

    handleHeight('#main-container');

</script>
<script type="text/javascript">
    $(document).ready(function () {
        var trigger = $('.hamburger'),
            overlay = $('.overlay'),
            isClosed = false;

        trigger.click(function () {
            hamburger_cross();
        });

        function hamburger_cross() {

            if (isClosed === true) {
                overlay.hide();
                trigger.removeClass('is-open');
                trigger.addClass('is-closed');
                isClosed = false;
            } else {
                overlay.show();
                trigger.removeClass('is-closed');
                trigger.addClass('is-open');
                isClosed = true;
            }
        }

        $('[data-toggle="offcanvas"]').click(function () {
            $('#wrapper').toggleClass('toggled');
        });
    });
</script>
</body>
</html>
