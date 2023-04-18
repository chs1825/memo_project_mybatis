<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div class="login-box">
    <h2>Login</h2>
    <form>
        <div class="user-box">
            <input type="text" id="userId" name="" required="" value="">
            <label>UserId</label>
        </div>
        <div class="user-box">
            <input type="password" id="userPwd" name="" required="" value="">
            <label>Password</label>
        </div>
        <button type="button" id="submitBtn">Submit</button>
        <br/>
        <p>Don't have an account? <a href="#">Sign up</a></p>
    </form>
</div>
</body>
<script type="text/javascript" src="/js/test.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script>
    let submitBtn = document.querySelector("#submitBtn");

    function loginAction(uId, uPwd) {

        // let data = {uId, uPwd};
        let data = {
            "name": "John",
            "age": 30,
            "address": {
                "city": "Seoul",
                "street": "123 Main St",
                "zipcode": "12345"
            },
            "contacts": [
                {
                    "type": "email",
                    "value": "john@example.comdas"
                },
                {
                    "type": "phone",
                    "value": "555-1234"
                }
            ],
            "hobbies": {
                "indoor": ["reading", "puzzles"],
                "outdoor": ["hiking", "biking"]
            }
        }

        // data = jsonToParam(data);

        let xhr = new XMLHttpRequest();
        xhr.open('POST', 'loginAction.do');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            if (xhr.status === 200) {

            } else {
                console.error("뭔가 문제 발생");
            }

        }

        console.log(data);
        console.log(jsonToParam(data));
        xhr.send(jsonToParam(data));
    }

    submitBtn.addEventListener('click', function () {
        let uId = document.querySelector("#userId").value;
        let uPwd = document.querySelector("#userPwd").value;

        if (uId != null && uPwd != null) {
            loginAction(uId, uPwd);
        } else {
            window.location.reload();
        }

    });


</script>


<style>
    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }

    body {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-color: #f1f1f1;
    }

    .login-box {
        width: 350px;
        background-color: #fff;
        padding: 40px;
        border-radius: 10px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
    }

    h2 {
        text-align: center;
        margin-bottom: 30px;
        color: #333;
    }

    form {
        display: flex;
        flex-direction: column;
    }

    form .user-box {
        position: relative;
        margin-bottom: 20px;
    }

    form .user-box input {
        width: 100%;
        padding: 10px 0;
        font-size: 16px;
        border: none;
        border-bottom: 1px solid #999;
        outline: none;
        background: transparent;
    }

    form .user-box label {
        position: absolute;
        top: 0;
        left: 0;
        padding: 10px 0;
        font-size: 16px;
        color: #999;
        pointer-events: none;
        transition: 0.5s;
    }

    form .user-box input:focus ~ label,
    form .user-box input:valid ~ label {
        top: -20px;
        left: 0;
        color: #333;
        font-size: 12px;
    }

    a {
        display: inline-block;
        padding: 10px 20px;
        background-color: #333;
        color: #fff;
        font-size: 16px;
        text-decoration: none;
        border-radius: 5px;
        transition: 0.5s;
    }

    a:hover {
        background-color: #fff;
        color: #333;
    }

    p {
        text-align: center;
        margin-top: 20px;
    }

    .signup-box {
        text-align: center;
        margin-top: 20px;
    }

    .signup-box a {
        color: #333;
        font-weight: bold;
        text-decoration: none;
    }

    .signup-box a:hover {
        text-decoration: underline;
    }

</style>
</html>