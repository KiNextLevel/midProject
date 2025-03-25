<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>로그인 회원가입</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            font-family: 'Jost', sans-serif;
            background: linear-gradient(to bottom, #0f0c29, #302b63, #24243e);
        }

        .main {
            width: 350px;
            height: 500px;
            background: red;
            overflow: hidden;
            background: url("https://doc-08-2c-docs.googleusercontent.com/docs/securesc/68c90smiglihng9534mvqmq1946dmis5/fo0picsp1nhiucmc0l25s29respgpr4j/1631524275000/03522360960922298374/03522360960922298374/1Sx0jhdpEpnNIydS4rnN4kHSJtU1EyWka?e=view&authuser=0&nonce=gcrocepgbb17m&user=03522360960922298374&hash=tfhgbs86ka6divo3llbvp93mg4csvb38") no-repeat center/ cover;
            border-radius: 10px;
            box-shadow: 5px 20px 50px #000;
        }

        #chk {
            display: none;
        }

        .signup {
            position: relative;
            width: 100%;
            height: 100%;
        }

        label {
            color: #fff;
            font-size: 2.3em;
            justify-content: center;
            display: flex;
            margin: 60px;
            font-weight: bold;
            cursor: pointer;
            transition: .5s ease-in-out;
        }

        input {
            width: 60%;
            height: 20px;
            background: #e0dede;
            justify-content: center;
            display: flex;
            margin: 20px auto;
            padding: 10px;
            border: none;
            outline: none;
            border-radius: 5px;
        }

        button {
            width: 60%;
            height: 40px;
            margin: 10px auto;
            justify-content: center;
            display: block;
            color: #fff;
            background: #573b8a;
            font-size: 1em;
            font-weight: bold;
            margin-top: 20px;
            outline: none;
            border: none;
            border-radius: 5px;
            transition: .2s ease-in;
            cursor: pointer;
        }

        button:hover {
            background: #6d44b8;
        }

        .login {
            height: 460px;
            background: #eee;
            border-radius: 60% / 10%;
            transform: translateY(-180px);
            transition: .8s ease-in-out;
        }

        .login label {
            color: #573b8a;
            transform: scale(.6);
        }

        #chk:checked ~ .login {
            transform: translateY(-500px);
        }

        #chk:checked ~ .login label {
            transform: scale(1);
        }

        #chk:checked ~ .signup label {
            transform: scale(.6);
        }

        /* 네이버 로그인 버튼 스타일 */
        .naver-login-btn {
            width: 60%;
            height: 40px;
            margin: 10px auto;
            justify-content: center;
            display: block;
            color: #fff;
            background: #1EC800; /* 네이버 색상 */
            font-size: 1em;
            font-weight: bold;
            margin-top: 10px;
            outline: none;
            border: none;
            border-radius: 5px;
            transition: .2s ease-in;
            cursor: pointer;
        }

        .naver-login-btn:hover {
            background: #19A400; /* 호버 시 색상 */
        }

        .social-login {
            margin-top: 20px;
            text-align: center;
        }

        .social-login p {
            color: #fff;
            margin-bottom: 10px;
        }

        .or-divider {
            display: flex;
            align-items: center;
            text-align: center;
            margin: 15px 0;
            color: #fff;
        }

        .or-divider::before, .or-divider::after {
            content: '';
            flex: 1;
            border-bottom: 1px solid #fff;
        }

        .or-divider::before {
            margin-right: 10px;
        }

        .or-divider::after {
            margin-left: 10px;
        }
    </style>
</head>
<body>
<!-- partial:index.partial.html -->
<!DOCTYPE html>
<html>
<head>
    <title>nextLevel</title>
    <link rel="stylesheet" type="text/css" href="slide navbar style.css">
    <link href="css/style.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
    <input type="checkbox" id="chk" aria-hidden="true">

    <div class="signup">
        <form action="join.do" method="POST">
            <label for="chk" aria-hidden="true">회원가입</label>
            <input type="text" name="userName" placeholder="User name" required>
            <input type="email" name="userEmail" placeholder="Email" required>
            <input type="password" name="userPassword" placeholder="Password" required>
            <button>가입하기</button>
        </form>
    </div>

    <div class="login">
        <form action="login.do" method="POST">
            <label for="chk" aria-hidden="true">로그인</label>
            <input type="email" name="userEmail" placeholder="Email" required>
            <input type="password" name="userPassword" placeholder="Password" required>
            <button type="submit">로그인</button>
        </form>

        <!-- 소셜 로그인 섹션 추가 -->
        <div class="social-login">
            <div class="or-divider">또는</div>
            <form action="login.do" method="POST">
                <button type="button" class="naver-login-btn" onclick="naverLogin()">
                    <i class="fas fa-n-square"></i> 네이버 아이디로 로그인
                </button>
            </form>
            <form action="login.do" method="POST">
                <button type="button" class="kakao-login-btn" onclick="kakaoLogin()">
                    <img src="img/kakao_login_medinum_wide.png" alt="카카오 로그인 버튼">
                </button>
            </form>
        </div>
    </div>
</div>

<!-- 네이버 로그인 스크립트 -->
<script>
    function naverLogin() {
        var clientId = "HPtl9HdFUiGzoDPAPQ4a";
        var redirectURI = encodeURIComponent("http://localhost:8088/Metronic-Shop-UI-master/theme/naverCallback.do");
        var state = generateState();

        // 상태 토큰 저장
        localStorage.setItem("naverState", state);

        // 네이버 로그인 페이지로 리다이렉트
        var naverLoginUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
            + "&client_id=" + clientId
            + "&redirect_uri=" + redirectURI
            + "&state=" + state;

        window.location.href = naverLoginUrl;
    }

    function kakaoLogin() {
        // Kakao login functionality can be implemented here
        alert('카카오 로그인 버튼 클릭됨');
    }

    // 랜덤 상태 토큰 생성 함수
    function generateState() {
        return Math.random().toString(36).substr(2, 11);
    }
</script>
</body>
</html>
<!-- partial -->

</body>
</html>
