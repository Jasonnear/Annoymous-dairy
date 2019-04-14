    var zuoi=1;
    $('.memessagebutton').click(function(){
        if(zuoi==1){
            $('.messagebox').show();
            $('.messagebox').animate({width:'300px',height:'500px'},350);
            zuoi=0;
        }else{
        	$('.messagebox').animate({height:'0',width:'0',},350);
        	setTimeout(function(){
        		$('.messagebox').hide()},300);
            zuoi=1;                	
        }
    });