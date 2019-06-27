var personInfoVue = new Vue({
    el: '#personInfo',
    data: {
        userName: 'Nobody',
        id:"",
        name:"",
        loginPwd:"",
        roleId:"",
        isActive:""
    }
});

window.onload = function() {
    var userInfo = localStorage.getItem("user");
    var objUser = JSON.parse(userInfo);
    if(objUser != null) {
        personInfoVue.userName = objUser.content.loginName;
        personInfoVue.id = objUser.content.userId;
        personInfoVue.name = objUser.content.name;
        personInfoVue.loginPwd = objUser.content.loginPwd;
        personInfoVue.roleId = objUser.content.roleId;
        personInfoVue.isActive = objUser.content.isActive;
    } else {
        alert("登录失败，请重新登录!")
    }
}