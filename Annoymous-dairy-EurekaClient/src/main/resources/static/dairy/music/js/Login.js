function login() {
    var phone = $('input[name="phone"]').val();
    var password = $('input[name="password"]').val();
    if (phone == "") {
        alert("请填写手机号码");
        return false;
    }
    else if (password == "") {
        alert("请填写密码");
        return false;
    }
    else {
        $.ajax({
            type: "GET",
            dataType: "json",
            cache: false,
            url: "index.ashx?method=Login",
            data: { phone: phone, password: password },
            success: function (data) {
                console.log(data);
                if (data.result != null) {
                    window.location.href = "index.html";
                } else {
                    alert("账号或密码错误，请重新输入");
                    return false;
                }
            }
        });
    }
    
}