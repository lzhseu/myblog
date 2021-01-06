// 个性标签
makeBloggerLabel(bloggerLabel)

// 填充剩余的区域
handleHeight('#article-list');

//分页
makePagerByLink("#article-list", "#pager-root", "#pager", baseUrl + homeBlogListPageUrl);
function makePagerByLink(listId, pagerRootId, pagerId, pageUrl) {
    if (totalBlogs < 1) {
        var str = "<div class='no-blog'>暂无文章...</div>";
        $(listId).html(str)
    } else if (pageIndex > totalPages) {
        var out = "<div class='no-blog'>页码超过范围...</div>"
        $(pagerRootId).append(out)
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
                return pageUrl + page;
            }
        };
        $(pagerId).bootstrapPaginator(options);

        var pagesInfo = "<div class='pagesInfo'>文章数：" + totalBlogs
            + " &nbsp;| &nbsp; 共 " + totalPages + " 页" +
            "</div>";
        $(pagerRootId).append(pagesInfo)
    }
}

// 类别请求
categoryBtnClick()



//--------------------------------------------------//

// 类别按钮鼠标进去出来变颜色
// $.each(blogCategory, function(i, obj) {
//     var cid = "#category" + obj.id;
//     $(cid).on('mouseenter').css("color", "#eee");
// });


// function handleBlogListItems(data) {
//
//     var resHtml;
//     if (data.code != 0) {
//         resHtml = "<div class='no-blog'>数据加载失败，请重试！</div>";
//         $('#article-list').append(resHtml).height("100%")
//     } else {
//
//         var str = "";
//         $.each(data.data, function (i, obj) {
//             console.log(data.data)
//             str += "<div class='item'>" +
//                 "<a href='#' class='title'>" + obj.title + "</a>" +
//                 "<div class='status'>发布于：" + obj.date + "　|　" + obj.nickname + "　|　" + obj.category + "</div>" +
//                 "<div class='content'>" + obj.intro + "</div>" +
//                 "</div>"
//         })
//         $('#article-list').prepend(str)
//     }
//
//
// }
