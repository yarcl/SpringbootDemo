/*
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
                        windows.location.href = "/router/main/index.html";
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
})(window);*/

var birthVue = new Vue({
    el: '#birthList',
    data: {
        birthVueList: []
    }
});

window.onload = function() {
    $.ajax({
        type:'post',
        url:'/birthday/birthList.do',
        //contentType: 'application/json',
        async:true,
        data:{},
        dataType:'json',
        success: function(data){
            var flag = data.success;
            var birthList = JSON.stringify(data?data.content:"[]");
            if(flag){
                birthVue.birthVueList = eval(birthList);
            } else {
                console.log("暂无数据!")
            }
        },
        error:function(e){
            console.log(e);
        }
    });
};
