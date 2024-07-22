function register() {
    let email= $('#email').val();
    console.log($('#email').val());
    console.log($('#password').val());
    console.log($('#name').val());

    $.ajax({
        method: "post",
        url:"/user/register",
        data:{
            'email':email,
            'password':$('#password').val(),
            'name':$('#name').val()
        },
        success: (result)=>{
            if (result.result ==="success"){
                window.alert("가입을 환영합니다.");
                window.location.href="/user/login";
            }else {
                window.alert(email+"은 이미 가입된 이메일 입니다.");
            }
        }

    })
}