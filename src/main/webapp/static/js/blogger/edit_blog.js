var editormdContainer;
var intro = "暂无";


function initEditormd() {
    editormdContainer = $(function () {
        editormd("editormd-container", {
            width: "100%",
            height: 780,
            //markdown : md,
            codeFold: true,
            syncScrolling: "single",
            //你的lib目录的路径
            path: "/static/plugin/editormd/lib/",
            imageUpload: true,
            imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp", "JPG"],
            imageUploadURL : imageUploadURL,
            /*上传图片成功后可以做一些自己的处理*/
            //onload: function () {
                //console.log("onload")
                //this.fullscreen();
                //this.unwatch();
                //this.watch().fullscreen();
                //this.width("100%");
                //this.resize("100%", 640);
            //},
            /*  theme: "dark",//工具栏主题
             previewTheme: "dark",//预览主题
             editorTheme: "pastel-on-dark",//编辑主题 */
            htmlDecode: true,   // 开启，才能在编辑器中使用 HTML 语法
            emoji: true,
            taskList: true,
            tocm: true,         // Using [TOCM]
            tex: true,                   // 开启科学公式TeX语言支持，默认关闭
            flowChart: true,             // 开启流程图支持，默认关闭
            sequenceDiagram: true,       // 开启时序/序列图支持，默认关闭,
            //这个配置在simple.html中并没有，但是为了能够提交表单，使用这个配置可以让构造出来的HTML代码直接在第二个隐藏的textarea域中，方便post提交表单。
            saveHTMLToTextarea: true,
            toolbarIcons: function () {
                // return editormd.toolbarModes['simple']; // full, simple, mini
                // Using "||" set icons align right.
                return [
                    "undo", "redo",
                    "|", "bold", "del", "italic", "quote", "ucwords", "uppercase", "lowercase",
                    "|", "h1", "h2", "h3", "h4", "h5", "h6",
                    "|", "list-ul", "list-ol", "hr",
                    "|", "link", "reference-link", "image", "code", "preformatted-text", "code-block", "table", "datetime", "emoji", "html-entities", "pagebreak",
                    "|", "watch", "preview", "clear", "search",
                    "|", "info"];
            }
        });

    });
}

$(document).ready(function () {
    initEditormd();
});

$('#save-btn').on('click', function () {

    var dateStr = getDateStr();
    //console.log(dateStr);
    var categoryId = $("#select-category").find("option:selected").attr('value');

    var blogTitle = $('#blog-title').val();
    if (blogTitle == null || typeof(blogTitle) == "undefined" || blogTitle === "") {
        fail_prompt("请输入标题");
        return;
    }

    $.ajax({
        url: baseUrl + saveBlogUrl,
        type: 'post',
        data: {
            id: blogId,
            categoryId: categoryId,
            title: blogTitle,
            content: $('#editorHtml').val(),
            contentMd: $('#editormd').val(),
            intro: intro,
            date: dateStr,
            modifyDate: dateStr
        },
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.code === 0) {
                blogId = data.data;
                success_prompt("保存成功！");
            } else {
                var message = data.msg;
                if (typeof (data.msg) === "undefined" || data.msg === "") {
                    message = "保存失败";
                }
                fail_prompt(message);
            }
        },
        error: function (data) {
            if (data.status === 200) {
                location.href = "/loginPage";
            }
        }
    })
});


$('#distribute-btn').on('click', function () {

    var abs = $('#blog-abstract').val();
    if (abs === null || typeof (abs) == "undefined" || abs === "") {

        fail_prompt("摘要不能为空");

    } else {
        var dateStr = getDateStr();
        var categoryId = $("#select-category").find("option:selected").attr('value');


        var blogTitle = $('#blog-title').val();
        if (blogTitle == null || typeof(blogTitle) == "undefined" || blogTitle === "") {
            fail_prompt("请输入标题");
            return;
        }

        intro = abs.substring(0, 150);
        var introLen = intro.length;
        var str = intro.substring(introLen-3,introLen);
        if (str !== "...") {
            intro += "...";
        }

        $.ajax({
            url: baseUrl + distributeBlogUrl,
            type: 'post',
            data: {
                id: blogId,
                categoryId: categoryId,
                title: blogTitle,
                content: $('#editorHtml').val(),
                contentMd: $('#editormd').val(),
                intro: intro,
                date: dateStr,
                modifyDate: dateStr
            },
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data.code === 0) {
                    blogId = data.data;
                    success_prompt("发布成功！");
                } else {
                    var message = data.msg;
                    if (typeof (data.msg) === "undefined" || data.msg === "") {
                        message = "发布失败";
                    }
                    fail_prompt(message);
                }
                $('#myModal').modal('hide');
            },
            error: function (data) {
                if (data.status === 200) {
                    location.href = "/loginPage";
                } else {
                    fail_prompt("发布失败");
                }
                $('#myModal').modal('hide');
            }
        })
    }
});

$('#again-btn').on('click', function () {
    window.location.href = baseUrl + editPageUrl;
});

$('#internal-btn').on('click', function () {
    var dateStr = getDateStr();
    //console.log(dateStr);
    var categoryId = $("#select-category").find("option:selected").attr('value');

    var blogTitle = $('#blog-title').val();
    if (blogTitle == null || typeof(blogTitle) == "undefined" || blogTitle === "") {
        fail_prompt("请输入标题");
        return;
    }

    $.ajax({
        url: baseUrl + internalBlogUrl,
        type: 'post',
        data: {
            id: blogId,
            categoryId: categoryId,
            title: blogTitle,
            content: $('#editorHtml').val(),
            contentMd: $('#editormd').val(),
            intro: intro,
            date: dateStr,
            modifyDate: dateStr
        },
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.code === 0) {
                blogId = data.data;
                success_prompt("保存成功！");
            } else {
                var message = data.msg;
                if (typeof (data.msg) === "undefined" || data.msg === "") {
                    message = "保存失败";
                }
                fail_prompt(message);
            }
        },
        error: function (data) {
            if (data.status === 200) {
                location.href = "/loginPage";
            }
        }
    })

});

function selectChange(obj) {
    categoryId = obj.value;
}

