// 分割个性标签
function makeBloggerLabel(bloggerLabel) {
    var bloggerLabelArr = bloggerLabel.split(',');
    var bloggerLabelText = "";
    for (var i = 0; i < bloggerLabelArr.length; i++) {
        bloggerLabelText += bloggerLabelArr[i] + " | ";
    }
    $('.self-tag').text(bloggerLabelText.substring(0, bloggerLabelText.length - 2));
}


// 填充剩余的区域
function handleHeight(domId) {
    if (isPC()) {
        var articleListDom = $(domId);
        var domHeight = articleListDom.height();
        var winHeight = $(window).height();
        // console.log("domHeight:  "+domHeight);
        // console.log("winHeight:  "+winHeight);
        if (domHeight < winHeight - 100) {
            // console.log("set.....")
            //var delta = winHeight - domHeight;
            articleListDom.height(winHeight);
        }
    }

}

function isPC() {

    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
        "SymbianOS", "Windows Phone",
        "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}

function AddOrSubHeight(rootDomId, deltaHeight)  {
    var rootDom = $(rootDomId);
    rootDom.height(rootDom.height() + deltaHeight);
}


// 类别请求
function categoryBtnClick() {
    $(".category-btn").on('click', function(){
        var param = $(this).text();
        var category = param.substring(2, param.length);
        window.location.href = baseUrl + categoryUrl + 1 + "?title=" + encodeURI(encodeURI(category));

        // var params = {
        //     "url": baseUrl + categoryUrl + 1,
        //     "title": category
        //     // "pageIndex": pageIndex
        // };
        // mockFormSubmit(params)

        // $.ajax({
        //     url: baseUrl + categoryUrl,
        //     type: 'get',
        //     data: {
        //         title: category
        //     },
        //     dataType: 'json',
        //     success: function (data) {
        //         console.log(data)
        //         $('#article-list').html(categoryListTemp)
        //         handleHeight()
        //     }
        // })

    });
}

function mockFormSubmit(params) {
    var form = $('<form />', {action : params.url, method:"get", style:"display:none;"}).appendTo('body');
    $.each(params, function(k, v) {
        if ( k != "url" ){
            form.append('<input type="hidden" name="' + k +'" value="' + v +'" />');
        }
    });
    //form.append('<input type="hidden" name="csrfToken" value="' + $("#csrf_token").val() + '" />' );
    form.submit();
}
//-------------------------------------------------------//

// 获取当前日期
function getDateStr() {
    var date = new Date();
    var str = date.getFullYear() + "-";
    var mm = date.getMonth() + 1;
    if (mm > 9) {
        str += mm;
    } else {
        str += "0" + mm;
    }
    str += "-";
    if (date.getDate()>9) {
        str += date.getDate();
    } else {
        str += "0" + date.getDate();
    }
    return str;
}


// 处理简单的成果失败
function handleJsonResult(data, str) {
    if (data.code === 0) {
        blogId = data.data;
        success_prompt(str + "成功！");
    } else {
        var message = data.msg;
        if (typeof (data.msg) === "undefined" || data.msg === "") {
            message = str + "失败";
        }
        fail_prompt(message);
    }
}



// 弹出提示框
/**
 * 弹出式提示框，默认1.2秒自动消失
 * @param message 提示信息
 * @param style 提示样式，有alert-success、alert-danger、alert-warning、alert-info
 * @param time 消失时间
 */
var prompt = function (message, style, time)
{
    style = (style === undefined) ? 'alert-success' : style;
    time = (time === undefined) ? 1200 : time;
    $('<div id="promptModal">')
        .appendTo('body')
        .addClass('alert '+ style)
        .css({"display":"block",
            "z-index":99999,
            "left":($(document.body).outerWidth(true) - 120) / 2,
            "top":($(window).height() - 45) / 2,
            "position": "absolute",
            "padding": "20px",
            "border-radius": "5px"})
        .html(message)
        .show()
        .delay(time)
        .fadeOut(10,function(){
            $('#promptModal').remove();
        });
};

// 成功提示
var success_prompt = function(message, time)
{
    prompt(message, 'alert-success', time);
};

// 失败提示
var fail_prompt = function(message, time)
{
    prompt(message, 'alert-danger', time);
};

// 提醒
var warning_prompt = function(message, time)
{
    prompt(message, 'alert-warning', time);
};

// 信息提示
var info_prompt = function(message, time)
{
    prompt(message, 'alert-info', time);
};

// 信息提示
var alert_prompt = function(message, time)
{
    prompt(message, 'alert-pormpt', time);
};