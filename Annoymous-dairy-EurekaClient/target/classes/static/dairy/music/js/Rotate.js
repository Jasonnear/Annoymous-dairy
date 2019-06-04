var ele = $("#player_image_detail");

var audioPlayer = document.querySelector('#audio');
//var timer = setInterval('singleRotate()', 60);

var timer = setInterval(function () {
    singleRotate();
}, 60);


//初始角度
var degree = 0;


//单次旋转
function singleRotate() {
    //如果音乐停止播放，则停止旋转
    if (audioPlayer.paused) {
        clearInterval(timer);
        return;
    }
    else {
        //一次增加50度
        degree = degree + 50 * Math.PI / 180;
        ele.css("transform", "rotate(" + degree + "deg)");       
    }

}

//console.log(audioPlayer.paused);

