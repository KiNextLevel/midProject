<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8"/>
    <title>마이페이지 | Next Level</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/MyPage.css" rel="stylesheet">
    <!-- 메타 태그 -->
    <meta content="Next Level - 마이페이지" name="description"/>
    <meta content="dating, social, next level" name="keywords"/>
    <meta content="Next Level" name="author"/>

    <link rel="shortcut icon" href="favicon.ico"/>
    <!-- 폰트 및 스타일시트 링크들 (원본과 동일) -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
          rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/components.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/style-shop.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style-responsive.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color"/>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/custom.css" rel="stylesheet"/>
</head>
<body class="ecommerce">
<!-- BEGIN TOP BAR -->
<div class="pre-header">
    <div class="container">
        <div class="row">
            <!-- BEGIN TOP BAR LEFT PART -->
            <div class="col-md-6 col-sm-6 additional-shop-info">
                <ul class="list-unstyled list-inline">
                    <li><i class="fa fa-phone"></i><span>010 - 1234 - 1234</span></li>
                    <!-- BEGIN LANGS -->
                    <li class="langs-block">
                        <a href="productPage.do" class="current"> 플러스샵 </a>
                    </li>
                    <!-- END LANGS -->
                </ul>
            </div>
            <!-- END TOP BAR LEFT PART -->
            <!-- BEGIN TOP BAR MENU -->
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
                    <c:if test="${userRole==1}">
                        <li><a href="adminPage.do">관리자페이지</a></li>
                    </c:if>
                    <li><a href="myPage.do">마이페이지</a></li>
                    <li>메시지</li>
                    <li><a href="logout.do">로그아웃</a></li>
                </ul>
            </div>
            <!-- END TOP BAR MENU -->
        </div>
    </div>
</div>
<!-- END TOP BAR -->

<!-- BEGIN HEADER -->
<div class="header">
    <div class="container">
        <a class="site-logo" href="mainPage.do"><img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png" alt="mainPage"></a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN TOP BAR MENU -->
        <div class="top-bar-right">
            <ul class="top-bar-list">
            </ul>
        </div>
        <!-- END TOP BAR MENU -->
    </div>
</div>
<!-- Header END -->

<div class="main">

    <div class="container">
        <!-- 경로 표시 -->
        <ul class="breadcrumb">
            <li><a href="mainPage.do">홈</a></li>
            <li><a href="">마이페이지</a></li>
            <li class="active">내 정보</li>
        </ul>

        <!-- 메인 콘텐츠 -->
        <div class="row margin-bottom-40">
            <!-- 사이드바 메뉴 -->
            <div class="sidebar col-md-3 col-sm-3">
                <ul class="list-group margin-bottom-25 sidebar-menu">
                    <li class="list-group-item clearfix active"><a href="#basic-info"><i class="fas fa-user"></i> 기본 정보</a>
                    </li>
                    <li class="list-group-item clearfix"><a href="#additional-info"><i class="fa fa-heart"></i> 추가
                        정보</a></li>
                    <li class="list-group-item clearfix"><a href="#preference-info"><i class="fa fa-calendar"></i> 선호 취향</a>
                    </li>
                    <li class="list-group-item clearfix"><a href="#event-info"><i class="fa fa-coins"></i> 참가 중인 이벤트</a>
                    </li>
                    <li class="list-group-item clearfix"><a href="#token-info"><i class="fa fa-cog"></i> 토큰 잔액 및 구매 내역</a></li>
                    <li class="list-group-item clearfix"><a href="#delete-info"><i class="fa fa-cog"></i> 회원 탈퇴</a></li>

                </ul>
            </div>

            <!-- 메인 콘텐츠 영역 -->
            <div class="col-md-9 col-sm-9">
                <div class="main-content">
                    <h1>마이 페이지</h1>

                    <!-- 프로필 섹션 -->
                    <div class="profile-container">
                        <div class="profile-header">
                            <div class="profile-left">
                                <div class="profile-image-container">
                                    <!-- 프로필 이미지 업로드를 위한 폼 수정 -->
                                    <form id="profileForm" action="updateProfileImage.do" method="POST"
                                          enctype="multipart/form-data">
                                        <input type="file" id="profileUpload" name="profileImage" accept="image/*"
                                               onchange="this.form.submit();"/>
                                    </form>

                                    <label for="profileUpload">
                                        <c:choose>
                                            <c:when test="${not empty userDTO.userProfile}">
                                                <img id="profileImage" src="${userDTO.userProfile}" alt="프로필 이미지"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img id="profileImage" src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/img/logos/3.png"
                                                     alt="기본 프로필 이미지"/>
                                            </c:otherwise>
                                        </c:choose>

                                        <div class="image-overlay">
                                            <i class="fa fa-camera"></i> <span>사진 변경</span>
                                        </div>
                                    </label>
                                </div>
                                <div class="profile-info">
                                    <h3 class="profile-name">
                                        <c:out value="${userDTO.userName}" default="닉네임"/>
                                    </h3>
                                    <span class="profile-role">일반 회원</span>
                                </div>
                            </div>
                            <div class="profile-actions">
                                <a href="updateProfilePage.do" class="btn btn-primary">
                                    <i class="fa fa-edit"></i> 정보 수정
                                </a>
                            </div>
                        </div>
                    </div>
                    <!-- 기본 정보 카드 -->
                    <div id="basic-info" class="info-card">
                        <h3>기본 정보</h3>
                        <div class="info-list">
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-comment"></i>
                                </div>
                                <div class="info-label">자기소개</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userDescription}">
                                            <c:out value="${userDTO.userDescription}"/>
                                        </c:when>
                                        <c:otherwise>
                                            자기소개를 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-user"></i>
                                </div>
                                <div class="info-label">이름</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userName}">
                                            <c:out value="${userDTO.userName}"/>
                                        </c:when>
                                        <c:otherwise>
                                            이름을 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-phone"></i>
                                </div>
                                <div class="info-label">전화번호</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userPhone}">
                                            <c:out value="${userDTO.userPhone}"/>
                                        </c:when>
                                        <c:otherwise>
                                            전화번호를 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-venus-mars"></i>
                                </div>
                                <div class="info-label">성별</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${userDTO.userGender}">
                                            여성
                                        </c:when>
                                        <c:otherwise>
                                            남성
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-birthday-cake"></i>
                                </div>
                                <div class="info-label">생년월일</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userBirth}">
                                            <c:out value="${userDTO.userBirth}"/>
                                        </c:when>
                                        <c:otherwise>
                                            생년월일을 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>

                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-ruler"></i>
                                </div>
                                <div class="info-label">키</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userHeight}">
                                            <c:out value="${userDTO.userHeight}"/> cm
                                        </c:when>
                                        <c:otherwise>
                                            0 cm
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 추가 정보 카드 -->
                    <div id="additional-info" class="info-card">
                        <h3>추가 정보</h3>
                        <div class="info-list">
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-weight"></i>
                                </div>
                                <div class="info-label">체형</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userBody}">
                                            <c:out value="${userDTO.userBody}"/>
                                        </c:when>
                                        <c:otherwise>
                                            체형을 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-graduation-cap"></i>
                                </div>
                                <div class="info-label">학력</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userEducation}">
                                            <c:out value="${userDTO.userEducation}"/>
                                        </c:when>
                                        <c:otherwise>
                                            학력을 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-map-marker"></i>
                                </div>
                                <div class="info-label">지역</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userRegion}">
                                            <c:out value="${userDTO.userRegion}"/>
                                        </c:when>
                                        <c:otherwise>
                                            지역을 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-theater-masks"></i>
                                </div>
                                <div class="info-label">MBTI</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${not empty userDTO.userMbti}">
                                            <c:out value="${userDTO.userMbti}"/>
                                        </c:when>
                                        <c:otherwise>
                                            MBTI를 입력해주세요.
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-wine-glass"></i>
                                </div>
                                <div class="info-label">음주</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${userDTO.userDrink == 0}">전혀 안함</c:when>
                                        <c:when test="${userDTO.userDrink == 1}">가끔</c:when>
                                        <c:when test="${userDTO.userDrink == 2}">자주</c:when>
                                        <c:otherwise>음주 정보를 입력해주세요.</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-smoking"></i>
                                </div>
                                <div class="info-label">흡연</div>
                                <div class="info-value">
                                    <c:choose>
                                        <c:when test="${userDTO.userSmoke}">흡연</c:when>
                                        <c:otherwise>비흡연</c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- 선호 취향 카드 -->
                    <div id="preference-info" class="info-card">
                        <h3>선호 취향</h3>
                        <div class="preference-tags">
                            <span class="preference-tag">
                                <i class="fas fa-ruler"></i>
                                <c:choose>
                                    <c:when test="${not empty preferenceDTO.preferenceHeight}">
                                        <c:out value="${preferenceDTO.preferenceHeight}"/>
                                    </c:when>
                                    <c:otherwise>
                                        키 선호도 미설정
                                    </c:otherwise>
                                </c:choose>
                            </span>
                            <span class="preference-tag">
                                <i class="fas fa-weight"></i>
                                <c:choose>
                                    <c:when test="${not empty preferenceDTO.preferenceBody}">
                                        <c:out value="${preferenceDTO.preferenceBody}"/>
                                    </c:when>
                                    <c:otherwise>
                                        체형 선호도 미설정
                                    </c:otherwise>
                                </c:choose>
                            </span>
                            <span class="preference-tag">
                                <i class="fas fa-birthday-cake"></i>
                                <c:choose>
                                    <c:when test="${not empty preferenceDTO.preferenceAge}">
                                        <c:out value="${preferenceDTO.preferenceAge}"/>
                                    </c:when>
                                    <c:otherwise>
                                        나이 선호도 미설정
                                    </c:otherwise>
                                </c:choose>
                            </span>
                        </div>
                    </div>

                    <!-- 참가중인 이벤트 -->
                    <div id="event-info" class="info-card">
                        <h3>참가 중인 이벤트</h3>
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>이벤트 이름</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:choose>
                                <c:when test="${not empty BoardDTO.boardList}">
                                    <c:forEach var="event" items="${BoardDTO.boardList}">
                                        <tr>
                                            <td><c:out value="${event.boardTitle}"/></td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>참가 중인 이벤트가 없습니다.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                            </tbody>
                        </table>
                    </div>

                    <!-- 토큰 잔액 및 구매 내역 -->
                    <div id="token-info" class="info-card">
                        <h3>토큰 잔액 및 구매 내역</h3>
                        <p>
                            <strong>현재 보유 토큰:</strong>
                            <span style="color: #e94d1c; font-size: 18px;">
                                <c:out value="${userDTO.userToken}" default="0"/> 개
                            </span>
                        </p>

                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>상품명</th>
                                <th>결제일</th>
                            </tr>
                            </thead>
                            <tbody>
                            <!-- 결제 내역 불러와야 함 -->
                            </tbody>
                        </table>
                    </div>

                    <div id="delete-info" class="info-card">
                        <h3>계정 설정</h3>
                        <!-- 계정 설정 -->
                        <p>회원 탈퇴를 원하시면 아래 버튼을 클릭하세요.</p>
                        <a href="AccountDelete.jsp" class="btn btn-danger">
                            <i class="fa fa-user-times"></i> 회원 탈퇴하기
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 자바스크립트 -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/back-to-top.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
<!-- slider for products -->
<script src='${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/zoom/jquery.zoom.min.js' type="text/javascript"></script><!-- product zoom -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>
<!-- Quantity -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme//plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/rateit/src/jquery.rateit.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
<!-- for slider-range -->

<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        // 파일 선택 시 자동으로 폼 제출됨 (onchange="this.form.submit();")

        // 선택적: 파일 선택 전 확인 메시지 표시
        $('.profile-image-container').click(function () {
            if (confirm('프로필 이미지를 변경하시겠습니까?')) {
                $('#profileUpload').click();
            }
        });

        // 사이드바 메뉴 클릭 시 부드러운 스크롤 이동
        $('.sidebar-menu a').click(function (e) {
            e.preventDefault(); // 기본 링크 동작 방지

            // 모든 메뉴 항목에서 active 클래스 제거
            $('.sidebar-menu li').removeClass('active');

            // 클릭한 메뉴 항목에 active 클래스 추가
            $(this).parent('li').addClass('active');

            // 대상 요소의 ID 가져오기
            var targetId = $(this).attr('href');

            // 대상 요소의 위치 계산 (상단에서 약간 여백 추가)
            var targetPosition = $(targetId).offset().top - 20;

            // 부드러운 스크롤 애니메이션
            $('html, body').animate({
                scrollTop: targetPosition
            }, 800); // 800ms 동안 애니메이션 실행
        });

        // 스크롤 시 현재 보고 있는 섹션에 따라 사이드바 메뉴 활성화
        $(window).scroll(function () {
            var scrollPosition = $(window).scrollTop();

            // 각 섹션을 확인하여 현재 보이는 섹션의 메뉴 활성화
            $('.info-card').each(function () {
                var currentSection = $(this);
                var sectionTop = currentSection.offset().top - 100; // 약간의 오프셋 적용
                var sectionId = '#' + currentSection.attr('id');

                if (scrollPosition >= sectionTop) {
                    $('.sidebar-menu li').removeClass('active');
                    $('.sidebar-menu a[href="' + sectionId + '"]').parent('li').addClass('active');
                }
            });
        });
    });
    function confirmDelete() {
        if(confirm('정말로 탈퇴하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
            window.location.href = 'deleteAccount.do';
        }
    }
</script>
</body>
</html>
