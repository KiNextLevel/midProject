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
    <style>
        /* 비활성화된 버튼 스타일 */
        .submit-btn:disabled {
            background-color: #cccccc;
            color: #666666;
            cursor: not-allowed;
        }
    </style>
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
                <div class="email-input-group">
                    <input type="email" id="signup-email" name="userEmail" class="form-control" placeholder="이메일 주소" required>
                    <button type="button" class="email-check-btn" onclick="checkEmailDuplicate()">중복 확인</button>
                </div>
                <div id="emailCheckResult"></div>
                <input type="hidden" id="emailVerified" name="emailVerified" value="false">
            </div>

            <div class="form-group">
                <label for="signup-password"><i class="fa fa-lock"></i> 비밀번호</label>
                <input type="password" id="signup-password" name="userPassword" class="form-control" placeholder="비밀번호" required>
            </div>

            <!-- 회원가입 버튼 - 초기에 비활성화 상태로 설정 -->
            <button type="submit" id="signup-submit-btn" class="submit-btn" disabled>회원가입</button>

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
    // 페이지 로드 시 실행
    $(document).ready(function() {
        // 이메일 입력란에 변경 이벤트 리스너 추가
        $('#signup-email').on('input', function() {
            // 이메일이 변경되면 버튼 비활성화 및 확인 결과 초기화
            $('#signup-submit-btn').prop('disabled', true);
            $('#emailVerified').val('false');
            $('#emailCheckResult').html('');
        });
    });

    function checkEmailDuplicate() {
        // 이메일 입력 필드의 ID를 HTML과 일치시킴
        const userEmail = document.getElementById('signup-email').value;

        // 이메일이 비어있는지 확인
        if (!userEmail) {
            alert('이메일을 입력해주세요.');
            return;
        }

        // 이메일 형식 검증
        const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        if (!emailRegex.test(userEmail)) {
            alert('올바른 이메일 형식이 아닙니다.');
            return;
        }

        // 소셜 타입 가져오기 (소셜 로그인 여부에 따라 다르게 설정)
        const socialType = 'normal'; // 기본값으로 normal 설정

        console.log("이메일 중복 체크 요청:", userEmail);

        // AJAX 요청
        $.ajax({
            url: '/checkEmailDuplicate.do', // 서블릿 URL
            type: 'POST',
            data: {
                userEmail: userEmail,
                socialType: socialType
            },
            success: function(response) {
                console.log("서버 응답:", response);

                if (response.isDuplicate) {
                    // 중복된 이메일인 경우
                    alert(response.message);
                    document.getElementById('emailCheckResult').innerHTML =
                        '<span style="color: red;">이미 사용 중인 이메일입니다.</span>';
                    document.getElementById('emailVerified').value = 'false';
                    // 회원가입 버튼 비활성화
                    document.getElementById('signup-submit-btn').disabled = true;
                } else {
                    // 사용 가능한 이메일인 경우
                    alert(response.message);
                    document.getElementById('emailCheckResult').innerHTML =
                        '<span style="color: green;">사용 가능한 이메일입니다.</span>';
                    document.getElementById('emailVerified').value = 'true';
                    // 회원가입 버튼 활성화
                    document.getElementById('signup-submit-btn').disabled = false;
                }
            },
            error: function(xhr, status, error) {
                console.error('이메일 중복 체크 중 오류 발생:', error);
                console.error('상태 코드:', xhr.status);
                console.error('응답 텍스트:', xhr.responseText);
                alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
                // 오류 발생 시 버튼 비활성화
                document.getElementById('signup-submit-btn').disabled = true;
            }
        });
    }
</script>

</body>
</html>
