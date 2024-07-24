function logout() {
    $.ajax({
        type: "GET",
        url: '/user/logout',
        dataType: 'json',
        username:'',
        password: ''
    })
    setTimeout(function () {
        window.location.href="/";
    })
}