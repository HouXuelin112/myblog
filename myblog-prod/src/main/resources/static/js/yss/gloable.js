

$("#out").bind("mouseenter", function (e) {
    var operation_dialog = $(".operation_dialog");
    console.log(e.pageX, e.pageY);
    let x = e.pageX > 1300 ? 1300 : e.pageX;
    let y = e.pageY;
    operation_dialog.css({
        "top":y + "px",
        "left": x - 150 + "px"
    })
    operation_dialog.slideDown();
});
$(document).bind("mousemove", function (e) {
    if(!$(".operation_dialog").is(":visible")){
        return;
    }
    let elem = e.target;
    while (elem){
        if(elem.className && (elem.className === "operation_dialog" || elem.id === "out")){
            return;
        }
        elem = elem.parentElement;
    }
    $(".operation_dialog").slideUp();
});
var displayImg = $("#displayImg");

$(document).click(function(e){
    if(!displayImg.is(":visible")){
        return;
    }
    var elem = e.target;
    while(elem){
        if(elem.id && elem.id === "displayImg"){
            return;
        }
        elem = elem.parentElement;
    }
    displayImg.hide();
})

$("img").dblclick(function(){
    var winWidth = document.documentElement.clientWidth;
    var winHeight = document.documentElement.clientHeight;
    console.log(winHeight);
    var top = (winHeight - 500) / 2;
    var left = (winWidth - 600) / 2;
    var img = $(this).clone();
    displayImg.html(img);
    displayImg.animate({
        width: "600px",
        height: "500px"
    }).css({
        position: "fixed",
        top: top + "px",
        left: left + "px",
    }).show();
})
layui.use(['layer'], function () {
    var layer = layui.layer;
    var active = {
        notice: function(){
            var html = $(".personal_info_container").html();
            //示范一个公告层
            layer.open({
                type: 1
                ,title: "个人信息" //不显示标题栏
                ,area: ['500px', '600px']
                ,shade: 0.8
                ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                ,btnAlign: 'c'
                ,btn: ['保存修改']
                ,moveType: 1 //拖拽模式，0或者1
                ,content: html
                ,success: function () {
                    console.log("log");
                }
                ,yes: function () {
                    var old_password = $("#LAY_layuipro .p_old_password");
                    var old_password_td = old_password.parent("td");
                    var new_password = $("#LAY_layuipro .p_new_password");
                    var new_password_confirm = $("#LAY_layuipro .p_confirm_new_password");
                    var new_password_td = new_password.parent("td");
                    var new_password_confirm_td = new_password_confirm.parent("td");
                    var phone = $("#LAY_layuipro .p_phone");
                    var phone_td = phone.parent("td");
                    var email = $("#LAY_layuipro .p_email");
                    var email_td = email.parent("td");
                    var age = $("#LAY_layuipro .p_age");
                    var birthday = $("#LAY_layuipro .p_birthday");
                    var birthday_td = birthday.parent("td");
                    var nickName = $("#LAY_layuipro .p_nickname");
                    var headBase64 = $("#LAY_layuipro .headBase64");
                    var id = getInput("p_id");

                    // console.log(headBase64.val());
                    if (!checkEmail(email.val()) || !checkPhone(phone.val()) || !checkBirthday() || !checkConfirmPassword()){
                        return;
                    }
                    $.post({
                        url: "/personal/updateUser",
                        data: JSON.stringify({
                            id: id,
                            nickName: nickName.val(),
                            password: new_password.val(),
                            phone: phone.val(),
                            email: email.val(),
                            birthday: birthday.val(),
                            headBase64: headBase64.val(),
                        }),
                        contentType: "application/json;charset=UTF-8",
                        success: function (data) {
                            if (data === "ok"){
                                alert("修改成功");
                                window.location.reload();
                            }else {
                                alert("出错了");
                            }

                        },
                        error: function () {
                            console.log("服务器请求错误！");
                        }
                    });

                    function checkBirthday(){
                        var date = new Date();
                        var date1 = new Date(birthday.val());
                        if (date < date1){
                            myAlert(birthday_td);
                            return false;
                        }
                        cancelAlert(birthday_td);
                        return true;
                    }

                    function checkEmail(str) {
                        var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
                        if (!reg.test(str)){
                            myAlert(email_td);
                            return false;
                        }
                        cancelAlert(email_td);
                        return  true;
                    }

                    function checkPhone(phone){
                        if(!(/^1[3456789]\d{9}$/.test(phone))){
                            myAlert(phone_td);
                            return false;
                        }
                        cancelAlert(phone_td);
                        return true;
                    }
                    function myAlert(elem) {
                        elem.css("box-shadow", "0 0 3px 1px red inset");
                    }
                    function cancelAlert(elem) {
                        elem.css("box-shadow", "none");
                    }

                    //检查两次输入密码是否一致
                    function checkConfirmPassword(){
                        var newPwd = new_password.val();
                        var newPwdConfirm = new_password_confirm.val();
                        if (newPwd !== newPwdConfirm){
                            new_password_td.css("box-shadow", "0 0 3px 1px red inset");
                            new_password_confirm_td.css("box-shadow", "0 0 3px 1px red inset");
                            return false;
                        }else {
                            new_password_td.css("box-shadow", "none");
                            new_password_confirm_td.css("box-shadow", "none");
                            return true;
                        }
                    }

                    function passwordReadonly(){
                        new_password.attr({"readonly":"readonly", "hidden":"hidden"});
                        new_password_confirm.attr({"readonly":"readonly", "hidden":"hidden"});
                        new_password_confirm_td.addClass("my_read_only");
                        new_password_td.addClass("my_read_only");
                    }
                    function passwordEditable(){
                        new_password.removeAttr("readonly");
                        new_password_confirm.removeAttr("readonly");
                        new_password.removeAttr("hidden");
                        new_password_confirm.removeAttr("hidden");
                        new_password_confirm_td.removeClass("my_read_only");
                        new_password_td.removeClass("my_read_only");
                    }
                }
            });
        }
    };

    function getInput(className){
        return $("#LAY_layuipro ." + className).val();
    }

    $('.toPersonalInfo').on('click', function(){
        var othis = $(this), method = othis.data('method');
        active[method] ? active[method].call(this, othis) : '';
        var old_password = $("#LAY_layuipro .p_old_password");
        var old_password_td = old_password.parent("td");
        var new_password = $("#LAY_layuipro .p_new_password");
        var new_password_confirm = $("#LAY_layuipro .p_confirm_new_password");
        var new_password_td = new_password.parent("td");
        var new_password_confirm_td = new_password_confirm.parent("td");
        var phone = $("#LAY_layuipro .p_phone");
        var phone_td = phone.parent("td");
        var email = $("#LAY_layuipro .p_email");
        var email_td = email.parent("td");
        var age = $("#LAY_layuipro .p_age");
        var birthday = $("#LAY_layuipro .p_birthday");
        var birthday_td = birthday.parent("td");

        function checkBirthday(){
            var date = new Date();
            var date1 = new Date(birthday.val());
            if (date < date1){
                myAlert(birthday_td);
                return false;
            }
            cancelAlert(birthday_td);
            return true;
        }

        $("#LAY_layuipro .headC").click(function () {
            $("#LAY_layuipro .file").click();
        });
        $("#LAY_layuipro .file").on("change", function (e) {
            var file = e.target.files[0];
            $("#LAY_layuipro .headC").val(file.name);
            var reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = function (ev) {
                var result = ev.target.result;
                $("#LAY_layuipro .headImg").attr("src", result);
                var base64 = result.substring(result.indexOf(",") + 1);
                $("#LAY_layuipro .headBase64").val(base64);
            }
        });

        birthday.on("change", function () {
            if (!checkBirthday()){
                age.text("0");
                return;
            }

            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var day = date.getDate();

            var birthYear = birthday.val().substring(0, 4);
            var birthMonth = birthday.val().substring(5, 7);
            var birthDay = birthday.val().substring(8, 10);
            var ageV = year - parseInt(birthYear);
            if (month === parseInt(birthMonth) && day < parseInt(birthDay)){
                ageV -= 1;
            }
            if (month < birthMonth){
                ageV -= 1;
            }
            age.text(ageV);
        });

        email.on("keyup", function () {
            console.log("email");
            var emailV = email.val();
            checkEmail(emailV);
        });

        function checkEmail(str) {
            var reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
            if (!reg.test(str)){
                myAlert(email_td);
                return false;
            }
            cancelAlert(email_td);
            return  true;
        }

        phone.on("keyup", function () {
            var phoneV = phone.val();
            checkPhone(phoneV);
        });
        function checkPhone(phone){
            if(!(/^1[3456789]\d{9}$/.test(phone))){
                myAlert(phone_td);
                return false;
            }
            cancelAlert(phone_td);
            return true;
        }
        function myAlert(elem) {
            elem.css("box-shadow", "0 0 3px 1px red inset");
        }
        function cancelAlert(elem) {
            elem.css("box-shadow", "none");
        }

        //检查两次输入密码是否一致
        function checkConfirmPassword(){
            var newPwd = new_password.val();
            var newPwdConfirm = new_password_confirm.val();
            if (newPwd !== newPwdConfirm){
                new_password_td.css("box-shadow", "0 0 3px 1px red inset");
                new_password_confirm_td.css("box-shadow", "0 0 3px 1px red inset");
                return false;
            }else {
                new_password_td.css("box-shadow", "none");
                new_password_confirm_td.css("box-shadow", "none");
                return true;
            }
        }
        new_password.on("keyup", checkConfirmPassword);
        new_password_confirm.keyup(checkConfirmPassword);

        function passwordReadonly(){
            new_password.attr({"readonly":"readonly", "hidden":"hidden"});
            new_password_confirm.attr({"readonly":"readonly", "hidden":"hidden"});
            new_password_confirm_td.addClass("my_read_only");
            new_password_td.addClass("my_read_only");
        }
        function passwordEditable(){
            new_password.removeAttr("readonly");
            new_password_confirm.removeAttr("readonly");
            new_password.removeAttr("hidden");
            new_password_confirm.removeAttr("hidden");
            new_password_confirm_td.removeClass("my_read_only");
            new_password_td.removeClass("my_read_only");
        }
        old_password.on("keyup", function () {
            var value = $(this).val();
            //如果密码为空，新密码输入框不可编辑
            if (value === ''){
                passwordReadonly();
                old_password_td.css("box-shadow", "none");
                return;
            }
            //验证输入的旧密码是否正确
            $.post({
                url: "/personal/checkPassword",
                data: {
                    value: value,
                },
                success: function (data) {
                    if (data === "noUser"){
                        window.location.reload();
                    }
                    if (data === 'ok'){
                        passwordEditable();
                        old_password_td.css("box-shadow", "none");
                    }else {
                        passwordReadonly();
                        old_password_td.css("box-shadow", "0 0 3px 1px red inset");
                    }
                },
                error: function () {
                    console.log("验证密码出错");
                }
            })
        });
    });

    $(".loginout").click(function () {
        layer.confirm("确认注销登录？注销之后将无法继续留言或评论",{
            btn: ['Yes', 'No'],
            time: 20000
        },function (index) {
            console.log("yes");
            $.get({
                url: "/logOut",
                success: function () {
                    $("#userid").first().val("0");
                    $(".out").after("<a href='/toLoginPage' class='blog-user login'>" +
                        "                    <i class='fa fa-user'></i><span style='font-size: 14px'>请登录</span>" +
                        "                </a>");

                    $(".operation_dialog").remove();
                    $(".out").remove();
                    console.log("success loginOut");
                    layer.close(index);
                },
                error: function () {
                    console.log("error logOut");
                }
            });

        },function () {
            console.log("no");
        });
    });
});
layui.use(['jquery', 'layer', 'util'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        util = layui.util;
    util.fixbar();
    //导航控制
    master.start($);
});
var slider = 0;
var pathname = window.location.pathname.replace('Read', 'Article');
var master = {};
master.start = function ($) {
    $('#nav li').hover(function () {
        $(this).addClass('current');
    }, function () {
        var href = $(this).find('a').attr("href");
        if (pathname.indexOf(href) == -1) {
            $(this).removeClass('current');
        }
    });
    selectNav();
    function selectNav() {
        var navobjs = $("#nav a");
        $.each(navobjs, function () {
            var href = $(this).attr("href");
            if (pathname.indexOf(href) != -1) {
                $(this).parent().addClass('current');
            }
        });
    };
    $('.phone-menu').on('click', function () {
        $('#nav').toggle(500);
    });
    $(".loginout").hover(function () {
        var tips = layer.tips('点击退出', '.loginout', {
            tips: [3, '#009688'],
        });
    }, function () {
        layer.closeAll('tips');
    })
};
