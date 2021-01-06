<%--
  Created by IntelliJ IDEA.
  User: 卢卓桓
  Date: 2020/5/27
  Time: 19:25
  To change this template use File | Settings | File Templates.
  这份文件暂时不用
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
    <link rel="stylesheet" href="${ctx}/static/css/blog/blog_content.css" />
    <script src="${ctx}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <title>${blogContentDto.categoryTitle} - ${blogContentDto.blogTitle}</title>
</head>
<body>
<div id="layout">
    <div class="col-md-9">
        <div id="test-editormd-view">
            <textarea style="display:none;" name="test-editormd-markdown-doc">###Hello world!</textarea>
        </div>
    </div>
    <div class="col-md-3">
        <div id="sidebar">
            <h1>目录</h1>
            <div class="markdown-body editormd-preview-container" id="custom-toc-container">空</div>
        </div>
    </div>
</div>

<!-- <script src="js/zepto.min.js"></script>
<script>
    var jQuery = Zepto;  // 为了避免修改flowChart.js和sequence-diagram.js的源码，所以使用Zepto.js时想支持flowChart/sequenceDiagram就得加上这一句
</script> -->
<script src="${ctx}/static/plugin/editormd/lib/marked.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/prettify.min.js"></script>

<script src="${ctx}/static/plugin/editormd/lib/raphael.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/underscore.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/sequence-diagram.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/flowchart.min.js"></script>
<script src="${ctx}/static/plugin/editormd/lib/jquery.flowchart.min.js"></script>

<script src="${ctx}/static/plugin/editormd/editormd.min.js"></script>
<script src="${ctx}/static/js/blog/blog_content.js"></script>
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
            layout.html("没有此文章！");
        </c:otherwise>
    </c:choose>

</script>
</body>
</html>
