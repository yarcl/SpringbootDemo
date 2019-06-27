var index = new Vue({
    el: '#indexName',
    data: {
        userName: 'Nobody'
    }
});
window.onload = function() {
    var userInfo = localStorage.getItem("user");
    var objUser = JSON.parse(userInfo);
    index.userName = objUser.content.name;
}