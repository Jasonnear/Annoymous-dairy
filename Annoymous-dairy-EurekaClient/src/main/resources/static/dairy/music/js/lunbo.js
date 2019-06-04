//轮播图
var show = document.getElementById("show"); // 获得大盒子
var ul = document.getElementById("ul"); // 获得ul
var ulLis = ul.children;// 获得ul的盒子 以此来生成ol中li的个数
var liWidth = ul.children[0].offsetWidth;// 获得每个li的宽度
// 操作元素
// 因为要做无缝滚动，所以要克隆第一张，放到最后一张后面
// 1. 克隆元素
ul.appendChild(ul.children[0].cloneNode(true));

// 2.创建ol 和li
var ol = document.createElement("ol");//创建ol元素
show.appendChild(ol);// 把ol放到scroll盒子里面去
for (var i=0;i<ulLis.length-1;i++) {
    var li = document.createElement("li");// 创建li元素
    li.innerHTML = i + 1;// 给li里面添加文字  1 2 3 4 5
    ol.appendChild(li);// 将li元素添加到ol里面
}
ol.children[0].className = "current";// ol中的第一个li背景色为purple

// 动画函数 第一个参数，代表谁动  第二个参数 动多少
// 让图片做匀速运动，匀速动画的原理是 当前的位置 + 速度  即 offsetLeft + speed

function animate(obj,target){
    // 首先清除掉定时器
    clearInterval(obj.timer);
    // 用来判断 是+ 还是 -  即说明向左走还是向右走
    var speed = obj.offsetLeft < target ? 8 : -8;
    obj.timer = setInterval(function(){
        var result = target - obj.offsetLeft;//它们的差值不会超过speed
        obj.style.left = obj.offsetLeft + speed + "px";
        // 有可能有小数的存在，所以在这里要做个判断
        if (Math.abs(result) <= Math.abs(speed)) {
            clearInterval(obj.timer);
            obj.style.left = target + "px";
        }
    },20);
}

var timer = null; // 轮播图的定时器
var key = 0;// 控制播放的张数
var circle = 0;// 控制小圆点

timer = setInterval(autoplay,4000);// 自动轮播
function autoplay(){
    /*自动轮播时,要对播放的张数key进行一个判断,如果播放的张数超过ulLis.length-1,
    就要重头开始播放.  因为我们克隆了第一张并将其放在最后面,所以我们要从第二张图片开始播放*/
    key++; // 先++
    if(key > ulLis.length-1){// 后判断

        ul.style.left = 0; // 迅速调回
        key = 1; // 因为第6张是第一张，所以播放的时候是从第2张开始播放
    }
    // 动画部分
    animate(ul,-key*liWidth);
    // 小圆点circle   当显示第几张图片是，对应的小圆点的颜色也发生变化
    var olLis = ol.children;
    /*同理,对小圆点也要有一个判断*/
    circle++;
    if (circle > olLis.length-1) {
        circle = 0;
    }
    // 小圆点颜色发生变化
    for (var i = 0 ; i < olLis.length;i++) {
        // 先清除掉所用的小圆点类名
        olLis[i].className = "";
    }
    // 给当前的小圆点 添加一个类名
    olLis[circle].className = "current";
}
