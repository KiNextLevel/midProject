<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <title>정보 수정 페이지</title>

    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta content="Metronic Shop UI description" name="description">
    <meta content="Metronic Shop UI keywords" name="keywords">
    <meta content="keenthemes" name="author">

    <meta property="og:site_name" content="-CUSTOMER VALUE-">
    <meta property="og:title" content="-CUSTOMER VALUE-">
    <meta property="og:description" content="-CUSTOMER VALUE-">
    <meta property="og:type" content="website">
    <meta property="og:image" content="-CUSTOMER VALUE-">
    <!-- link to image for socio -->
    <meta property="og:url" content="-CUSTOMER VALUE-">

    <link rel="shortcut icon" href="favicon.ico">

    <!-- Fonts START -->
    <link
            href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
            rel="stylesheet" type="text/css">
    <!-- Fonts END -->

    <!-- Global styles START -->
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css"
          rel="stylesheet">
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">
    <!-- Global styles END -->

    <!-- Page level plugin styles START -->
    <link href="assets/plugins/fancybox/source/jquery.fancybox.css"
          rel="stylesheet">
    <link href="assets/plugins/owl.carousel/assets/owl.carousel.css"
          rel="stylesheet">
    <link href="assets/plugins/uniform/css/uniform.default.css"
          rel="stylesheet" type="text/css">
    <!-- Page level plugin styles END -->

    <!-- Theme styles START -->
    <link href="assets/pages/css/components.css" rel="stylesheet">
    <link href="assets/corporate/css/style.css" rel="stylesheet">
    <link href="assets/pages/css/style-shop.css" rel="stylesheet"
          type="text/css">
    <link href="assets/corporate/css/style-responsive.css" rel="stylesheet">
    <link href="assets/corporate/css/themes/red.css" rel="stylesheet"
          id="style-color">
    <link href="assets/corporate/css/custom.css" rel="stylesheet">
    <!-- Theme styles END -->
</head>
<body class="ecommerce">
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
                    <li><a href="myPage.do">마이페이지</a></li>
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
        <a class="site-logo" href="mainPage.do"><img src="assets/corporate/img/logos/3.png"
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

<form action="updateProfile.do" method="POST" role="form" class="form-horizontal form-without-legend">
    <div class="container">
        <h1>정보 변경하기</h1>

        <!-- 자기소개 -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="userDescription">자기소개</label>
            <div class="col-md-8">
                <textarea class="form-control" id="userDescription" name="userDescription" rows="6" maxlength="200"><c:out value="${userDTO.userDescription}"/></textarea>
                <small id="charCount">0 / 200</small>
            </div>
        </div>

        <!-- 키 선택 -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="height">키 <span class="require">*</span></label>
            <div class="col-md-8">
                <input type="number" id="height" name="userHeight" class="form-control"
                       value="<c:out value="${userDTO.userHeight}"/>" placeholder="cm 단위로 입력하세요" step="0.01" min="100" max="200">
            </div>
        </div>

        <!-- 체형 선택 -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="bodyType">체형 <span class="require">*</span></label>
            <div class="col-md-8">
                <select id="bodyType" name="userBody" class="form-control">
                    <option value="">선택하세요</option>
                    <c:forEach var="bodyType" items="${['마름', '보통', '통통', '뚱뚱']}">
                        <option value="${bodyType}" <c:if test="${userDTO.userBody == bodyType}">selected</c:if>>${bodyType}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- 학력 선택 -->
        <div class="form-group">
            <label class="col-md-2 control-label">학력 <span class="require">*</span></label>
            <div class="col-md-8">
                <select name="userEducation" class="form-control">
                    <option value="">선택하세요</option>
                    <c:forEach var="education" items="${['고등학교 졸업', '전문대', '대학교 재학', '대학교 졸업', '대학원 재학', '대학원 졸업']}">
                        <option value="${education}" <c:if test="${userDTO.userEducation == education}">selected</c:if>>${education}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- 직업 입력 -->
        <div class="form-group">
            <label class="col-md-2 control-label" for="job">직업 <span class="require">*</span></label>
            <div class="col-md-8">
                <input type="text" id="job" name="userJob" class="form-control"
                       value="<c:out value="${userDTO.userJob}"/>" placeholder="직업을 입력하세요">
            </div>
        </div>

        <!-- 종교 선택 -->
        <div class="form-group">
            <label class="col-md-2 control-label">종교 <span class="require">*</span></label>
            <div class="col-md-8">
                <select name="userReligion" class="form-control">
                    <option value="">선택하세요</option>
                    <option value="무교" <c:if test="${userDTO.userReligion == '무교'}">selected</c:if>>무교</option>
                    <option value="기독교" <c:if test="${userDTO.userReligion == '기독교'}">selected</c:if>>기독교</option>
                    <option value="천주교" <c:if test="${userDTO.userReligion == '천주교'}">selected</c:if>>천주교</option>
                    <option value="불교" <c:if test="${userDTO.userReligion == '불교'}">selected</c:if>>불교</option>
                    <option value="이슬람교" <c:if test="${userDTO.userReligion == '이슬람교'}">selected</c:if>>이슬람교</option>
                    <option value="기타" <c:if test="${userDTO.userReligion == '기타'}">selected</c:if>>기타</option>
                </select>
            </div>
        </div>

        <!-- 지역 선택 -->
        <div class="form-group">
            <label class="col-md-2 control-label">지역 <span class="require">*</span></label>
            <div class="col-md-8">
                <select class="form-control" name="userRegion">
                    <option value="">선택하세요</option>
                    <option value="서울" <c:if test="${userDTO.userRegion == '서울'}">selected</c:if>>서울</option>
                    <option value="경기" <c:if test="${userDTO.userRegion == '경기'}">selected</c:if>>경기도</option>
                    <option value="인천" <c:if test="${userDTO.userRegion == '인천'}">selected</c:if>>인천</option>
                    <option value="부산" <c:if test="${userDTO.userRegion == '부산'}">selected</c:if>>부산</option>
                    <option value="대구" <c:if test="${userDTO.userRegion == '대구'}">selected</c:if>>대구</option>
                    <option value="광주" <c:if test="${userDTO.userRegion == '광주'}">selected</c:if>>광주</option>
                    <option value="대전" <c:if test="${userDTO.userRegion == '대전'}">selected</c:if>>대전</option>
                    <option value="울산" <c:if test="${userDTO.userRegion == '울산'}">selected</c:if>>울산</option>
                    <option value="세종" <c:if test="${userDTO.userRegion == '세종'}">selected</c:if>>세종</option>
                    <option value="강원" <c:if test="${userDTO.userRegion == '강원'}">selected</c:if>>강원</option>
                    <option value="충북" <c:if test="${userDTO.userRegion == '충북'}">selected</c:if>>충북</option>
                    <option value="충남" <c:if test="${userDTO.userRegion == '충남'}">selected</c:if>>충남</option>
                    <option value="전북" <c:if test="${userDTO.userRegion == '전북'}">selected</c:if>>전북</option>
                    <option value="전남" <c:if test="${userDTO.userRegion == '전남'}">selected</c:if>>전남</option>
                    <option value="경북" <c:if test="${userDTO.userRegion == '경북'}">selected</c:if>>경북</option>
                    <option value="경남" <c:if test="${userDTO.userRegion == '경남'}">selected</c:if>>경남</option>
                    <option value="제주" <c:if test="${userDTO.userRegion == '제주'}">selected</c:if>>제주</option>
                </select>
            </div>
        </div>


        <!-- MBTI 선택 -->
        <div class="form-group">
            <label class="col-md-2 control-label">MBTI <span class="require">*</span></label>
            <div class="col-md-8">
                <select class="form-control" name="userMbti">
                    <option value="">선택하세요</option>
                    <c:forEach var="mbti" items="${['ISTJ', 'ISFJ', 'INFJ', 'INTJ', 'ISTP', 'ISFP', 'INFP', 'INTP', 'ESTP', 'ESFP', 'ENFP', 'ENTP', 'ESTJ', 'ESFJ', 'ENFJ', 'ENTJ']}">
                        <option value="${mbti}" <c:if test="${userDTO.userMbti == mbti}">selected</c:if>>${mbti}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- 음주 여부 -->
        <div class="form-group">
        <label class="col-md-2 control-label">음주 <span class="require">*</span></label>
        <div class="col-md-8">
            <select class="form-control" name="userDrink">
                <option value="">선택하세요</option>
                <option value="0" <c:if test="${userDTO.userDrink == 0}">selected</c:if>>전혀 안 함</option>
                <option value="1" <c:if test="${userDTO.userDrink == 1}">selected</c:if>>가끔 마심</option>
                <option value="2" <c:if test="${userDTO.userDrink == 2}">selected</c:if>>자주 마심</option>
            </select>
        </div>
    </div>


        <!-- 흡연 여부 -->
        <div class="form-group">
            <label class="col-md-2 control-label">흡연 <span class="require">*</span></label>
            <div class="col-md-8">
                <select class="form-control" name="userSmoke">
                    <option value="">선택하세요</option>
                    <option value="false" <c:if test="${userDTO.userSmoke == false}">selected</c:if>>비흡연</option>
                    <option value="true" <c:if test="${userDTO.userSmoke == true}">selected</c:if>>흡연</option>
                </select>
            </div>
        </div>

        <hr>
        <!-- 선호 키 -->
        <div class="form-group">
            <label class="col-md-2 control-label">선호 키 <span class="require">*</span></label>
            <div class="col-md-8">
                <select name="preferenceHeight" class="form-control">
                    <option value="">선택하세요</option>
                    <c:forEach var="height" items="${['150cm 이하', '150cm ~ 155cm', '155cm ~ 160cm', '160cm ~ 165cm', '165cm ~ 170cm', '170cm ~ 175cm', '175cm ~ 180cm', '180cm 이상']}">
                        <option value="${height}" <c:if test="${preferenceDTO.preferenceHeight == height}">selected</c:if>>${height}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- 선호 체형 -->
        <div class="form-group">
            <label class="col-md-2 control-label">선호 체형 <span class="require">*</span></label>
            <div class="col-md-8">
                <select name="preferenceBody" class="form-control">
                    <option value="">선택하세요</option>
                    <c:forEach var="body" items="${['마름', '보통', '통통', '뚱뚱']}">
                        <option value="${body}" <c:if test="${preferenceDTO.preferenceBody == body}">selected</c:if>>${body}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <!-- 선호 나이 -->
        <div class="form-group">
            <label class="col-md-2 control-label">선호 나이 <span class="require">*</span></label>
            <div class="col-md-8">
                <select name="preferenceAge" class="form-control">
                    <option value="">선택하세요</option>
                    <c:set var="ageGroups" value="${[
                        {'value': 'early20', 'label': '20대 초반'},
                        {'value': 'mid20', 'label': '20대 중반'},
                        {'value': 'late20', 'label': '20대 후반'},
                        {'value': 'early30', 'label': '30대 초반'},
                        {'value': 'mid30', 'label': '30대 중반'},
                        {'value': 'late30', 'label': '30대 후반'},
                        {'value': 'early40', 'label': '40대 초반'},
                        {'value': 'mid40', 'label': '40대 중반'},
                        {'value': 'late40', 'label': '40대 후반'},
                        {'value': 'early50', 'label': '50대 초반'},
                        {'value': 'mid50', 'label': '50대 중반'},
                        {'value': 'late50', 'label': '50대 후반'},
                        {'value': 'early60', 'label': '60대 초반'},
                        {'value': 'mid60', 'label': '60대 중반'},
                        {'value': 'late60', 'label': '60대 후반'},
                        {'value': 'early70', 'label': '70대 초반'},
                        {'value': 'mid70', 'label': '70대 중반'},
                        {'value': 'late70', 'label': '70대 후반'},
                        {'value': 'early80', 'label': '80대 초반'},
                        {'value': 'mid80', 'label': '80대 중반'},
                        {'value': 'late80', 'label': '80대 후반'},
                        {'value': 'early90', 'label': '90대 초반'},
                        {'value': 'mid90', 'label': '90대 중반'},
                        {'value': 'late90', 'label': '90대 후반'},
                        {'value': '100plus', 'label': '100세 이상'}
                    ]}" />

                    <c:forEach var="age" items="${ageGroups}">
                        <option value="${age.value}" <c:if test="${preferenceDTO.preferenceAge == age.label}">selected</c:if>>${age.label}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <!-- 버튼 (제출) -->
        <div class="form-group">
            <div class="col-md-8 col-md-offset-2">
                <button class="btn btn-primary" type="submit">정보 저장</button>
            </div>
        </div>
    </div>
</form>
<!-- BEGIN PRE-FOOTER -->
<div class="pre-footer">
    <div class="container">
        <div class="row">
            <!-- BEGIN BOTTOM ABOUT BLOCK -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Next Level</h2>
                <p>우리는 인연과 연인인을 중시합니다.</p>
            </div>
            <!-- END BOTTOM ABOUT BLOCK -->

            <!-- BEGIN BOTTOM INFO BLOCK -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Information</h2>
            </div>
            <!-- END INFO BLOCK -->

            <!-- BEGIN TWITTER BLOCK -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2 class="margin-bottom-0">Github</h2>
                <a class="twitter-timeline" href="https://github.com/KiNextLevel" data-tweet-limit="2" data-theme="dark"
                   data-link-color="#57C8EB" data-widget-id="455411516829736961"
                   data-chrome="noheader nofooter noscrollbar noborders transparent">https://github.com/KiNextLevel</a>
            </div>
            <!-- END TWITTER BLOCK -->

            <!-- BEGIN BOTTOM CONTACTS -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Our Contacts</h2>
                <address class="margin-bottom-40">
                    서울 강남구 테헤란로26길 12<br>
                    (우) 06236 (지번) 역삼동 736-56<br>
                    Notion: <a href="https://sheer-sundial-325.notion.site/1b5c9677015480c4a9ebfba7bbc63185">Notion</a><br>
                    Email: <a href="0414minyoung@naver.com">0414minyoung@naver.com</a>
                </address>
            </div>
            <!-- END BOTTOM CONTACTS -->
        </div>
        <hr>
        <div class="row">
            <!-- 자바스크립트 로드 부분 유지 -->
            <script src="assets/plugins/jquery.min.js" type="text/javascript"></script>
            <script src="assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
            <script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="assets/corporate/scripts/back-to-top.js" type="text/javascript"></script>
            <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>

            <!-- 페이지 레벨 자바스크립트 -->
            <script src="assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
            <script src="assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
            <script src="assets/corporate/scripts/layout.js" type="text/javascript"></script>

            <script type="text/javascript">
                jQuery(document).ready(function () {
                    Layout.init();
                    Layout.initOWL();
                    Layout.initTwitter();
                });

                document.addEventListener('DOMContentLoaded', function() {
                    const introTextarea = document.getElementById('userDescription');
                    const charCount = document.getElementById('charCount');

                    // 초기 글자 수 표시
                    charCount.textContent = introTextarea.value.length + ' / 200';

                    // 입력할 때마다 글자 수 업데이트
                    introTextarea.addEventListener('input', function() {
                        charCount.textContent = this.value.length + ' / 200';
                    });
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>
