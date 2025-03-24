<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.example.webapp.model.dto.UserDTO" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8" />
    <title> 마이페이지 | Next Level</title>
    <link href="css/MyPage.css" rel="stylesheet">
    <link href="css/ProductPage.css" rel="stylesheet">
    <link href="css/MyPage2.css" rel="stylesheet">
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <!-- 메타 태그 -->
    <meta content="Next Level - 마이페이지" name="description" />
    <meta content="dating, social, next level" name="keywords" />
    <meta content="Next Level" name="author" />

    <link rel="shortcut icon" href="favicon.ico" />
    <script src="http://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script>
    <!-- 폰트 -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css" />

    <!-- FontAwesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />

    <!-- 글로벌 스타일 -->
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" />

    <!-- 테마 스타일 -->
    <link href="assets/pages/css/components.css" rel="stylesheet" />
    <link href="assets/corporate/css/style.css" rel="stylesheet" />
    <link href="assets/pages/css/style-shop.css" rel="stylesheet" type="text/css" />
    <link href="assets/corporate/css/style-responsive.css" rel="stylesheet" />
    <link href="assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color" />
    <link href="assets/corporate/css/custom.css" rel="stylesheet" />

</head>
<!-- BEGIN STYLE CUSTOMIZER -->
<div class="color-panel hidden-sm">
    <div class="color-mode-icons icon-color"></div>
    <div class="color-mode-icons icon-color-close"></div>
    <div class="color-mode">
        <p>THEME COLOR</p>
        <ul class="inline">
            <li class="color-red current color-default" data-style="red"></li>
            <li class="color-blue" data-style="blue"></li>
            <li class="color-green" data-style="green"></li>
            <li class="color-orange" data-style="orange"></li>
            <li class="color-gray" data-style="gray"></li>
            <li class="color-turquoise" data-style="turquoise"></li>
        </ul>
    </div>
</div>
<!-- END BEGIN STYLE CUSTOMIZER -->

<!-- BEGIN TOP BAR -->
<div class="pre-header">
    <div class="container">
        <div class="row">
            <!-- BEGIN TOP BAR LEFT PART -->
            <div class="col-md-6 col-sm-6 additional-shop-info">
            </div>
            <!-- END TOP BAR LEFT PART -->
            <!-- BEGIN TOP BAR MENU -->
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
                    <li><a href="mainPage.do">마이페이지</a></li>
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
        <a class="site-logo" href="shop-index.html"><img src="assets/corporate/img/logos/3.png"
                                                         alt="Metronic Shop UI"></a>

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
            <li><a href="index.html">홈</a></li>
            <li><a href="">마이페이지</a></li>
            <li class="active">내 정보</li>
        </ul>

        <!-- 메인 콘텐츠 -->
        <div class="row margin-bottom-40">
            <!-- 사이드바 메뉴 -->
            <div class="sidebar col-md-3 col-sm-3">
                <ul class="list-group margin-bottom-25 sidebar-menu">
                    <li class="list-group-item clearfix active">
                        <a href="javascript:;"><i class="fa fa-user"></i> 내 정보</a>
                    </li>
                    <li class="list-group-item clearfix">
                        <a href="javascript:;"><i class="fa fa-heart"></i> 관심 목록</a>
                    </li>
                    <li class="list-group-item clearfix">
                        <a href="javascript:;"><i class="fa fa-calendar"></i> 이벤트 참가 내역</a>
                    </li>
                    <li class="list-group-item clearfix">
                        <a href="javascript:;"><i class="fa fa-coins"></i> 토큰 관리</a>
                    </li>
                    <li class="list-group-item clearfix">
                        <a href="javascript:;"><i class="fa fa-cog"></i> 계정 설정</a>
                    </li>
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
                                    <label for="profileUpload">
                                        <img id="profileImage" src="assets/img/profile-default.png" alt="프로필 이미지" />
                                        <div class="image-overlay">
                                            <i class="fa fa-camera"></i>
                                            <span>사진 변경</span>
                                        </div>
                                    </label>
                                    <input type="file" id="profileUpload" accept="image/*" />
                                </div>
                                <div class="profile-info">
                                    <h3 class="profile-name">닉네임</h3>
                                    <span class="profile-role">일반 회원</span>
                                </div>
                            </div>
                            <div class="profile-actions">
                                <a href="/theme/MyPageEdit.jsp" class="btn btn-primary">
                                    <i class="fa fa-edit"></i> 정보 수정
                                </a>
                            </div>
                        </div>
                    </div>


                    <!-- 기본 정보 카드 -->
                    <div class="info-card">
                        <h3>기본 정보</h3>
                        <div class="info-list">
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-comment"></i>
                                </div>
                                <div class="info-label">자기소개</div>
                                <div class="info-value">${userDTO.userDescription != null ? userDTO.userDescription : '자기소개를 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-user"></i>
                                </div>
                                <div class="info-label">이름</div>
                                <div class="info-value">${userDTO.userName != null ? userDTO.userName : '이름을 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-phone"></i>
                                </div>
                                <div class="info-label">전화번호</div>
                                <div class="info-value">${userDTO.userPhone != null ? userDTO.userPhone : '전화번호를 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-venus-mars"></i>
                                </div>
                                <div class="info-label">성별</div>
                                <div class="info-value">${userDTO.userGender ? '여성' : '남성'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-birthday-cake"></i>
                                </div>
                                <div class="info-label">생년월일</div>
                                <div class="info-value">${userDTO.userBirth != null ? userDTO.userBirth : '생년월일을 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-ruler"></i>
                                </div>
                                <div class="info-label">키</div>
                                <div class="info-value">${userDTO.userHeight != null ? userDTO.userHeight : '0'} cm</div>
                            </div>
                        </div>
                    </div>

                    <!-- 추가 정보 카드 -->
                    <div class="info-card">
                        <h3>추가 정보</h3>
                        <div class="info-list">
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-weight"></i>
                                </div>
                                <div class="info-label">체형</div>
                                <div class="info-value">${userDTO.userBody != null ? userDTO.userBody : '체형을 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-graduation-cap"></i>
                                </div>
                                <div class="info-label">학력</div>
                                <div class="info-value">${userDTO.userEducation != null ? userDTO.userEducation : '학력을 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fa fa-map-marker"></i>
                                </div>
                                <div class="info-label">지역</div>
                                <div class="info-value">${userDTO.userRegion != null ? userDTO.userRegion : '지역을 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-theater-masks"></i>
                                </div>
                                <div class="info-label">MBTI</div>
                                <div class="info-value">${userDTO.userMbti != null ? userDTO.userMbti : 'MBTI를 입력해주세요.'}</div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-wine-glass"></i>
                                </div>
                                <div class="info-label">음주</div>
                                <div class="info-value">
                                    ${userDTO.userDrink == 0 ? '전혀 안함' : userDTO.userDrink == 1 ? '가끔' : userDTO.userDrink == 2 ? '자주' : '음주 정보를 입력해주세요.'}
                                </div>
                            </div>
                            <div class="info-item">
                                <div class="info-icon">
                                    <i class="fas fa-smoking"></i>
                                </div>
                                <div class="info-label">흡연</div>
                                <div class="info-value">${userDTO.userSmoke ? '흡연' : '비흡연'}</div>
                            </div>
                        </div>
                    </div>

                    <!-- 선호 취향 카드 -->
                    <div class="info-card">
                        <h3>선호 취향</h3>
                        <div class="preference-tags">
                            <span class="preference-tag"><i class="fas fa-ruler"></i> ${PreferenceDTO.preferenceHeight != null ? PreferenceDTO.preferenceHeight : '키 선호도 미설정'}</span>
                            <span class="preference-tag"><i class="fas fa-weight"></i> ${PreferenceDTO.preferenceBody != null ? PreferenceDTO.preferenceBody : '체형 선호도 미설정'}</span>
                            <span class="preference-tag"><i class="fas fa-birthday-cake"></i> ${PreferenceDTO.preferenceAge != null ? PreferenceDTO.preferenceAge : '나이 선호도 미설정'}</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 푸터 영역 -->
<div class="pre-footer">
    <div class="container">
        <div class="row">
            <!-- 푸터 내용 -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Next Level</h2>
                <p>우리는 인연과 연인을 중시합니다.</p>
            </div>
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Our Contacts</h2>
                <address class="margin-bottom-40">
                    서울 강남구 테헤란로26길 12<br />
                    (우) 06236 (지번) 역삼동 736-56<br />
                    Email: <a href="mailto:0414minyoung@naver.com">0414minyoung@naver.com</a>
                </address>
            </div>
        </div>
    </div>
</div>

<!-- 자바스크립트 -->
<script src="assets/plugins/jquery.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/MyPageUploadProfile.js"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();
        Layout.initImageZoom();
        Layout.initTouchspin();
        Layout.initUniform();

        // 슬라이더 직접 초기화
        $("#age-slider-range").slider({
            range: true,
            min: 18,
            max: 60,
            values: [18, 35],
            slide: function (event, ui) {
                $("#ageAmount").val(ui.values[0] + " - " + ui.values[1] + "세");
            }
        });
        $("#ageAmount").val($("#age-slider-range").slider("values", 0) + " - " +
            $("#age-slider-range").slider("values", 1) + "세");

        $("#height-slider-range").slider({
            range: true,
            min: 150,
            max: 200,
            values: [160, 180],
            slide: function (event, ui) {
                $("#heightAmount").val(ui.values[0] + " - " + ui.values[1] + "cm");
            }
        });
        $("#heightAmount").val($("#height-slider-range").slider("values", 0) + " - " +
            $("#height-slider-range").slider("values", 1) + "cm");
    });
</script>

</body>
</html>
