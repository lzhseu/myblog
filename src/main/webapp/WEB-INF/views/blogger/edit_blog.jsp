<%--
  Created by IntelliJ IDEA.
  User: 卢卓桓
  Date: 2020/5/29
  Time: 14:14
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

    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/plugin/editormd/css/editormd.css" />
    <link rel="stylesheet" href="${ctx}/static/css/blog/edit_blog.css">

    <script src="${ctx}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/constant/url-constant.js"></script>
    <script src="${ctx}/static/js/common/common.js"></script>
    <title>${bloggerName} - 编辑</title>
</head>
<body>

<div class="header">
    <div class="col-md-2">
        <div class="title">创建您的博文</div>
    </div>
    <div class="col-md-6">
        <div class="other-blog-info">
            <div class="col-sm-6">
                <input type="text" class="form-control blog-title" id="blog-title" placeholder="博文标题">
            </div>
            <div class="col-sm-3" >
                <div class="select-category">
                    <select class="form-control" id="select-category" onchange="selectChange(this)">
                        <c:forEach var="item" items="${bloggerCategory}">
                            <option value="${item.id}">${item.title}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </div>
    </div>
    <div class="col-md-4">
        <div class="btns">
            <button type="button" class="btn btn-default" id="save-btn">保存草稿</button>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">立即发布</button>
            <button type="button" class="btn btn-default" id="internal-btn">保存为内部文章</button>
            <button type="button" class="btn btn-default" id="again-btn">再写一篇</button>
        </div>
    </div>

    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">立即发布</h4>
                </div>
                <div class="modal-body">
                    摘要（必填，不超过130字）：
                    <textarea id="blog-abstract" cols="30" rows="10" class="form-control"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="distribute-btn">发布</button>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="editormd" id="editormd-container">
    <textarea class="editormd-markdown-textarea" name="test-editormd-markdown-doc"
              id="editormd"></textarea>

    <!-- 第二个隐藏文本域，用来构造生成的HTML代码，方便表单POST提交，这里的name可以任意取，后台接受时以这个name键为准 -->
    <!-- html textarea 需要开启配置项 saveHTMLToTextarea == true -->
    <textarea class="editormd-html-textarea" name="editorhtml"
              id="editorHtml"></textarea>
</div>

<script src="${ctx}/static/plugin/editormd/editormd.min.js"></script>
<script src="${ctx}/static/js/blogger/edit_blog.js"></script>
<script>

    var blogId;
    var categoryId;

    <c:if test="${not empty editBlogDto}">
        $('#editormd').html("${editBlogDto.contentMd}");
        $('#blog-title').val("${editBlogDto.blogTitle}");
        var cate = document.getElementById("select-category");
        for(var i = 0;i < cate.length;i++){
            if(cate[i].value === "${editBlogDto.categoryId}") {
                cate[i].selected = true;
            }
        }
        categoryId = ${editBlogDto.categoryId};
        $('#blog-abstract').val("${editBlogDto.blogIntro}");
        blogId = ${editBlogDto.blogId};
    </c:if>

</script>
</body>
</html>
