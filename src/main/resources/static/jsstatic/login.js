$.yarcl = '';

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
            var content = data.content;
            console.log(content);
            var flag =  data.flag;
            if(flag){
                $.yarcl = '张三';
                window.location.href = "/router/main/index";
            } else {
                alert("用户名或者密码错误!")
            }
        },
        error:function(e){
            console.log(e);
        }
    });
});