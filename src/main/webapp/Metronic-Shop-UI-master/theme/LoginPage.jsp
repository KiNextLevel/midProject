<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인 회원가입</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!-- 카카오 SDK 추가 -->
    <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/SignInUp.css" rel="stylesheet">
</head>
<body>
<div class="auth-container">
    <!-- 헤더 영역 -->
    <div class="auth-header">
        <h2>Next Love</h2>
    </div>

    <!-- 탭 영역 -->
    <div class="auth-tabs">
        <div class="auth-tab" id="login-tab">로그인</div>
        <div class="auth-tab active" id="signup-tab">회원가입</div>
    </div>

    <!-- 컨텐츠 영역 -->
    <div class="auth-content">
        <!-- 로그인 폼 -->
        <form class="auth-form" id="login-form" action="login.do" method="POST">
            <div class="form-group">
                <label for="login-email"><i class="fa fa-envelope"></i> 이메일</label>
                <input type="email" id="login-email" name="userEmail" class="form-control" placeholder="이메일 주소" required>
            </div>

            <div class="form-group">
                <label for="login-password"><i class="fa fa-lock"></i> 비밀번호</label>
                <input type="password" id="login-password" name="userPassword" class="form-control" placeholder="비밀번호" required>
            </div>

            <button type="submit" class="submit-btn">로그인</button>

            <div class="divider">
                <span class="divider-text">또는</span>
            </div>

            <div class="social-login">
                <div class="social-login-title">소셜 계정으로 로그인</div>
                <div class="social-buttons">
                    <button type="button" class="social-btn naver-btn" onclick="naverLogin()">N</button>
                    <button type="button" class="social-btn kakao-btn" onclick="kakaoLogin()"><i class="fas fa-comment"></i></button>
                </div>
            </div>

            <div class="auth-info">
                <p>아직 계정이 없으신가요? <a href="#" id="go-to-signup">회원가입</a>하고 서비스를 이용하세요.</p>
            </div>
        </form>

        <!-- 회원가입 폼 -->
        <form class="auth-form active" id="signup-form" action="join.do" method="POST">
            <div class="form-group">
                <label for="signup-name"><i class="fa fa-user"></i> 이름</label>
                <input type="text" id="signup-name" name="userName" class="form-control" placeholder="이름" required>
            </div>

            <div class="form-group">
                <label for="signup-email"><i class="fa fa-envelope"></i> 이메일</label>
                <input type="email" id="signup-email" name="userEmail" class="form-control" placeholder="이메일 주소" required>
            </div>

            <div class="form-group">
                <label for="signup-password"><i class="fa fa-lock"></i> 비밀번호</label>
                <input type="password" id="signup-password" name="userPassword" class="form-control" placeholder="비밀번호" required>
            </div>

            <button type="submit" class="submit-btn">회원가입</button>

            <div class="divider">
                <span class="divider-text">또는</span>
            </div>

            <div class="social-login">
                <div class="social-login-title">소셜 계정으로 가입</div>
                <div class="social-buttons">
                    <button type="button" class="social-btn naver-btn" onclick="naverLogin()">N</button>
                    <button type="button" class="social-btn kakao-btn" onclick="kakaoLogin()"><i class="fas fa-comment"></i></button>
                </div>
            </div>

            <div class="auth-info">
                <p>이미 계정이 있으신가요? <a href="#" id="go-to-login">로그인</a>하고 서비스를 이용하세요.</p>
            </div>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/js/SignInUp.js" type="text/javascript"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        $("#signup-email").blur(function() {
            var email = $(this).val();

            if(email != "") {
                console.log("이메일 중복 확인 요청: " + email);  // 디버깅용 로그

                $.ajax({
                    url: "checkEmailDuplicate.do",
                    type: "POST",
                    data: {
                        userEmail: email,
                        socialType: "normal"
                    },
                    dataType: "json",
                    success: function(response) {
                        console.log("서버 응답:", response);  // 디버깅용 로그

                        // 기존 메시지 제거
                        $("#emailCheck").remove();

                        // 새 메시지 추가
                        if(response.available) {
                            $("#signup-email").after('<div id="emailCheck" style="color: green; margin-top: 5px;">' + response.message + '</div>');
                        } else {
                            $("#signup-email").after('<div id="emailCheck" style="color: red; margin-top: 5px;">' + response.message + '</div>');
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error("이메일 중복 확인 중 오류 발생:", error);
                        console.error("상태 코드:", xhr.status);
                        console.error("응답 텍스트:", xhr.responseText);

                        // 기존 메시지 제거
                        $("#emailCheck").remove();

                        // 오류 메시지 추가
                        $("#signup-email").after('<div id="emailCheck" style="color: red; margin-top: 5px;">서버 오류가 발생했습니다. 다시 시도해주세요.</div>');
                    }
                });
            }
        });
    });

</script>
</body>
</html>
