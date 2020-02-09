var homePage = "/router/remind/remindList.html";

$(".razor-btn-remind").click(function(){
    window.location.href = this.dataset.href;
});

function deleteRemind(obj) {
    var id = obj.dataset.id;
    jQuery.ajax({
        type: "get",
        url: "/remind/deleteRemind.do?id="+id,
        data: {},
        async:true, // 异步请求
        cache:false, // 设置为 false 将不缓存此页面
        dataType: 'json', // 返回对象
        success: function(data) {
            if(data && data.content == 1) {
                window.location.href = homePage;
            } else {
                alert("删除失败!")
            }
        },
        error: function(data) {
            alert("删除失败,请联系相关人员");
        }
    });
}