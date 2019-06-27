(function(windows) {
    windows.onload = function() {
        $(".form-sub").click(function(){
            $.ajax({
                type:'post',
                url:'/user/login.do',
                //contentType: 'application/json',
                async:true,
                data:{
                    username:$("#userName").val(),
                    password:$("#password").val()
                },
                dataType:'json',
                success: function(data){
                    var flag = data.success;
                    var userStr = JSON.stringify(data||data.content);
                    if(flag){
                        localStorage.setItem("user", userStr);
                        windows.location.href = "/router/main/index";
                    } else {
                        alert("用户名或者密码错误!")
                    }
                },
                error:function(e){
                    console.log(e);
                }
            });
        });
    }
})(window);
