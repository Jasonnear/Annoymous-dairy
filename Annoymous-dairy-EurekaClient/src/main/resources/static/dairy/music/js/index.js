require.config({
    paths: {
        jquery: 'jquery-3.2.1.min'
    }
});
require(['jquery'], function ($) {
    require(["bootstrap.min"]);       //require  bootstrap.min.js
    /*控制导航栏显示和隐藏*/
    $('#jl_nav_hot').mouseover(function () {
        $('#jl_nav_hot').addClass("jl_nav_current").siblings().removeClass("jl_nav_current");
        $('#jl_detail_movie').hide();
        $('#jl_detail_music').hide();
        $('#jl_detail_photo').hide();
        $('#jl_detail_travel').hide();
        $('#jl_detail_article').hide();
        $('#jl_detail_food').hide();
    })

    $('#jl_nav_movie').mouseover(function () {
        $('#jl_detail_movie').show().siblings().hide();
        $('#jl_nav_movie').addClass("jl_nav_current").siblings().removeClass("jl_nav_current");
    });
    $('#jl_middle').mouseover(function () {
        $('#jl_detail_movie').hide();
    });

    $('#jl_nav_music').mouseover(function () {
        $('#jl_detail_music').show().siblings().hide();
        $('#jl_nav_music').addClass("jl_nav_current").siblings().removeClass("jl_nav_current");
    });
    $('#jl_middle').mouseover(function () {
        $('#jl_detail_music').hide();
    });

    $('#jl_nav_photo').mouseover(function () {
        $('#jl_detail_photo').show().siblings().hide();
        $('#jl_nav_photo').addClass("jl_nav_current").siblings().removeClass("jl_nav_current");
    });
    $('#jl_middle').mouseover(function () {
        $('#jl_detail_photo').hide();
    });

    $('#jl_nav_travel').mouseover(function () {
        $('#jl_detail_travel').show().siblings().hide();
        $('#jl_nav_travel').addClass("jl_nav_current").siblings().removeClass("jl_nav_current");
    });
    $('#jl_middle').mouseover(function () {
        $('#jl_detail_travel').hide();
    });

    $('#jl_nav_article').mouseover(function () {
        $('#jl_detail_article').show().siblings().hide();
        $('#jl_nav_article').addClass("jl_nav_current").siblings().removeClass("jl_nav_current");
    });
    $('#jl_middle').mouseover(function () {
        $('#jl_detail_article').hide();
    });

    $('#jl_nav_food').mouseover(function () {
        $('#jl_detail_food').show().siblings().hide();
        $('#jl_nav_food').addClass("jl_nav_current").siblings().removeClass("jl_nav_current");
    });
    $('#jl_middle').mouseover(function () {
        $('#jl_detail_food').hide();
    });

    $('#jl_header').mouseover(function () {
        $('#jl_detail_movie').hide();
        $('#jl_detail_music').hide();
        $('#jl_detail_photo').hide();
        $('#jl_detail_travel').hide();
        $('#jl_detail_article').hide();
        $('#jl_detail_food').hide();
    });

    $('#jl_message').mouseover(function () {
        $('#jl_detail_movie').hide();
        $('#jl_detail_music').hide();
        $('#jl_detail_photo').hide();
        $('#jl_detail_travel').hide();
        $('#jl_detail_article').hide();
        $('#jl_detail_food').hide();
    });

    require(["Picture"]);    //请求加载Picture.js
    require(["Rotate"]);     //请求加载Rotate.js


    /*每日推荐显示和隐藏*/
    $('#jl_message_movie').mouseover(function () {
        $('#jl_commend_movie').show().siblings('#jl_commend_photo').hide().siblings('#jl_commend_music').hide().siblings('#jl_commend_travel').hide().siblings('#jl_commend_article').hide().siblings('#jl_commend_food').hide();
        $(this).addClass('jl_intro_current').siblings().removeClass('jl_intro_current');
    });
    $('#jl_message_music').mouseover(function () {
        $('#jl_commend_music').show().siblings('#jl_commend_movie').hide().siblings('#jl_commend_photo').hide().siblings('#jl_commend_travel').hide().siblings('#jl_commend_article').hide().siblings('#jl_commend_food').hide();
        $(this).addClass('jl_intro_current').siblings().removeClass('jl_intro_current');
    });
    $('#jl_message_photo').mouseover(function () {
        $('#jl_commend_photo').show().siblings('#jl_commend_movie').hide().siblings('#jl_commend_music').hide().siblings('#jl_commend_travel').hide().siblings('#jl_commend_article').hide().siblings('#jl_commend_food').hide();
        $(this).addClass('jl_intro_current').siblings().removeClass('jl_intro_current');
    });
    $('#jl_message_travel').mouseover(function () {
        $('#jl_commend_travel').show().siblings('#jl_commend_movie').hide().siblings('#jl_commend_music').hide().siblings('#jl_commend_photo').hide().siblings('#jl_commend_article').hide().siblings('#jl_commend_food').hide();
        $(this).addClass('jl_intro_current').siblings().removeClass('jl_intro_current');
    });
    $('#jl_message_article').mouseover(function () {
        $('#jl_commend_article').show().siblings('#jl_commend_movie').hide().siblings('#jl_commend_music').hide().siblings('#jl_commend_photo').hide().siblings('#jl_commend_travel').hide().siblings('#jl_commend_food').hide();
        $(this).addClass('jl_intro_current').siblings().removeClass('jl_intro_current');
    });
    $('#jl_message_food').mouseover(function () {
        $('#jl_commend_food').show().siblings('#jl_commend_movie').hide().siblings('#jl_commend_music').hide().siblings('#jl_commend_photo').hide().siblings('#jl_commend_article').hide().siblings('#jl_commend_travel').hide();
        $(this).addClass('jl_intro_current').siblings().removeClass('jl_intro_current');
    });

 //   require(["Footer"]);     //请求加载Footer.js
});

//锚点跳转(推荐）
function jumpIntroduce() {
    document.getElementById("jl_message_text").scrollIntoView(); //跳到每日推荐
}

//锚点跳转(图片轮播)
function jumpTop() {
    document.getElementById("jl_middle").scrollIntoView(); //跳到图片轮播
}


//截取字符串
function sub(str, num) {  //str 为传进来的字符串，num 为要截取的字符长度
    if (str != null) {
        if (str.length > num) {
            result = str.substring(0, num) + "...";
        }
        else {
            result = str;
        }
    }
    return result;
}
