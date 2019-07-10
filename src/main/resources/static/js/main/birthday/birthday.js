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
// 变量定义层次
var BirthObj = {
    nowPage:1,
    pageSize:1
};

// 原生数据层次
var birthVue = new Vue({
    el: '#birthList',
    data: {
        birthVueList: []
    }
});

// 过调用数据层次
/**
 * 修改当前页
 * @param nowPage
 */
function changeNowPage(nowPage) {
    BirthObj.nowPage += nowPage;
    callBirthData();
}

/**
 * 修改页面条数
 * @param pageSize
 */
function changePageSize(pageSize) {
    BirthObj.pageSize = pageSize;
}

/**
 * 调用生日数据
 */
function callBirthData() {
    $.ajax({
        type:'post',
        url:'/birthday/birthList.do',
        async:true,
        data:{
            nowPage: BirthObj.nowPage,
            pageSize: BirthObj.pageSize
        },
        dataType:'json',
        success: function(data){
            var success = data.success;
            var birthObj = data.content.object;
            BirthObj.totalPage = data.totalPage;
            if(success){
                birthVue.birthVueList = birthObj;
            } else {
                console.log("暂无数据!")
            }
        },
        error:function(e){
            console.log(e);
        }
    });
}


// 初始数据加载层次
window.onload = function() {
    callBirthData()
};