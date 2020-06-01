
layui.use(['layer'], function () {
    var layer = layui.layer;
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
                    $(".loginout").after("<a href='/toLoginPage' class='blog-user login'>" +
                        "                    <i class='fa fa-user'></i><span style='font-size: 14px'>请登录</span>" +
                        "                </a>");

                    $(".loginout").remove();
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
    $(".blog-user img").hover(function () {
        var tips = layer.tips('点击退出', '.blog-user', {
            tips: [3, '#009688'],
        });
    }, function () {
        layer.closeAll('tips');
    })
};
