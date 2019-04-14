var a = 0;
var b = 0;
$(function(){
	$("#features_dairy").show();
	$("#features_music").hide();
	
	/*点击">"将Features进行修改*/
	$("#jason_control_right").click(function(){
		a++;
		if(a%2 != 0){
			$("#features_dairy").hide();
			$("#features_music").show();
		}else{
			$("#features_dairy").show();
			$("#features_music").hide();
		}
	})
	/*点击">"将Features进行修改*/
	$("#jason_control_left").click(function(){
		b++;
		if(b%2 != 0){
			$("#features_dairy").hide();
			$("#features_music").show();
		}else{
			$("#features_dairy").show();
			$("#features_music").hide();
		}
	})
	
})
