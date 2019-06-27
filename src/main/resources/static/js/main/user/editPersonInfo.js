var editPersonInfoVue = new Vue({
    el: '#editPersonInfo',
    data: {
        userName: 'Nobody',
        id:"",
        name:"",
        loginPwd:"",
        roleId:"",
        isActive:""
    }
});

var userInfo = localStorage.getItem("user");
var objUser = JSON.parse(userInfo);
if(objUser != null) {
    editPersonInfoVue.userName = objUser.content.loginName;
    editPersonInfoVue.id = objUser.content.userId;
    editPersonInfoVue.name = objUser.content.name;
    editPersonInfoVue.loginPwd = objUser.content.loginPwd;
    editPersonInfoVue.roleId = objUser.content.roleId;
    editPersonInfoVue.isActive = objUser.content.isActive;
} else {
    alert("登录失败，请重新登录!")
}