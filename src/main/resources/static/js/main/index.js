window.onload = function() {
    var user = localStorage.getItem("user");
    console.log(user);
    $("#index-name").html(JSON.parse(user).name);
}