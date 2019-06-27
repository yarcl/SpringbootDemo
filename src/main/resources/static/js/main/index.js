window.onload = function() {
    var user = localStorage.getItem("user");
    console.log(user);
    $("#index-name").html(JSON.parse(user).name);
}

var index = new Vue({
    el: '#app',
    data: {
        userName: 'Yarcl'
    }
});