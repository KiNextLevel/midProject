<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.4/kakao.min.js" integrity="sha384-DKYJZ8NLiK8MN4/C5P2dtSmLQ4KwPaoqAfyA/DfmEc1VDxu4yyC7wy6K1Hs90nka" crossorigin="anonymous"></script>
    <style>
        /* 기존 스타일 그대로 유지 */
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
            height: 500px; /* 기존 높이로 유지 */
            background: red;
            overflow: hidden;
            background: url("https://doc-08-2c-docs.googleusercontent.com/docs/securesc/68c90smiglihng9534mvqmq1946dmis5/fo0picsp1nhiucmc0l25s29respgpr4j/1631524275000/03522360960922298374/03522360960922298374/1Sx0jhdpEpnNIydS4rnN4kHSJtU1EyWka?e=view&authuser=0&nonce=gcrocepgbb17m&user=03522360960922298374&hash=tfhgbs86ka6divo3llbvp93mg4csvb38") no-repeat center/ cover;
            border-radius: 10px;
            box-shadow: 5px 20px 50px #000;
        }

        /* 로그인 폼 영역 조정 */
        .login {
            position: relative;
            width: 100%;
            height: 100%;
        }

        /* 소셜 로그인 섹션 */
        .social-login {
            width: 80%;
            margin: 0 auto;
            text-align: center;
            padding-top: 5px;
        }

        .or-divider {
            display: flex;
            align-items: center;
            text-align: center;
            margin: 10px 0;
            color: #fff;
            font-size: 14px;
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

        /* 소셜 로그인 버튼 컨테이너 */
        .social-login-buttons {
            display: flex;
            justify-content: center; /* 중앙 정렬 */
            gap: 20px; /* 버튼 사이 간격 */
            margin: 10px auto;
        }

        /* 정사각형 소셜 로그인 버튼 스타일 */
        .social-btn {
            width: 50px;
            height: 50px;
            border-radius: 8px;
            border: none;
            display: flex;
            align-items: center;
            justify-content: center;
            cursor: pointer;
            transition: all 0.3s ease;
            font-size: 24px; /* 아이콘 크기 */
        }

        /* 네이버 로그인 버튼 */
        .naver-login-btn {
            background-color: #1EC800;
            color: #fff;
        }

        .naver-login-btn:hover {
            background-color: #19A400;
            transform: translateY(-3px);
        }

        /* 카카오 로그인 버튼 */
        .kakao-login-btn {
            background-color: #fee500;
            color: #3c1e1e;
        }

        .kakao-login-btn:hover {
            background-color: #fddc00;
            transform: translateY(-3px);
        }

        /* 네이버 아이콘 (N 글자) */
        .naver-icon {
            font-weight: bold;
            font-style: normal;
            font-size: 22px;
        }

        /* 카카오 아이콘 (말풍선 모양) */
        .kakao-icon {
            font-size: 22px;
        }
    </style>
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

        <!-- 소셜 로그인 섹션 -->
        <div class="social-login">
            <!-- 정사각형 소셜 로그인 버튼 -->
            <div class="social-login-buttons">
                <!-- 네이버 로그인 버튼 -->
                <button type="button" class="social-btn naver-login-btn" onclick="naverLogin()">
                    <span class="naver-icon">N</span>
                </button>

                <!-- 카카오 로그인 버튼 -->
                <button type="button" class="social-btn kakao-login-btn" id="kakao-login-btn" onclick="kakaoLogin()">
                    <span class="kakao-icon"><i class="fas fa-comment"></i></span>
                </button>
            </div>
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
        // 카카오 SDK 초기화 (앱 키 설정)
        Kakao.init('cb9656ab4895e6ee319e89e74f28a308');

        // 카카오 로그인 처리
        Kakao.Auth.authorize({
            redirectUri: 'http://localhost:8088/Metronic-Shop-UI-master/theme/kakaoCallBack.do'  // 리다이렉트 URI 설정
        });
    }

    // 랜덤 상태 토큰 생성 함수
    function generateState() {
        return Math.random().toString(36).substr(2, 11);
    }
</script>

</body>
</html>
