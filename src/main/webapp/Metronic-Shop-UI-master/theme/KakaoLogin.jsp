<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>카카오 로그인</title>
  <script src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.4/kakao.min.js"
          integrity="sha384-DKYJZ8NLiK8MN4/C5P2dtSmLQ4KwPaoqAfyA/DfmEc1VDxu4yyC7wy6K1Hs90nka" crossorigin="anonymous"></script>
</head>
<body>

<%--이메일 보내기 버튼--%>
<%--<button id="email-btn" >이메일 보내기</button>--%>
<form action="sendEmail.do" method="POST">
  <input type="email" name="toEmail" placeholder="Recipient's Email" required />
  <button type="submit">Send Email</button>
</form>
<%--http://localhost:8088/Metronic-Shop-UI-master/theme/sendEmail.do--%>
<%--clientid = 946591518832-1mcia4a01goc994auf5tjjomo7updi0c.apps.googleusercontent.com--%>
<%--clientPw = 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--%>


<!-- 카카오 로그인 이미지 버튼 -->
<img src="img/kakao_login_medium_wide.png" id="kakao-login-btn" alt="카카오 로그인 버튼" style="cursor: pointer;">

<!-- 카카오 계정 연결 끊기 버튼 -->
<button id="unlink-btn" >카카오 계정 연결 끊기</button>

<script type="text/javascript">
  // 카카오 SDK 초기화 (앱 키 설정)
  Kakao.init('25a9dbf40f7886253bc52cd3038dab93');

  // 카카오 로그인 버튼 클릭 시 로그인 처리
  document.getElementById('kakao-login-btn').onclick = function() {
    // 카카오 로그인 요청
    Kakao.Auth.authorize({
      redirectUri: 'http://localhost:8088/Metronic-Shop-UI-master/theme/kakaoCallBack.do'  // 리다이렉트 URI 설정
    });
  };

  // 카카오 로그인 상태 확인
  Kakao.Auth.getStatus(function(response) {
    if (response.status === 'connected') {
      // 로그인 된 상태면 연결 끊기 버튼 보이기
      document.getElementById('unlink-btn').style.display = 'block';
    }
  });

  // 카카오 계정 연결 끊기 버튼 클릭 시 연결 끊기 처리
  document.getElementById('unlink-btn').onclick = function() {
    Kakao.API.request({
      url: '/v1/user/unlink', // 연결 끊기 API 호출
      success: function(response) {
        alert("카카오 계정 연결이 끊어졌습니다.");
        document.getElementById('unlink-btn').style.display = 'none'; // 연결 끊기 후 버튼 숨기기
      },
      fail: function(error) {
        console.error("연결 끊기 실패:", error);
        alert("연결 끊기에 실패했습니다.");
      }
    });
  };
</script>

</body>
</html>
