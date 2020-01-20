var remind = new Vue({
    el: '#remind',
    data: {
        voList: []
    }
});

window.onload = function() {
    jQuery.ajax({
        type: "get",
        url: "/remind/remindList.do",
        data: {},
        async:true, // 异步请求
        cache:false, // 设置为 false 将不缓存此页面
        dataType: 'json', // 返回对象
        success: function(data) {
            remind.voList = data.content;
        },
        error: function(data) {
            console.log(data);
        }
    }
)};