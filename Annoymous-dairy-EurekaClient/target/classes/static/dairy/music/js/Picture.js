var insert_ul = "";
var insert_ol = "";
//for (var j = 0; j < 5; j++) {
//    var pic = data.result[i].List[j].ZK_ADDRESS;
//    if (j == 0) {
//        insert_ul += "<li class='current'><img src='" + pic + "' alt=''/></li>";
//        insert_ol += "<li class='current'></li>";
//    } else {
//        insert_ul += "<li><img src='" + pic + "' alt=''/></li>";
//        insert_ol += "<li></li>";
//    }
//}
//$("#pic>ul").html(insert_ul);
//$("#pic>ol").html(insert_ol);

$("#jl_pic>ol>li").off("mouseover").on("mouseover", function () {
    var index = $(this).index();
    $(this).addClass("jl_ol_current").siblings().removeClass("jl_ol_current");
    $("#jl_pic>ul>li").eq(index).addClass("jl_pic_current").siblings().removeClass("jl_pic_current");
})

var step = 3500;
var timer = setInterval(function () {
    Timer();
}, step);
$('#jl_pic').hover(function () {
    clearInterval(timer);
    $("#jl_left_btn").show();
    $("#jl_right_btn").show();
}, function () {
    timer = setInterval(function () {
        Timer();
    }, step);
    $("#jl_left_btn").hide();
    $("#jl_right_btn").hide();
});


$("#jl_pic #jl_left_btn").off("click").on("click", function () {
    var index = $("#jl_pic>ul>li.jl_pic_current").index();   
    var size = $("#jl_pic>ul>li").length;
    if (--index == -1) {
        index = size - 1;
    }
    $("#jl_pic>ol>li").eq(index).mouseover();
});

$("#jl_pic #jl_right_btn").off("click").on("click", function () {
    var index = $("#jl_pic>ul>li.jl_pic_current").index();
    var size = $("#jl_pic>ul>li").length;
    if (++index == size) {
        index = 0;
    }
    $("#jl_pic>ol>li").eq(index).mouseover();
});

function Timer() {
    var index = $("#jl_pic>ul>li.jl_pic_current").index();
    var size = $("#jl_pic>ul>li").length;
    if (++index == size) {
        index = 0;
    }
    $("#jl_pic>ol>li").eq(index).mouseover();
}




//$.ajax({
//    type: "GET",
//    dataType: "json",
//    cache: false,
//    url: "bask.ashx?method=Get_Picture",
//    data: { Information: "Information" },
//    success: function (data) {
//        if (data != null) {
//            for (var i = 0; i < data.result.length; i++) {
//                var type = data.result[i].TYPE;
//                //中间两张图片轮播
//                if (type == 'carousel') {
//                    var insert_ul = "";
//                    var insert_ol = "";
//                    for (var j = 0; j < data.result[i].List.length; j++) {
//                        var pic = data.result[i].List[j].ZK_ADDRESS;
//                        if (j == 0) {
//                            insert_ul += "<li class='current'><img src='" + pic + "' alt=''/></li>";
//                            insert_ol += "<li class='current'></li>";
//                        } else {
//                            insert_ul += "<li><img src='" + pic + "' alt=''/></li>";
//                            insert_ol += "<li></li>";
//                        }
//                    }
//                    $("#pic>ul").html(insert_ul);
//                    $("#pic>ol").html(insert_ol);

//                    $("#pic>ol>li").off("click").on("click", function () {
//                        var index = $(this).index();
//                        $(this).addClass("current").siblings().removeClass("current");
//                        $("#pic>ul>li").eq(index).addClass("current").siblings().removeClass("current");
//                    })

//                    var step = 3500;
//                    var timer = setInterval(function () {
//                        Timer();
//                    }, step);
//                    $('#pic').hover(function () {
//                        clearInterval(timer);
//                        $("#left_btn").show();
//                        $("#right_btn").show();
//                    }, function () {
//                        timer = setInterval(function () {
//                            Timer();
//                        }, step);
//                        $("#left_btn").hide();
//                        $("#right_btn").hide();
//                    });


//                    $("#pic #left_btn").off("click").on("click", function () {
//                        var index = $("#pic>ul>li.current").index();
//                        var size = $("#pic>ul>li").length;
//                        if (--index == -1) {
//                            index = size - 1;
//                        }
//                        $("#pic>ol>li").eq(index).click();
//                    });

//                    $("#pic #right_btn").off("click").on("click", function () {
//                        var index = $("#pic>ul>li.current").index();
//                        var size = $("#pic>ul>li").length;
//                        if (++index == size) {
//                            index = 0;
//                        }
//                        $("#pic>ol>li").eq(index).click();
//                    });

//                    function Timer() {
//                        var index = $("#pic>ul>li.current").index();
//                        var size = $("#pic>ul>li").length;
//                        if (++index == size) {
//                            index = 0;
//                        }
//                        $("#pic>ol>li").eq(index).click();
//                    }

//                }

//            }
//        }


//    }
//})
