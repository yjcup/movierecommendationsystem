$(document).ready(function(){
    // Bind click event to the login button
    console.log(12312312)
    $('#login-btn').click(function(){
        // Get username and password input values
        var username = $('#username').val();
        var password = $('#password').val();

        // Make AJAX request to the backend
        $.ajax({
            type: 'POST',
            url: '/movielogin',
            data: {
                username: username,
                password: password
            },
            success: function(response){
                // Handle success response
                console.log(response);
                if(response.code=="0"){
                    layui.use(function () {
                        var layer = layui.layer;
                        layer.alert("登录成功")
                    })
                    window.location.href = '/index'; // 替换成你的 index 页面地址

                }else{
                    layui.use(function () {
                        var layer = layui.layer;
                        layer.alert("登录失败！！")
                    })
                }
                // You can redirect or show a success message here
            },
            error: function(xhr, status, error){
                // Handle error response
                console.error(xhr.responseText);
                // You can show an error message to the user here
            }
        });
    });
});