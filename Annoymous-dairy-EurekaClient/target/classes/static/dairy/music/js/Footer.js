$.ajax({
    type: "GET",
    dataType: "json",
    cache: false,
    url: "index.ashx?method=GetFooterInfo",
    success: function (data) {
        if (data != null) {
            console.log(data);                                    //先看看后台返回的数据是什么样的
            var name = "";
            var link = "";
            if (data.result != null) {
                var address = data.result.teamInfo.F_Address;     //地址
                var email = data.result.teamInfo.F_Email;         //邮箱
                var tel = data.result.teamInfo.F_Tel;             //电话
                var daycount = data.result.flowRecord.F_DayFlow; //每日流量
                var sumcount = data.result.flowRecord.F_SumFlow; //总流量
                for (var i = 0; i < data.result.passion.length; i++) {
                    link = data.result.passion[i].F_Address;
                    name += "<p><a href='" + link + "' target='_blank'>" + data.result.passion[i].F_PassionName + "</a></p>";
                }
                $('#jl_link').append(name);
                $('#jl_address').append(address);
                $('#jl_email').append(email);
                $('#jl_tel').append(tel);
                $('#jl_daycount').html(daycount);
                $('#jl_sumcount').html(sumcount);
            }
        }
    }
});