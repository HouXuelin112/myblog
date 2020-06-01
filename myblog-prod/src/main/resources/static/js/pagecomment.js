
layui.use(['element', 'jquery', 'form', 'layedit', 'flow', 'layer'], function () {
    var element = layui.element;
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;
    var layer = layui.layer;
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face'],
    });



    //评论和留言的编辑器的验证
    form.verify({
        content: function (value) {
            value = $.trim(layedit.getContent(editIndex));
            if (value == "") return "请输入内容";
            layedit.sync(editIndex);
        },
        replyContent: function (value) {
            if (value == "") return "请输入内容";
        }
    });

    //评论回复提交处理
    form.on("submit(formReply)", function (data) {
        let userId = $("#userid").val() || 0;
        let blogId = data.field.blogId;
        let parentId = data.field.parentId;
        let toUserId = data.field.toUserId;
        let content = data.field.replyContent;
        // console.log(data.field);
        if (userId == 0){
            $("body").append("<div class=\"layui-icon layui-icon-cry\" style=\"font-size: 20px; color: red;z-index: 10909; position: fixed;top:300px;left: 650px;\">请先登录</div>");
            // $("#loginTip").show();
            setTimeout(function () {
                $("body div").last().remove();
                // $("#loginTip").hide();
            },3000);
            return false;
        }
        $.ajax({
            url: "/reply",
            method: "post",
            data: JSON.stringify({
                blog: {
                    id: blogId
                },
                commentContent: content,
                parentId: parentId,
                toUser: {
                    id: toUserId
                }
            }),
            contentType: "application/json",
            success: function (data) {
                $("#blog-comment").load("/commentsList?blogId=" + blogId);
                $(".replycontainer").find("textarea[name=replyContent]").val("");
            },
            error: function () {
                console.log("huifuchucuo");
            }
        });
       return false;
    });

    //评论提交事件监听
    form.on("submit(formLeaveMessage)", function (data) {
        let userid = $("#userid").val() || 0;
        console.log(userid);
        // console.log(userid)
        if (userid == 0){
            $("body").append("<div class=\"layui-icon layui-icon-cry\" style=\"font-size: 20px; color: red;z-index: 10909; position: fixed;top:300px;left: 650px;\">请先登录</div>");
            // $("#loginTip").show();
            setTimeout(function () {
                $("body div").last().remove();
                // $("#loginTip").hide();
            },3000);
            return false;
        }
        let field = data.field;
        console.log(JSON.stringify(field));
        let blogId = field.blogId;
        let content = field.editorContent;
        $.ajax({
            url: "/comment",
            method: "post",
            data: JSON.stringify({
                blog: {
                    id: blogId
                },
                commentContent: content,
                parentId: 0,
                toUser:{
                    id: 2
                }
            }),
            contentType: "application/json",
            success: function (data) {
                // console.log("comments success " + data);
                $("#blog-comment").load("/commentsList?blogId=" + blogId);
                layedit.setContent(editIndex, ""); //清空
            },
            error: function () {
                console.log("评论出错");
            }
        })
        return false;
    });


     
    //回复按钮点击事件
    $('#blog-comment').on('click', '.btn-reply', function () {
        var targetId = $(this).siblings("input").last().val()
            , targetName = $(this).siblings("input").first().val(),toUserId = $(this).siblings("input").eq(1).val()
            , $container = $(this).parent('p').parent().siblings('.replycontainer');
        if ($(this).text() == '回复') {
            $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
            $container.removeClass('layui-hide');
            $container.find('input[name="parentId"]').val(targetId);
            $container.find('input[name="toUserId"]').val(toUserId);
            $(this).parents('.blog-comment li').find('.btn-reply').text('回复');
            $(this).text('收起');
        } else {
            $container.addClass('layui-hide');
            $container.find('input[name="targetUserId"]').val(0);
            $(this).text('回复');
        }
    });
});
 