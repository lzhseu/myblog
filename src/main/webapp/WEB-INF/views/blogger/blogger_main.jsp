<%--
  Created by IntelliJ IDEA.
  User: 卢卓桓
  Date: 2020/5/27
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="author" content="lzh, lzhseu@163.com">
    <link href="https://cdn.bootcdn.net/ajax/libs/normalize/8.0.1/normalize.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx}/static/css/blogger/blogger_main.css">
    <link rel="stylesheet" href="${ctx}/static/css/blog/category.css">
    <link rel="stylesheet" href="${ctx}/static/css/blog/main.css">
    <script src="${ctx}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/bootstrap-paginator.js"></script>
    <script src="${ctx}/static/js/constant/url-constant.js"></script>
    <script src="${ctx}/static/js/common/common.js"></script>
    <title>${bloggerProfile.nickname} - 管理台</title>
</head>
<body>
    <div class="col-md-2 side-bar">
        <div class="header">
            <a href="#" class="logo">${bloggerProfile.nickname}</a>
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

    <div class="col-md-7">
        <div class="blogger-main" id="blogger-main">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active">
                    <a href="#profile" data-toggle="tab">个人资料</a>
                </li>
                <li>
                    <a href="#ope-category" data-toggle="tab">博文类别</a>
                </li>
<%--                <li class="dropdown">--%>
<%--                    <a href="#" id="categoryTabDrop" class="dropdown-toggle"--%>
<%--                       data-toggle="dropdown">博文类别 <b class="caret"></b>--%>
<%--                    </a>--%>
<%--                    <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1"  id="category-select-panel">--%>
<%--                        <li>--%>
<%--                            <a href="#ope-category" tabindex="-1" data-toggle="tab">--%>
<%--                                新增类别--%>
<%--                            </a>--%>
<%--                        </li>--%>

<%--                        <c:forEach var="item" items="${blogCategory}">--%>
<%--                            <li>--%>
<%--                                <a href="#ope-category" tabindex="-1" data-toggle="tab" >--%>
<%--                                    ${item.title}--%>
<%--                                </a>--%>
<%--                            </li>--%>
<%--                        </c:forEach>--%>

<%--                    </ul>--%>
<%--                </li>--%>
                <li class="dropdown">
                    <a id="blogsTabDrop" class="dropdown-toggle"
                       data-toggle="dropdown" onclick="allBlogTagClick()">所有博文 <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="myTabDrop1" id="all-blog-dropdown">
<%--                        <c:forEach var="item" items="${blogCategory}">--%>
<%--                            <li>--%>
<%--                                <a href="#blog-list" tabindex="-1" data-toggle="tab">--%>
<%--                                        ${item.title}--%>
<%--                                </a>--%>
<%--                            </li>--%>
<%--                        </c:forEach>--%>
                    </ul>
                </li>
                <li><a href="#others" data-toggle="tab">其他</a></li>
            </ul>

            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="profile">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <span>网站昵称：</span>
                            <input type="text" class="form-control" id="nickname">
                        </div>
                        <div class="form-group">
                            <span>个性签名：</span>
                            <input type="text" class="form-control" id="self-intro">
                        </div>
                        <div class="form-group">
                            <span>自我标签：</span>
                            <input type="text" class="form-control" id="self-label">
                        </div>
                        <div class="form-group">
                            <span>邮箱：</span>
                            <input type="text" class="form-control" id="email">
                        </div>
                        <div class="form-group confirm-profile-btn">
                            <button type="button" class="btn btn-default" id="confirm-profile-btn">确定修改</button>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="ope-category">
                    <div id="category-list-panel"></div>
                    <button type="button" class="btn btn-default" id="create-category-btn">新增类别</button>
                </div>
                <div class="tab-pane fade" id="blog-list">
                </div>
                <div class="tab-pane fade" id="others">
                    <p>暂无</p>
                </div>
            </div>

        </div>
        <div class="footer">
            Powered by Lu Zhuohuan
        </div>
    </div>

    <div class="col-md-3 right-bar">
        <!-- <div class="search-bar">搜索框</div> -->
        <div class="other-func">
            <button type="button" class="btn btn-link create-blog-btn" id="create-blog-btn">创建博文</button>
        </div>

    </div>


    <div class="modal fade" id="category-edit-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="category-edit-modalLabel">编辑类别</h4>
                </div>
                <div class="modal-body">
                    <h3 id="category-edit-title"></h3>
                    <input id="hidden-category-edit-id" type="hidden">
                    <div class="form-group">
                        <span>类别描述：</span>
                        <textarea id="category-edit-intro" cols="30" rows="5" class="form-control"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="submit-category-edit-btn">提交</button>
                </div>
            </div>
        </div>
    </div>


    <div class="modal fade" id="category-create-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="category-create-modalLabel">新增类别</h4>
                </div>
                <div class="modal-body">
                    <span>类别名称：</span>
                    <input type="text" class="form-control" id="category-create-title"/>
                    <input id="hidden-category-create-id" type="hidden">
                    <div class="form-group">
                        <span>类别描述：</span>
                        <textarea id="category-create-intro" cols="30" rows="5" class="form-control"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" id="submit-category-create-btn">提交</button>
                </div>
            </div>
        </div>
    </div>



    <script type="application/javascript">

        var nickname;
        var selfIntro;
        var email;
        var bloggerProfileId;
        var bloggerLabel;

        <c:if test="${not empty bloggerProfile}">
            nickname = "${bloggerProfile.nickname}";
            selfIntro = "${bloggerProfile.intro}";
            email = "${bloggerProfile.email}";
            bloggerProfileId = ${bloggerProfile.id};
            bloggerLabel = "${bloggerProfile.label}";
            // 个性标签
            makeBloggerLabel(bloggerLabel);
        </c:if>




    </script>
    <script src="${ctx}/static/js/blogger/blogger_main.js"></script>
</body>
</html>
