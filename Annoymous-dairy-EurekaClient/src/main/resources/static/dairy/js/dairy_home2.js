var stompClient = null;
var connect_flag = true;


//展示专题为feel的数据：
function show_topic(topic_id){
	
	$(".single-blog-post").hide();
	$.ajax({
		type:"post",
		url:"/dairy/show_topic",
		data:{"topic_id":topic_id},
		dataType:"json",
		async:true,
		success: function(data)
		{
			if(data.object != null)
			{		    				
				arr = data.object;
				for(var i = 0; i < arr.length ; i++)
				{
					if(arr[i].first_image_url != '/pic/default_first_image_url.jpg' && arr[i].dairytype_id == 0){
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id + "'><div class='image-box'><img src='" 
								+ arr[i].first_image_url + "'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
								+ arr[i].id + "'>" 
								+ arr[i].article + "</a></h3><p>" 
								+ arr[i].first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}else if(arr[i].first_image_url == '/pic/default_first_image_url.jpg' && arr[i].dairytype_id == 0){
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
								+ arr[i].id + "'>" 
								+ arr[i].article + "</a></h3><p>" 
								+ arr[i].first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}else{
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Voice</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='#'>" 
								+ arr[i].article + "</a></h3><p><audio src='" 
								+ arr[i].url + "' controls='controls'></audio></p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll2' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}
				}
			}
		}
	});
	
}

//展示Voice数据：
function show_voice(){
	$(".single-blog-post").hide();
	$.ajax({
		type:"post",
		url:"/dairy/show_voice",
		dataType:"json",
		async:true,
		success: function(data)
		{ 
			if(data.object != null)
			{
				arr = data.object;
				console.log(arr[0]);
				for(var i = 0; i < arr.length ; i++)
				{
					$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
							+ arr[i].id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Voice</a></li><li class='date'>" 
							+ arr[i].createTime + "</li></ul><h3 class='title'><a href='#'>" 
							+ arr[i].article + "</a></h3><p><audio src='" 
							+ arr[i].url + "' controls='controls'></audio></p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll2' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");			   				
				}
			}
		}
	});
}

//展示Daily数据：
function show_daily(){
	$(".single-blog-post").hide();
	$.ajax({
		type:"post",
		url:"/dairy/show_daily",
		dataType:"json",
		async:true,
		success: function(data)
		{ 
			if(data.object != null)
			{
				arr = data.object;
				for(var i = 0; i < arr.length ; i++)
				{
					if(arr[i].first_image_url != '/pic/default_first_image_url.jpg' && arr[i].dairytype_id == 0){
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id + "'><div class='image-box'><img src='" 
								+ arr[i].first_image_url + "'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
								+ arr[i].id + "'>"
								+ arr[i].article + "</a></h3><p>" 
								+ arr[i].first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}else{
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
								+ arr[i].id + "'>" 
								+ arr[i].article + "</a></h3><p>" 
								+ arr[i].first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}
				}
				
			}
		}
	});
}

function content(body){
	console.log(body);
	if(body.first_image_url != '/pic/default_first_image_url.jpg' && body.dairytype_id == 0){
		$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
				+ body.id + "'><div class='image-box'><img src='" 
				+ body.first_image_url + "'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
				+ body.createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
				+ body.id + "'>" 
				+ body.article + "</a></h3><p>" 
				+ body.first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
	}else if(body.first_image_url == '/pic/default_first_image_url.jpg' && body.dairytype_id == 0){
		$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
				+ body.id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
				+ body.createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
				+ body.id + "'>" 
				+ body.article + "</a></h3><p>" 
				+ body.first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
	}else{
		$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
				+ body.id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Voice</a></li><li class='date'>" 
				+ body.createTime + "</li></ul><h3 class='title'><a href='#'>" 
				+ body.article + "</a></h3><p><audio src='" 
				+ body.url + "' controls='controls'></audio></p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll2' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
	}
}
function showContent(body) {
    content(body);
    
}
function connect() {
    var socket = new SockJS('/point'); //建立连接到端点的对象(还未发起连接)
    stompClient = Stomp.over(socket);			//用stomp进行包装，规范WebSocket传输协议使用的协议,用stomp目的是实现客户端和服务器端所使用的WebSocket协议是一致的
    stompClient.connect({}, function (frame) {	//stompClient.connect()是stompClient固有的一个事件【作用:连接服务器的WebSocket的端点】,当连接成功后就回调方法
        console.log('Connected: ' + frame);	//打印 + $("#id").html()
        stompClient.subscribe('/push/dairy' + $("#id").html(), function (result) { //stompClient.subscribe()是stompClient固有的一个事件【作用:订阅服务器消息的某个路径和接收服务器消息，若经过@SendTo的时候发现与该路径相同即可广播到回调方法中】，
        	console.info(result)//目的:打印admin所发的消息
        	showContent(JSON.parse(result.body));
        });
    });
}

function sendName() {
	//自动去发布消息
	if($("#send").html() == ''){
	}else{
		stompClient.send("/app/send");
	}    	
}

//展示所有
function record(){
	$.ajax({
		type:"post",
		url:"/dairy/show_all_part",
		dataType:"json",
		async:true,
		success: function(data)
		{ 
			if(data.object != null)
			{		    				
				arr = data.object;
				console.log(arr[0].id);
				for(var i = 0; i < arr.length ; i++)
				{
					if(arr[i].first_image_url != '/pic/default_first_image_url.jpg' && arr[i].dairytype_id == 0){
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id +"'><div class='image-box'><img src='" 
								+ arr[i].first_image_url + "'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
								+ arr[i].id + "'>" 
								+ arr[i].article + "</a></h3><p>" 
								+ arr[i].first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}else if(arr[i].first_image_url == '/pic/default_first_image_url.jpg' && arr[i].dairytype_id == 0){
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Daily</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='/dairy/show_daily_myself?id=" 
								+ arr[i].id + "'>" 
								+ arr[i].article + "</a></h3><p>" 
								+ arr[i].first_context + "</p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}else{
						$("#con").prepend("<div class='single-blog-post' style='padding-top:20px' value='" 
								+ arr[i].id + "'><div class='image-box'></div><div class='post-meta-box bg-box'><ul class='author-meta clearfix'><li class='tag'><a href='#'>Voice</a></li><li class='date'>" 
								+ arr[i].createTime + "</li></ul><h3 class='title'><a href='#'>" 
								+ arr[i].article + "</a></h3><p><audio src='" 
								+ arr[i].url + "' controls='controls'></audio></p><div class='layui-btn-group'><button class='layui-btn layui-bg-black coll2' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe600;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe63a;</i></button><button class='layui-btn layui-bg-black' style='padding-left: 20px; padding-right: 20px;'><i class='layui-icon'>&#xe6c6;</i></button></div></div></div>");
					}
				}
			}
		}
	});
}


//聊天的连接
function chat_connect() {
    var socket = new SockJS('/point'); //建立连接到端点的对象(还未发起连接)
    stompClient = Stomp.over(socket);			//用stomp进行包装，规范WebSocket传输协议使用的协议,用stomp目的是实现客户端和服务器端所使用的WebSocket协议是一致的
    stompClient.connect({}, function (frame) {	//stompClient.connect()是stompClient固有的一个事件【作用:连接服务器的WebSocket的端点】,当连接成功后就回调方法
        console.log('Connected: ' + frame);	//打印 + $("#id").html()
        stompClient.subscribe('/chat' + $("#id").html(), function (result) { //stompClient.subscribe()是stompClient固有的一个事件【作用:订阅服务器消息的某个路径和接收服务器消息，若经过@SendTo的时候发现与该路径相同即可广播到回调方法中】，
        	console.info(result)//目的:打印admin所发的消息
        	//showChatContent(JSON.parse(result.body));
        });
    });
}

//发送聊天记录

$(function(){
	
	/**
	 * 用户发布消息
	 */
	//先显示以前存放的内容
	record();
	if(connect_flag){
		//发布消息的连接
		setTimeout(connect,1000);
		connect_flag = false;
	}
	setTimeout(sendName,2000);
	
	$(".rel").on('click',function(){
		var value = $(this).attr("value");
		show_topic(value);
	})
	
	/**
	 * 聊天
	 */
	$("#con").on('click',".layui-icon-username",function(){
		var to_userid = $(this).siblings().eq(0).html();
		var from_userid = $("#id").html();
		if(from_userid != to_userid){
			
			
		}
	})
	
	/**
	 * daily收藏
	 */
	$("#con").on('click','.coll',function(){
		var dairy_id = $(this).parent().parent().parent().attr("value");		
		$.ajax({
			type:"post",
			url:"/dairy/collect_daily",
			dataType:"json",
			data:{"id":dairy_id},
			async:true,
			success: function(data)
			{ 
				if(data.code == 1){
					//改变图标的样式
					//window.location.href = "/dairy/show_collect";
				}
			}
		});
		
	})
	
	/**
	 * voice收藏
	 */
	$("#con").on('click','.coll2',function(){
		var dairy_id = $(this).parent().parent().parent().attr("value");
		$.ajax({
			type:"post",
			url:"/dairy/collect_voice",
			dataType:"json",
			data:{"id":dairy_id},
			async:true,
			success: function(data)
			{ 
				if(data.code == 1){
					//改变图标的样式
					//window.location.href = "/dairy/show_collect";
				}
			}
		});
		
	})
	
})