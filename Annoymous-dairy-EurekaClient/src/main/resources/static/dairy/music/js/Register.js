//$('input[name="username"]').attr("value","");
//$('input[name="phone"]').attr("value", "");
//$('input[name="mail"]').attr("value", "");
//$('input[name="password"]').attr("value", "");

function check() {
    var username = $('input[name="username"]').val();
    var phone = $('input[name="phone"]').val();
    var mail = $('input[name="mail"]').val();
    var password = $('input[name="password"]').val();
    if (username == "" || phone == "" || mail == "" || password == "") {
        alert("请将信息填写完整");
        return false;
    }
    else {
        $.ajax({
            type: "GET",
            dataType: "json",
            cache: false,
            url: "index.ashx?method=StoreLoginInfo",
            data: { username: username, phone: phone, mail: mail, password: password },
            success: function (data) {
                console.log(data);
                if (data.result) {
                    window.location.href = "RegisterSuccess.html";
                } else {
                    alert("注册失败");
                    return false;
                }
            }
        });
    }

}