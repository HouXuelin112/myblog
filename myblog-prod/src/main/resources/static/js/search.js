
var search = $("#searchtxt");
search.on("keyup", function () {
    let text = $(this).val().trim();
    if (text === ''){
        $(".search-result").hide();
        return;
    }
    $.post({
        url: "/getTips",
        data: {
            text : text
        },
        success: function (data) {
            if (data.length === 0){
                $(".search-result").hide();
                return;
            }
            let li = "";
            for (let blog of data){
                li += "<a style='text-decoration: none;color: black' href='/read/" + blog.id + "'><li value='" + blog.id +"' class='option' style='line-height:30px;text-align:center;letter-spacing:1px;'>" + blog.title + "</li></a>";
            }
            $(".search-result").html(li);
            $(".search-result").show();
            $(".option").on("mouseenter",function () {
                $(this).css("background-color", "lightgrey").css("border-right", "5px solid black");
            })
            $(".option").on("mouseout",function () {
                $(this).css("background-color", "white").css("border-right", "0");
            })
            $(".option").click(function () {
                $("#searchtxt").val($(this).text().trim());
                $("#searchId").val($(this).attr("value"));
                $(".search-result").hide();
            });
        },
        error: function () {
            console.log("chucuole");
        }
    })
})
$("#searchtxt").on("blur", function () {
    $(".search-result").hide();
})
