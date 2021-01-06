// 填充剩余的区域
handleHeight('#category, #category-null');

// 表格行宽
$('.table-item').height(50);

// 个性标签
makeBloggerLabel(bloggerLabel);

// 类别请求
categoryBtnClick();

// 分页
//console.log(totalBlogs)
if (totalBlogs < 1) {
    var str = "<div class='no-blog'>暂无文章...</div>";
    $('#category-article-list').html(str)
} else if (pageIndex > totalPages) {
    var out = "<div class='no-blog'>页码超过范围...</div>"
    $('#category-pager-root').append(out)
} else {

    var options = {
        bootstrapMajorVersion: 3,
        currentPage: pageIndex,
        totalPages: totalPages,
        numberOfPages: 5,

        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "上一页";
                case "next":
                    return "下一页";
                case "last":
                    return "末页";
                case "page":
                    return page;
            }
        },
        pageUrl: function (type, page, current) {
            return baseUrl + categoryUrl + page + "?title=" + encodeURI(encodeURI(currentCategoryTitle));
        }
    };
    $("#category-pager").bootstrapPaginator(options);

    var pagesInfo = "<div class='pagesInfo'>文章数：" + totalBlogs
        + " &nbsp;| &nbsp; 共 " + totalPages + " 页" +
        "</div>";
    $('#category-pager-root').append(pagesInfo)
}

// 处理不存在的目录
//$('#category-null').html()