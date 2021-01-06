$('#login-btn').on('click', function () {

        var email = $('#inputEmail').val();
        var password = $('#inputPassword').val();

        $.ajax({
            url: baseUrl + loginUrl,
            type: 'post',
            data: {
                email: email,
                password: password
            },
            dataType: 'json',
            success: function (data) {
                //console.log(data);
                if (data.code === 0) {
                    location.href = baseUrl + bloggerMainPageUrl;
                } else {
                    fail_prompt("邮箱或密码错误！")
                }
            }
        });
});