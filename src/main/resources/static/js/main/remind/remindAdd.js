$(".remind-save").click(function(){
    $.ajax({
        type:'post',
        url:'/remind/addRemind.do',
        contentType: 'application/json',
        async:true,
        data:JSON.stringify({
            "remindTitle":$("#remindTitle").val(),
            "remindDate":$("#remindDate").val(),
            "remindType":$("#remindType").val(),
            "remindFre":$("#remindFre").val(),
            "pushType":$("#pushType").val(),
            "remindPath":$("#pushPath").val()
        }),
        dataType:'json',
        success: function(data){
            var flag = data.success;
            if(flag){
                window.location.href = "/router/remind/remindList.html";
            } else {
                alert("返回数据失败!")
            }
        },
        error:function(e){
            console.log(e);
        }
    });
});