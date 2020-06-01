var area;
layui.use(['element', 'jquery', 'form', 'layedit', 'flow'], function () {
    var element = layui.element;
    var form = layui.form;
    var $ = layui.jquery;
    var layedit = layui.layedit;
    var flow = layui.flow;
    //留言的编辑器
    var editIndex = layedit.build('remarkEditor', {
        height: 150,
        tool: ['face'],
    });

    form.on("submit(formReply)", function (data) {
        var userId = $("#userid").val() || 0;
        if (userId == 0){
            $("body").append("<div class='layui-icon layui-icon-cry' style='font-size: 20px; color: red;z-index: 10909; position: fixed;top:300px;left: 650px;'>请先登录</div>");
            // $("#loginTip").show();
            setTimeout(function () {
                $("body div").last().remove();
                // $("#loginTip").hide();
            },3000);
            return false;
        }
        let content = data.field.replyContent;
        let parentId = data.field.parentId;
        let targetId = data.field.targetId;
        let toUser = data.field.toUser;
        let headSrc = $(".loginout img").attr("src");
        let username = $(".loginout .username").text();
        let date = new Date();
        let commentTime = date.getFullYear() + "-" + (date.getMonth() + 1 > 9 ? date.getMonth() + 1:("0" + date.getMonth() + 1)) + "-" + (date.getDate() > 9 ? date.getDate():("0" + date.getDate())) + " " + (date.getHours() > 9 ? date.getHours():("0" + date.getHours()))  +":" + (date.getMinutes() > 9 ?date.getMinutes():('0' + date.getMinutes())) + ":" + (date.getSeconds() > 9 ? date.getSeconds():("0" + date.getSeconds()));
        console.log(commentTime, headSrc, username);

        myReply =" <hr />\n" +
            "                                <div class='comment-child add'>\n" +
            "                                    <a name='reply-1'></a>\n" +
            "                                    <img src='" + headSrc + "'>\n" +
            "                                    <div class='info'>\n" +
            "                                        <span class='username'>"+ username +"</span>\n" +
            "                                        <span style='padding-right:0;margin-left:-5px;'>回复</span>\n" +
            "                                        <span class='username'>"+ toUser +"</span>\n" +
            "                                        <span>"+ content +"</span>\n" +
            "                                    </div>\n" +
            "                                    <p class='info'>\n" +
            "                                        <i class='fa fa-map-marker' aria-hidden='true'></i>\n" +
            "                                        <span class='comment-time'>"+ commentTime +"</span>\n" +
            "                                        <a href='javascript:;' class='btn-reply' data-targetid='2' data-targetname='hxl'>回复</a>\n" +
            "                                        <input hidden value='" + userId+"' name='targetId'>\n" +
            "                                        <input hidden value='"+ username +"' name='nickName'>\n" +
            "                                        <input hidden value='"+ parentId +"' name='parentId'>\n" +
            "                                    </p>\n" +
            "                                </div>"

            $(this).parents(".replycontainer").before(myReply);
        $.post({
           url: "/replyMessage",
           data: JSON.stringify({
               user: {
                   id: userId,
               },
               messageContent: content,
               parentId: parentId,
               toUser: {
                   id: targetId
               }
           }),
            contentType: "application/json",
            success: function (data) {
                console.log(data);
                $(".replycontainer").find("textarea[name=replyContent]").val("");
            },
            error: function () {
                console.log("huifuchucuo");
            }
        });

        return false;
    })

    form.on("submit(formLeaveMessage)", function (data) {
        var userId = $("#userid").val() || 0;
        if (userId == 0){
            $("body").append("<div class='layui-icon layui-icon-cry' style='font-size: 20px; color: red;z-index: 10909; position: fixed;top:300px;left: 650px;'>请先登录</div>");
            // $("#loginTip").show();
            setTimeout(function () {
                $("body div").last().remove();
                // $("#loginTip").hide();
            },3000);
            return false;
        }
        let content = data.field.editorContent;
        let headSrc = $(".loginout img").attr("src");
        let username = $(".loginout .username").text();
        let date = new Date();
        let commentTime = date.getFullYear() + "-" + (date.getMonth() + 1 > 9 ? date.getMonth() + 1:("0" + date.getMonth() + 1)) + "-" + (date.getDate() > 9 ? date.getDate():("0" + date.getDate())) + " " + (date.getHours() > 9 ? date.getHours():("0" + date.getHours()))  +":" + (date.getMinutes() > 9 ?date.getMinutes():('0' + date.getMinutes())) + ":" + (date.getSeconds() > 9 ? date.getSeconds():("0" + date.getSeconds()));
        console.log(commentTime, headSrc, username);


        let messageLi = "<li class='zoomIn article'>\n" +
            "                    <div class='comment-parent'>\n" +
            "                        <a name='remark-1'></a>\n" +
            "                        <img src='"+ headSrc +"' />\n" +
            "                        <div class='info'>\n" +
            "                            <span class='username'>" + username +"</span>\n" +
            "                        </div>\n" +
            "                        <div class='comment-content'>\n" +
                                        content +
            "                        </div>\n" +
            "                        <p class='info info-footer add'>\n" +
            "                            <i class='fa fa-map-marker' aria-hidden='true'></i>\n" +
            "                            <span class='comment-time'>"+ commentTime +"</span>\n" +
            "                            <a href='javascript:;' class='btn-reply' data-targetid='1' data-targetname='fff'>回复</a>\n" +
            "                            <input hidden value='"+  userId +"' name='targetId'>\n" +
            "                            <input hidden value='"+ username +"' name='nickName'>\n" +
            "                            <input hidden value='' name='parentId'>" +
            "                        </p>\n" +
            "                    </div>" +
            "<div class='replycontainer layui-hide'>\n" +
            "                        <form class='layui-form' action=''>\n" +
            "                            <input type='hidden' name='parentId' value='1'>\n" +
            "                            <input type='hidden' name='targetId' value='0'>\n" +
            "<input hidden name='toUser' value='dd'>"+
            "                            <div class='layui-form-item'>\n" +
            "                                <textarea name='replyContent' lay-verify='replyContent' placeholder='请输入回复内容' class='layui-textarea' style='min-height:80px;'></textarea>\n" +
            "                            </div>\n" +
            "                            <div class='layui-form-item'>\n" +
            "                                <button class='layui-btn layui-btn-xs' lay-submit='formReply' lay-filter='formReply'>提交</button>\n" +
            "                            </div>\n" +
            "                        </form>\n" +
            "                    </div>\n" +
            "                </li>"
        $("#message-list").prepend(messageLi);

        $.post({
            url: "/leaveMessage",
            data: JSON.stringify({
                user: {
                   id: userId
                },
                messageContent: content,
                parentId: 0,
                toUser: {
                    id: 2
                }
            }),
            contentType: "application/json",
            success: function (data) {
                // console.log("comments success " + data);
                // $("#blog-comment").load("/commentsList?blogId=" + blogId);
                console.log(data);
                $(".add input[name='parentId']").val(data);
                $(".add").removeClass("add");
                layedit.setContent(editIndex, ""); //清空
            },
            error: function () {
                console.log("留言出错");
            }
        })
        return false;
    });



    //留言的编辑器的验证
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
    //回复按钮点击事件
    $('#message-list').on('click', '.btn-reply', function () {
         var parentId = $(this).siblings("input[name='parentId']").val()
             , targetId = $(this).siblings("input[name='targetId']").val()
             , targetName = $(this).siblings("input[name='nickName']").val()
             , $container = $(this).parent('p').parent().siblings('.replycontainer');
         console.log(parentId, targetId, targetName);
         if ($(this).text() == '回复') {
             $container.find('textarea').attr('placeholder', '回复【' + targetName + '】');
             $container.removeClass('layui-hide');
             $container.find('input[name="targetId"]').val(targetId);
             $container.find('input[name="parentId"]').val(parentId);
             $container.find('input[name="toUser"]').val(targetName);
             $(this).parents('.message-list li').find('.btn-reply').text('回复');
             $(this).text('收起');
         } else {
             $container.addClass('layui-hide');
             $container.find('input[name="targetUserId"]').val(0);
             $(this).text('回复');
         }
     });
 
});
 
