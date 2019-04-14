$(function(){
	
	$(".rel").on('click',function(){
		var value = $(this).attr("value");
		if(value == 0){
			window.location.href="/dairy/daily_index";
		}else if(value == 1){
			window.location.href="/dairy/voice_index";
		}else if(value == 2){
			window.location.href="/dairy/future_daily_index";
		}else{
			window.location.href="/dairy/future_voice_index";
		}
	})
	/**
	 * daily 阅读
	 */
	$(".ReadMore").on('click',function(){
		var value = $(this).attr("value");
		window.location.href="/dairy/show_daily_myself?id=" + value;
	})
	
	/**
	 * daily 删除
	 */
	$(".delete_daily").on('click',function(){
		var value = $(this).attr("value");
		window.location.href="/dairy/delete_daily_myself?id=" + value;
	})
	
	/**
	 * voice 删除
	 */
	$(".delete_voice").on('click',function(){
		var value = $(this).attr("value");
		window.location.href="/dairy/delete_voice_myself?id=" + value;
	})
	
	/**
	 * future daily 阅读
	 */
	$(".future_ReadMore").on('click',function(){
		var value = $(this).attr("value");
		window.location.href="/dairy/show_future_daily_myself?id=" + value;
	})
	
	/**
	 * future daily 删除
	 */
	$(".delete_future_daily").on('click',function(){
		var value = $(this).attr("value");
		window.location.href="/dairy/delete_future_daily_myself?id=" + value;
	})
	
	/**
	 * future voice 删除
	 */
	$(".delete_future_voice").on('click',function(){
		var value = $(this).attr("value");
		window.location.href="/dairy/delete_future_voice_myself?id=" + value;
	})
	
	/**
	 * 点击显示更多
	 */
	$(".show_more").on('click',function(){
		var value = $(this).attr("value");
		/**
		 * dairy_type:
		 * 	daily
		 * 	voice
		 * 	future_daily
		 * 	future_voice
		 */
		window.location.href="/dairy/show_more?dairy_type=" + value;
	})
})