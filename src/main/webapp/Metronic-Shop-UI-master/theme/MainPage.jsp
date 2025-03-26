<%@ page import="org.example.webapp.model.dto.UserDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.example.webapp.model.dto.AlertDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--
Template: Metronic Frontend Freebie - Responsive HTML Template Based On Twitter Bootstrap 3.3.4
Version: 1.0.0
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Follow: www.twitter.com/keenthemes
Like: www.facebook.com/keenthemes
Purchase Premium Metronic Admin Theme: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->

<!-- Head BEGIN -->
<head>
    <meta charset="utf-8">
    <title>Men category | Metronic Shop UI</title>
    <link href="css/ProductPage.css" rel="stylesheet">

    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <meta content="Metronic Shop UI description" name="description">
    <meta content="Metronic Shop UI keywords" name="keywords">
    <meta content="keenthemes" name="author">

    <meta property="og:site_name" content="-CUSTOMER VALUE-">
    <meta property="og:title" content="-CUSTOMER VALUE-">
    <meta property="og:description" content="-CUSTOMER VALUE-">
    <meta property="og:type" content="website">
    <meta property="og:image" content="-CUSTOMER VALUE-"><!-- link to image for socio -->
    <meta property="og:url" content="-CUSTOMER VALUE-">

    <link rel="shortcut icon" href="favicon.ico">

    <!-- Fonts START -->
    <link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all" rel="stylesheet" type="text/css">
    <!-- Fonts END -->

    <!-- Global styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Global styles END -->

    <!-- Page level plugin styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/assets/owl.carousel.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
    <link href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css"><!-- for slider-range -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/rateit/src/rateit.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin styles END -->

    <!-- Theme styles START -->
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/components.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/css/style-shop.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/style-responsive.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color">
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/css/custom.css" rel="stylesheet">
    <!-- Theme styles END -->
</head>
<!-- Head END -->

<!-- Body BEGIN -->
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
                <ul class="list-unstyled list-inline">
                    <li><i class="fa fa-phone"></i><span>010 - 1234 - 1234</span></li>
                    <!-- BEGIN CURRENCIES -->
                    <li class="shop-currencies">
                        <a href="productPage.do">광고 제거</a>
                    </li>
                    <!-- END CURRENCIES -->
                    <!-- BEGIN LANGS -->
                    <li class="langs-block">
                        <a href="productPage.do" class="current"> 토큰 구매 </a>
                    </li>
                    <!-- END LANGS -->
                </ul>
            </div>
            <!-- END TOP BAR LEFT PART -->
            <!-- BEGIN TOP BAR MENU -->
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
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
        <a class="site-logo" href="shop-index.html"><img src="assets/corporate/img/logos/logo-shop-red.png" alt="Metronic Shop UI"></a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN CART 읽지 않은 알림이 있으면 비동기로 "새 알림이 있습니다", 없으면 그냥 없음-->
        <div class="top-cart-block">
            <!-- 여기가 새 알림이 있습니다 칸 -->
            <c:if test="${not empty alerts}">
                <div class="top-cart-info">
                    <p>새 알림이 있습니다</p>
                </div>
            </c:if>

            <i class="fa fa-shopping-cart"></i>

            <div class="top-cart-content-wrapper">
                <div class="top-cart-content">
                    <ul class="scroller" style="height: 250px;">
                        <c:if test="${empty alerts}">
                            <li>
                                <p>받은 알림이 아직 없습니다</p>
                            </li>
                        </c:if>

                        <c:forEach var="data" items="${alerts}">
                            <li>
                                <span class="cart-content-count">${data.alertNumber}</span>
                                <strong>${data.alertContent}</strong>
                                <em>${data.alertDate}</em>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <!--END CART -->
    </div>
</div>
<!-- Header END -->
<!-- 문자 전송 요청-->

<script>
    // 메인 페이지 로드 시 /sendMessage 요청을 보내는 코드
    window.onload = () => {
        fetch("sendMessage.do")
            .then(response => {
                if (!response.ok) {
                    throw new Error('네트워크 응답이 잘못되었습니다.');
                }
                return response.json(); // JSON 응답을 반환받음
            })
            .then(data => {
                console.log('받은 데이터:', data);
                document.getElementById('message').textContent = data.message || '메시지가 없습니다.';
            })
            .catch(error => {
                console.error('문제가 발생했습니다:', error);
                document.getElementById('message').textContent = '데이터를 불러오는 데 문제가 발생했습니다.';
            });
    };
</script>
<div class="title-wrapper">
    <div class="container"><div class="container-inner">
        <a href="boardPage.do">
            <h1><span>이벤트!</span></h1>
            <em>온/오프라인 이벤트 참여하기</em>
        </a>
    </div></div>
</div>

<div class="main">
    <div class="container">
        <ul class="breadcrumb">
            <li class="active">Home</li>
        </ul>
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
            <!-- BEGIN SIDEBAR -->
            <div class="sidebar col-md-3 col-sm-5">
                <div class="sidebar-filter margin-bottom-25">
                    <h2>Filter</h2>
                    <!-- 성별 필터 -->
                    <h3>성별</h3>
                    <div class="checkbox-list">
                        <label><input type="checkbox"> 남</label>
                        <label><input type="checkbox"> 여</label>
                    </div>

                    <!-- 나이 슬라이더 -->
                    <h3>나이</h3>
                    <p>
                        <label for="ageAmount">나이</label>
                        <input type="text" id="ageAmount" style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="age-slider-range" class="age"></div>

                    <!-- 거주지역 -->
                    <h3>거주지역</h3>
                    <div class="checkbox-list">
                        <label><input type="checkbox"> 내 주위 0~100km</label>
                    </div>

                    <!-- 키 슬라이더 -->
                    <h3>키</h3>
                    <p>
                        <label for="heightAmount">키</label>
                        <input type="text" id="heightAmount" style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="height-slider-range" class="height"></div>

                    <!-- 학력 -->
                    <h3>학력</h3>
                    <div class="checkbox-list">
                        <label><input type="checkbox"> 범위 지정</label>
                    </div>

                    <!-- 종교 -->
                    <h3>종교</h3>
                    <div class="checkbox-list">
                        <label><input type="checkbox"> 무교</label>
                        <label><input type="checkbox"> 기독교</label>
                        <label><input type="checkbox"> 불교</label>
                        <label><input type="checkbox"> 천주교</label>
                        <label><input type="checkbox"> 힌두교</label>
                        <label><input type="checkbox"> 기타</label>
                    </div>

                    <!-- 흡연 -->
                    <h3>흡연</h3>
                    <div class="checkbox-list">
                        <label><input type="checkbox"> 흡연</label>
                        <label><input type="checkbox"> 비흡연</label>
                    </div>
                </div>

                <c:if test="${not empty userDTO and !userDTO.userPreminum}">
                    <div class="sidebar-products clearfix">
                                <h2>구글 광고</h2>
                                <div class="item">
                                    <a href="shop-item.html"><img src="assets/pages/img/products/k1.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                                    <h3><a href="shop-item.html">Some Shoes in Animal with Cut Out</a></h3>
                                    <div class="price">$31.00</div>
                                </div>
                                <div class="item">
                                    <a href="shop-item.html"><img src="assets/pages/img/products/k4.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                                    <h3><a href="shop-item.html">Some Shoes in Animal with Cut Out</a></h3>
                                    <div class="price">$23.00</div>
                                </div>
                                <div class="item">
                                    <a href="shop-item.html"><img src="assets/pages/img/products/k3.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                                    <h3><a href="shop-item.html">Some Shoes in Animal with Cut Out</a></h3>
                                    <div class="price">$86.00</div>
                        </div>
                    </div>
                </c:if>

            </div>
            <!-- END SIDEBAR -->

            <!-- BEGIN CONTENT -->
            <div class="col-md-9 col-sm-7">
                <div class="row list-view-sorting clearfix">
                    <div class="col-md-2 col-sm-2 list-view">
                        <a href="javascript:;"><i class="fa fa-th-large"></i></a>
                        <a href="javascript:;"><i class="fa fa-th-list"></i></a>
                    </div>
                    <div class="col-md-10 col-sm-10">
                        <div class="pull-right">
                            <label class="control-label">Show:</label>
                            <select class="form-control input-sm">
                                <option value="#?limit=24" selected="selected">24</option>
                                <option value="#?limit=25">25</option>
                                <option value="#?limit=50">50</option>
                                <option value="#?limit=75">75</option>
                                <option value="#?limit=100">100</option>
                            </select>
                        </div>
                        <div class="pull-right">
                            <label class="control-label">Sort&nbsp;By:</label>
                            <select class="form-control input-sm">
                                <option value="#?sort=p.sort_order&amp;order=ASC" selected="selected">Default</option>
                                <option value="#?sort=pd.name&amp;order=ASC">Name (A - Z)</option>
                                <option value="#?sort=pd.name&amp;order=DESC">Name (Z - A)</option>
                                <option value="#?sort=p.price&amp;order=ASC">Price (Low &gt; High)</option>
                                <option value="#?sort=p.price&amp;order=DESC">Price (High &gt; Low)</option>
                                <option value="#?sort=rating&amp;order=DESC">Rating (Highest)</option>
                                <option value="#?sort=rating&amp;order=ASC">Rating (Lowest)</option>
                                <option value="#?sort=p.model&amp;order=ASC">Model (A - Z)</option>
                                <option value="#?sort=p.model&amp;order=DESC">Model (Z - A)</option>
                            </select>
                        </div>
                    </div>
                </div>
                <!-- BEGIN PRODUCT LIST -->
                <div class="row product-list">
                    <c:if test="${empty userDatas}">
                        <li>
                            <p>회원이 없습니다</p>
                        </li>
                    </c:if>
                    <c:forEach var="data" items="${userDatas}">
                        <!-- PRODUCT ITEM START -->
                        <div class="col-md-4 col-sm-6 col-xs-12">
                            <div class="product-item">
                                <div class="pi-img-wrapper">
                                    <img src="${data.userProfile}" class="img-responsive" alt="userImage">
                                    <div class="product-page-cart">
                                        <button class="btn btn-primary" type="submit">메시지 보내기</button>
                                        <a href="userDetailPage.do?userEmail=${data.userEmail}" class="btn btn-default">프로필 보기</a>
                                    </div>
                                </div>
                                <h3><a href="userDetailPage.do?userEmail=${data.userEmail}">${data.userNickname}</a></h3>
                                <div class="height">키: ${data.userHeight}cm</div>
                                <div class="description">${data.userDescription}</div>
                            </div>
                        </div>
                        <!-- PRODUCT ITEM END -->
                    </c:forEach>

                    <!-- Default Product Item -->
                    <div class="col-md-4 col-sm-6 col-xs-12">
                        <div class="product-item">
                            <div class="pi-img-wrapper">
                                <img src="TEST" class="img-responsive" alt="userImage">
                                <div>
                                    <button class="btn btn-default" type="submit">메시지 보내기</button>
                                    <a href="userDetailPage.do?userEmail=test@test.com" class="btn btn-default">프로필 보기</a>
                                </div>
                            </div>
                            <div class="age">나이: 22세</div>
                            <div class="height">키: 222cm</div>
                            <div class="자기소개">ㅎㅇ</div>
                        </div>
                    </div>
                    <!-- PRODUCT ITEM END -->
                </div>
                <!-- END PRODUCT LIST -->
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END SIDEBAR & CONTENT -->
    </div>
</div>

<!-- BEGIN FOOTER -->
<div class="footer">
    <div class="container">
        <div class="row">
            <!-- BEGIN BOTTOM ABOUT BLOCK -->
            <div class="col-md-3 col-sm-6 pre-footer-col">
                <h2>Next Level</h2>
                <p>
                    우리는 인연과 연인인을 중시합니다.
                </p>
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
    </div>
</div>
<!-- END FOOTER -->

<!-- BEGIN fast view of a product -->
<%--    <div id="product-pop-up" style="display: none; width: 700px;">--%>
<%--            <div class="product-page product-pop-up">--%>
<%--              <div class="row">--%>
<%--                <div class="col-md-6 col-sm-6 col-xs-3">--%>
<%--                  <div class="product-main-image">--%>
<%--                    <img src="assets/pages/img/products/model7.jpg" alt="Cool green dress with red bell" class="img-responsive">--%>
<%--                  </div>--%>
<%--                </div>--%>
<%--                <div class="col-md-6 col-sm-6 col-xs-9">--%>
<%--                  <h1>회원이름</h1>--%>
<%--                  <div class="price-availability-block clearfix">--%>
<%--                  </div>--%>
<%--                  <div class="description">--%>
<%--                    <p>회원설명</p>--%>
<%--                  </div>--%>
<%--                  <div class="product-page-options">--%>
<%--                  </div>--%>
<%--                  <div class="product-page-cart">--%>
<%--                    <button class="btn btn-primary" type="submit">메시지 보내기</button>--%>
<%--                    <a href="shop-item.html" class="btn btn-default">프로필 보기</a>--%>
<%--                  </div>--%>
<%--                </div>--%>

<%--                <div class="sticker sticker-sale"></div>--%>
<%--              </div>--%>
<%--            </div>--%>
<%--    </div>--%>
<!-- END fast view of a product -->

<!-- Load javascripts at bottom, this will reduce page load time -->
<!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
<![endif]-->
<script src="assets/plugins/jquery.min.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="assets/corporate/scripts/back-to-top.js" type="text/javascript"></script>
<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
<script src="assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
<script src="assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->
<script src='assets/plugins/zoom/jquery.zoom.min.js' type="text/javascript"></script><!-- product zoom -->
<script src="assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script><!-- Quantity -->
<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="assets/plugins/rateit/src/jquery.rateit.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script><!-- for slider-range -->

<script src="assets/corporate/scripts/layout.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();
        Layout.initImageZoom();
        Layout.initTouchspin();
        Layout.initUniform();
        Layout.initAgeSliderRange();
        Layout.initHeightSliderRange();
    });
</script>
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>