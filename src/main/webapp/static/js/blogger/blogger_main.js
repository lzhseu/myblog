// handleHeight('#blogger-main');

// 标签页
$('#myTab a').click(function (e) {
    e.preventDefault();
    var title = $(this).text().trim();

    if (title === "博文类别") {
        ajaxAllCategory(function(data){
            htmlDomCategoryList(data.data);
            $('#blogger-main').height(data.data.length * 51 + $('#myTab').height()
                + $('#create-category-btn').height() + 100);
        });
    } else {
        $('#blogger-main').height('100%')
    }

    $(this).tab('show')
});

//---------------- 个人资料 ----------------//
// 个人资料初始填充
$('#nickname').val(nickname);
$('#self-intro').val(selfIntro);
$('#self-label').val(bloggerLabel);
$('#email').val(email);

// 确认修改
$('#confirm-profile-btn').click(function () {
    $.ajax({
        url: baseUrl + updateProfileUrl,
        type: 'post',
        data: {
            id: bloggerProfileId,
            nickname: $('#nickname').val(),
            intro: $('#self-intro').val(),
            label: $('#self-label').val(),
            email: $('#email').val()
        },
        dataType: 'json',
        success: function (data) {
            //console.log(data)
            handleJsonResult(data, "修改");
        },
        error: function (data) {
            if (data.status === 200) {
                location.href = "/loginPage";
            }
        }
    })
});

//---------------- 博文类别 ----------------//
// 获取博文类别
function ajaxCategory(title) {
    title = encodeURI(title)
    $.ajax({
        url: baseUrl + getCategoryByTitleUrl,
        type: 'get',
        data: {
            title: title
        },
        dataType: 'json',
        success: function (data) {
            //console.log(data);
            if (data.code === 0) {
                if (data.data != null) {
                    $('#category-edit-intro').val(data.data.desc);
                    $('#hidden-category-edit-id').val(data.data.id);
                }
            }
        },
        error: function (data) {
            if (data.status === 200) {
                location.href = "/loginPage";
            }
        }
    })
}




// 获取当前所有的类别
function ajaxAllCategory(callback) {
    $.ajax({
        url: baseUrl + getAllCategoryUrl,
        type: 'get',
        data: {
        },
        dataType: 'json',
        success: function (data) {
            //console.log(data)
            callback(data);
        },
        error: function (data) {
            if (data.status === 200) {
                location.href = "/loginPage";
            }
        }
    })
}

function htmlDomCategoryList(data) {

    var str = '';
    if (data.length === 0)  {
        str = "<div class='no-blog'>暂无类别...</div>";
    } else {
        str = '<table class="table" id="category-list-table">';
        $.each(data, function (i, obj) {

            str += '<tr class="table-item">' +
                '<td style="vertical-align:middle;"><div class="title">' + obj.title +  '</div></td>' +
                '<td style="vertical-align:middle;"><div class="edit"><button type="button" class="btn btn-link edit-blog-btn" onclick="editCategoryBtnClick(this)" value=' + obj.id + '>' +
                '编辑</button></div></td>' +
                '<td style="vertical-align:middle;"><div class="delete"><button type="button" class="btn btn-link delete-blog-btn" onclick="deleteCategoryBtnClick(this)" value=' + obj.id + '>' +
                '删除</button></div></td>' +
                '</tr>';
        });
        str += '</table>';
        // str += '<div id ="category-pager-root" style="text-align:center">' +
        //     '<ul id="category-pager"></ul>' +
        //     '</div>';
    }
    $('#category-list-panel').html(str);
}


// 新增或修改的 ajax 请求
function ajaxInsertAndUpdateCategory(categoryId, title, desc, modalId, mode) {
    $.ajax({
        url: baseUrl + updateCategoryUrl,
        type: 'post',
        data: {
            id: categoryId,
            title: title,
            desc: desc
        },
        dataType: 'json',
        success: function (data) {
            var obj = data.data;
            console.log(data);
            $(modalId).modal('hide');
            if (mode === 'create') {
                
                if (data.code === 42) {
                    fail_prompt("该类别已存在");
                } else if (data.code === 0) {
                    var str = '<tr class="table-item">' +
                        '<td style="vertical-align:middle;"><div class="title">' + obj.title +  '</div></td>' +
                        '<td style="vertical-align:middle;"><div class="edit"><button type="button" class="btn btn-link edit-blog-btn" onclick="editCategoryBtnClick(this)" value=' + obj.id + '>' +
                        '编辑</button></div></td>' +
                        '<td style="vertical-align:middle;"><div class="delete"><button type="button" class="btn btn-link delete-blog-btn" onclick="deleteCategoryBtnClick(this)" value=' + obj.id + '>' +
                        '删除</button></div></td>' +
                        '</tr>';
                    $('#category-list-table').append(str);
                    AddOrSubHeight('#blogger-main',51);
                    success_prompt("添加成功");
                }

            } else {
                handleJsonResult(data, "操作");
            }

        },
        error: function (data) {
            if (data.status === 200) {
                location.href = "/loginPage";
            }
        }
    })
}


// 编辑类别
function editCategoryBtnClick(obj) {
    var title = $(obj).parents().eq(2).find('.title').text();

    ajaxCategory(title);

    $('#category-edit-title').text(title);

    $('#category-edit-modal').modal("show");
}

// 编辑
$('#submit-category-edit-btn').click(function () {
    var title = $('#category-edit-title').text();
    var desc = $('#category-edit-intro').val();
    var categoryId = $('#hidden-category-edit-id').val();

    ajaxInsertAndUpdateCategory(categoryId, title, desc, '#category-edit-modal', 'edit')

});

// 新增类别
$('#create-category-btn').click(function () {

    $('#category-create-modal').modal("show");
});

// 提交新增类别
$('#submit-category-create-btn').click(function () {
    var title = $('#category-create-title').val();
    var desc = $('#category-create-intro').val();

    if (typeof (title) ==="undefined" || title === null || title.trim() === ""){
        fail_prompt("类别名称不能为空！");
    } else {
        ajaxInsertAndUpdateCategory(undefined, title, desc, '#category-create-modal', 'create');
    }

});


// 删除类别
function deleteCategoryBtnClick(obj) {
    if (confirm("删除类别将同时删除该类别下所有文章，您是否要继续？")){
        // console.log(categoryId)
        var categoryId = obj.value;
        $.ajax({
            url: baseUrl + deleteCategoryUrl,
            type: 'post',
            data: {
                id: categoryId
            },
            dataType: 'json',
            success: function (data) {
                if (data.code === 0) {
                    if (data.data === "delete ok") {
                        $(obj).parents().eq(2).remove();  //移除该行
                        AddOrSubHeight('#blogger-main', -50);
                        success_prompt("删除成功");
                    } else {
                        var str = "该类别下还有" + data.data + "篇文章，确认删除？";
                        if (confirm(str)) {
                            confirmDeleteCategoryAndBlogs(data.data, categoryId, obj)
                        } else {
                            info_prompt("取消删除");
                        }
                    }
                }
            },
            error: function (data) {
                if (data.status === 200) {
                    location.href = "/loginPage";
                }
            }
        })
    }
}

function confirmDeleteCategoryAndBlogs(count, categoryId, obj) {
    $.ajax({
        url: baseUrl + confirmDeleteCategoryAndBlogsUrl,
        type: 'post',
        data: {
            id: categoryId,
            count: count
        },
        dataType: 'json',
        success: function (data) {
            $(obj).parents().eq(2).remove();
            AddOrSubHeight('#blogger-main', -50);
            handleJsonResult(data, "删除");
        }
    })
}

//---------------- 所有博文 ----------------//

function allBlogTagClick() {

    var str = '';

    ajaxAllCategory(function (data) {
        //console.log(data);
        var arr = data.data;
        $.each(arr, function (i, obj) {
            str += '<li><a href="#blog-list" tabindex="-1" data-toggle="tab" onclick="getBlogs(this)" title = "' + obj.title + '">' + obj.title + '</a></li>';
        });

        $('#all-blog-dropdown').html(str);
    })
}

function getBlogs(obj) {
    var title = encodeURI(obj.title);
    ajaxBlogList(title, 1, 10);
    $('#blogger-main').height('120%')
}


// 获取博文列表
function ajaxBlogList(title, pageIndex, pageSize) {
    $.ajax({
        url: baseUrl + getBlogListAllByCategoryTitleUrl,
        type: 'get',
        data: {
            title: title,
            pageIndex: pageIndex,
            pageSize: pageSize
        },
        dataType: 'json',
        success: function (data) {
            if (data.code === 0) {
                if (data.data.blogCount > 0) {
                    // 填充表格
                    htmlDomBlogList(data.data.blogListItemDtos);
                    pageManager(title, data.data.blogCount, pageIndex, data.data.totalPages, '#blog-pager-root', '#blog-pager');
                } else {
                    // 暂无
                    var str = "<div class='no-blog'>暂无文章...</div>";
                    $('#blog-list').html(str)
                }
            } else {
                handleJsonResult(data, "获取数据");
            }
        },
        error: function (data) {
            if (data.status === 200) {
                location.href = "/loginPage";
            }
        }
    })
}

function htmlDomBlogList(data) {
        var str = '<table class="table" id="blog-list-table">';
        $.each(data, function (i, obj) {

            var stateDom = '';
            if (obj.state === 0) {
                stateDom += '<td style="vertical-align:middle;"><div class="save-state">草稿</div></td>';
            } else if (obj.state === 1) {
                stateDom += '<td style="vertical-align:middle;"><div class="private-state">私密</div></td>';
            } else if (obj.state === 2) {
                stateDom += '<td style="vertical-align:middle;"><div class="public-state">公开</div></td>';
            } else if (obj.state === 3) {
                stateDom += '<td style="vertical-align:middle;"><div class="internal-state">内部</div></td>';
            } else {
                stateDom += '<td style="vertical-align:middle;"><div class="unknown-state">未知</div></td>';
            }

            str += '<tr class="table-item">' +
                '<td style="vertical-align:middle;">' + '<div class="title">' + obj.title + '</div></td>' +
                stateDom +
                '<td style="vertical-align:middle;"><div class="status">' + obj.date + '|' + obj.nickname +  '</div></td>' +
                '<td style="vertical-align:middle;"><div class="edit"><button type="button" class="btn btn-link edit-blog-btn" onclick="editBtnClick(this)" value=' + obj.id + '>' +
                '编辑</button></div></td>' +
                '<td style="vertical-align:middle;"><div class="delete"><button type="button" class="btn btn-link delete-blog-btn" onclick="deleteBlogBtnClick(this)" value=' + obj.id + '>' +
                '删除</button></div></td>' +
                '</tr>';
        });
    str += '</table>';
    str += '<div id ="blog-pager-root" style="text-align:center">' +
        '<ul id="blog-pager"></ul>' +
        '</div>';

    $('#blog-list').html(str);

}

// 分页展示博文
function pageManager(title, totalBlogs, pageIndex, totalPages, pagerRootId, pagerId) {

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
        onPageClicked: function (event, originalEvent, type, page) {
            if (pagerId === '#blog-pager') {
                ajaxBlogList(title, page, 10);
            } else if (pagerId === '#category-pager'){
                //ajaxAllCategory(title, page, 10);
            }

        }
    };
    $(pagerId).bootstrapPaginator(options);

    var pagesInfo = "<div class='pagesInfo'>记录数：" + totalBlogs
        + " &nbsp;| &nbsp; 共 " + totalPages + " 页" +
        "</div>";
    $(pagerRootId).append(pagesInfo)

}

// 编辑博文按钮
function editBtnClick(obj) {

    var blogId = obj.value;
    window.open(baseUrl + editPageUrl + "?blogId=" + blogId);
}

// 删除博文按钮
function deleteBlogBtnClick(obj) {
    var blogId = obj.value;

    if (confirm('确定删除？')) {
        $.ajax({
            url: baseUrl + deleteBlogByIdUrl,
            type: 'get',
            data: {
                id: blogId
            },
            dataType: 'json',
            success: function (data) {
                $(obj).parents().eq(2).remove();  //移除该行
                handleJsonResult(data, "删除博文");
            },
            error: function (data) {
                if (data.status === 200) {
                    location.href = "/loginPage";
                }
            }
        })
    }
}



//---------------- 创建博文 ----------------//

$('#create-blog-btn').click(function (){
    window.open(baseUrl + editPageUrl);
});

