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
    <title>메인 페이지</title>
    <link href="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/css/ProductPage.css" rel="stylesheet">

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
                    <c:if test="${sessionScope.userPremium == false}">
                    <li class="shop-currencies">
                        <a href="productPage.do">광고 제거</a>
                    </li>
                    </c:if>
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

        <!-- BEGIN CART 읽지 않은 알림이 있으면 비동기로 "새 알림이 있습니다", 없으면 그냥 없음-->
        <div class="top-cart-block">
            <!-- 여기가 새 알림이 있습니다 칸 -->
            <c:if test="${hasUnreadAlerts}">
                <div class="top-cart-info">
                    <p>새 알림이 있습니다</p>
                </div>
            </c:if>

            <!-- 클릭 시 JS 함수 호출하도록 수정 -->
<%--            <a href="javascript:void(0);" class="top-cart-toggle" onclick="toggleCartContent()">--%>
                <i class="fa fa-bell"></i>
<%--            </a>--%>

            <div class="top-cart-content-wrapper">
                <div class="top-cart-content">
                    <ul class="scroller" style="height: 250px;">
                        <c:if test="${empty alertDatas}">
                            <li>
                                <p>받은 알림이 아직 없습니다</p>
                            </li>
                        </c:if>

                        <c:forEach var="data" items="${alertDatas}">
                            <li id="alert-${data.alertNumber}" onclick="markAsRead(${data.alertNumber})">
                                <span class="cart-content-count">${data.alertNumber}</span>
                                <a href="javascript:void(0);" class="alert-content">${data.alertContent}</a>

                                <em>${data.alertDate}</em>

                                <!-- 상태를 표시 (읽음/읽지 않음) -->
                                <span class="alert-status" style="background-color: ${data.alertIsWatch ? '#4CAF50' : '#F44336'};">
                                        ${data.alertIsWatch ? '읽음' : '읽지 않음'}
                                </span>
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
    // 메인 페이지 로드 시 /SendMessage 요청 보냄 - 일단 보류
    window.onload = () => {
        fetch("/SendMessage")
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
                    <div class="checkbox-list" id="gender-filters">
                        <label><input type="checkbox" name="gender" value="male" checked="checked"> 남</label>
                        <label><input type="checkbox" name="gender" value="female" checked="checked"> 여</label>
                    </div>

                    <!-- 거리 필터 -->
                    <h3>거주지역</h3>
                    <div class="checkbox-list" id="distance-filters">
                        <label><input type="radio" name="distance" value="10" checked="checked"> 10km 이내</label>
                        <label><input type="radio" name="distance" value="50"> 50km</label>
                        <label><input type="radio" name="distance" value="100"> 100km 이상</label>
                    </div>
                    <!-- 나이 슬라이더 -->
                    <h3>나이</h3>
                    <p>
                        <label for="ageAmount">나이</label>
                        <input type="text" id="ageAmount" style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="age-slider-range" class="age"></div>

                    <!-- 키 슬라이더 -->
                    <h3>키</h3>
                    <p>
                        <label for="heightAmount">키</label>
                        <input type="text" id="heightAmount" style="border:0; color:#f6931f; font-weight:bold;">
                    </p>
                    <div id="height-slider-range" class="height"></div>

                    <!-- 종교 -->
                    <h3>종교</h3>
                    <div class="checkbox-list" id="religion-filters">
                        <label><input type="checkbox" name="religion" value="non"> 무교</label>
                        <label><input type="checkbox" name="religion" value="christianity"> 기독교</label>
                        <label><input type="checkbox" name="religion" value="buddhism"> 불교</label>
                        <label><input type="checkbox" name="religion" value="catholicism"> 천주교</label>
                        <label><input type="checkbox" name="religion" value="hinduism"> 힌두교</label>
                        <label><input type="checkbox" name="religion" value="others"> 기타</label>
                    </div>
                    <!-- 흡연 -->
                    <h3>흡연</h3>
                    <div class="checkbox-list" id="smoking-filters">
                        <label><input type="checkbox" name="smoking" value="smoke"> 흡연</label>
                        <label><input type="checkbox" name="smoking" value="non"> 비흡연</label>
                    </div>
                </div>

                <c:if test="${not empty userEmail and !userPremium}">
                    <div class="sidebar-products clearfix">
                                <h2>구글 광고</h2>
                                <div class="item">
                                    <a href="productPage.do"><img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/img/products/k1.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                                    <h3><a href="productPage.do">Some Shoes in Animal with Cut Out</a></h3>
                                    <div class="price">$31.00</div>
                                </div>
                                <div class="item">
                                    <a href="productPage.do"><img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/img/products/k4.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                                    <h3><a href="productPage.do">Some Shoes in Animal with Cut Out</a></h3>
                                    <div class="price">$23.00</div>
                                </div>
                                <div class="item">
                                    <a href="productPage.do"><img src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/pages/img/products/k3.jpg" alt="Some Shoes in Animal with Cut Out"></a>
                                    <h3><a href="productPage.do">Some Shoes in Animal with Cut Out</a></h3>
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
                <div class="row product-list" id="product-list">
                    <!-- 여기에 초기 데이터가 렌더링 됩니다. -->
                </div>
                <!-- END PRODUCT LIST -->

                <!-- 더 보기 버튼 -->
                <div id="load-more-container">
                    <button id="load-more-btn" class="btn btn-primary">더 보기</button>
                </div>
            </div>
            <!-- END CONTENT -->
        </div>
        <!-- END SIDEBAR & CONTENT -->
    </div>
</div>
<!-- BEGIN PRE-FOOTER -->
<div class="pre-footer">
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

<!-- Load javascripts at bottom, this will reduce page load time -->
<!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
<script>
    function markAsRead(alertNumber) {
        // 클릭된 알림 번호를 로그에 출력
        console.log("로그: 알림 번호 [" + alertNumber + "]");

        // AJAX 요청을 통해 알림 상태를 "읽음"으로 변경
        $.ajax({
            url: "/updateAlertStatus",  // 상태를 업데이트할 URL
            type: "POST",
            data: { alertNumber: alertNumber },  // alertNumber만 쿼리 문자열로 보내기
            dataType: 'json',  // 서버에서 응답으로 받는 데이터 타입
            success: function(response) {
                console.log("로그: 상태 업데이트 성공 [" + response + "]");

                // 상태 업데이트 성공 시, UI에서 해당 알림의 상태를 '읽음'으로 변경
                $("#alert-" + alertNumber)
                    .find(".alert-status")
                    .css("background-color", "#4CAF50")  // '읽음' 색상으로 변경
                    .text("읽음");  // 텍스트를 '읽음'으로 변경

                // 매번 알림을 읽음으로 표시할 때마다 호출
                // 새 알림이 있습니다 숨기기 위한 함수
                checkAllAlertsRead();
            },
            error: function() {
                console.log("비동기 처리 실패");
            }
        });
    }

    // 알림 상태를 모두 확인하여 "새 알림이 있습니다" 메시지를 숨기기
    function checkAllAlertsRead() {
        // 모든 알림이 '읽음' 상태인지 확인
        let allRead = true;

        $(".alert-status").each(function() {
            if ($(this).text() === '읽지 않음') {
                allRead = false;
            }
        });

        // 모든 알림이 읽음이면 "새 알림이 있습니다" 메시지 숨기기
        if (allRead) {
            $(".top-cart-info").hide();  // "새 알림이 있습니다" 메시지를 숨김
        }
    }
</script>
<!--[if lt IE 9]>
<script src="assets/plugins/respond.min.js"></script>
<![endif]-->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/back-to-top.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->

<!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script><!-- pop up -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script><!-- slider for products -->
<script src='${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/zoom/jquery.zoom.min.js' type="text/javascript"></script><!-- product zoom -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script><!-- Quantity -->
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/plugins/rateit/src/jquery.rateit.js" type="text/javascript"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js" type="text/javascript"></script><!-- for slider-range -->

<script src="${pageContext.request.contextPath}/Metronic-Shop-UI-master/theme/assets/corporate/scripts/layout.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function() {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();
        Layout.initImageZoom();
        Layout.initTouchspin();
        Layout.initUniform();
        // Layout.initAgeSliderRange();
        // Layout.initHeightSliderRange();
        // Layout.testFunction();

        // 페이지 레이아웃 스크립트에 추가
        $(window).on('load', function() {
            // 모든 이미지가 로드된 후 실행
            equalizeCardHeights();
        });

        $(window).on('resize', function() {
            // 창 크기 변경 시 실행
            equalizeCardHeights();
        });

        function equalizeCardHeights() {
            // 각 행별로 카드 높이 맞추기
            var currentTallest = 0,
                currentRowStart = 0,
                rowDivs = [],
                $el,
                topPosition = 0;

            $('.product-item').each(function() {
                $el = $(this);
                topPosition = $el.position().top;

                if (currentRowStart != topPosition) {
                    // 새로운 행 시작
                    for (var currentDiv = 0; currentDiv < rowDivs.length; currentDiv++) {
                        rowDivs[currentDiv].height(currentTallest);
                    }
                    rowDivs.length = 0; // 배열 초기화
                    currentRowStart = topPosition;
                    currentTallest = $el.height();
                    rowDivs.push($el);
                } else {
                    // 같은 행에 추가
                    rowDivs.push($el);
                    currentTallest = (currentTallest < $el.height()) ? ($el.height()) : (currentTallest);
                }

                // 마지막 행 처리
                for (var currentDiv = 0; currentDiv < rowDivs.length; currentDiv++) {
                    rowDivs[currentDiv].height(currentTallest);
                }
            });
        }

        // 기존 코드와 새로운 "더 보기" 및 필터 기능을 통합

        // Java에서 전달된 userDatas를 JavaScript로 가져오기
        var userDatas = ${userDatas};  // 대신 EL로 값을 사용하면 됩니다.
        var displayedUsers = [];
        var limit = 9; // 한 번에 더 불러올 사용자 수
        var start = 0; // 현재 표시된 사용자 수

        // 초기 로딩 (첫 9명만)
        function loadInitialUsers() {
            var usersToDisplay = userDatas.slice(start, start + limit);
            updateProductList(usersToDisplay);
            start += limit; // 다음 9명으로 업데이트
        }

        // 더 보기 버튼 클릭 시 호출
        $('#load-more-btn').click(function() {
            loadMoreUsers();
        });

        // 더 많은 사용자 로드
        function loadMoreUsers() {
            var usersToDisplay = userDatas.slice(start, start + limit);
            updateProductList(usersToDisplay);
            start += limit;
            if (start >= userDatas.length) {
                $('#load-more-btn').text('더 이상 없습니다.');
            }
        }

        // 응답 받은 데이터를 이용해 HTML을 동적으로 업데이트
        function updateProductList(users) {
            var productListHtml = '';
            users.forEach(function(data) {
                productListHtml += `
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
                `;
            });
            $('#product-list').html(productListHtml);  // 기존 목록에 덮어쓰기
        }

        // 필터 적용 함수
        function applyFilters() {
            var filteredUsers = userDatas.filter(function(user) {
                var matchesGender = true;
                var genderFilters = $('#gender-filters input:checked').map(function() {
                    return $(this).val();
                }).get();
                if (genderFilters.length > 0) {
                    matchesGender = genderFilters.includes(user.userGender);
                }

                // 나이 필터
                var selectedAge = $('#ageRange').val();
                var matchesAge = (user.userAge >= selectedAge);

                // 필터가 적용된 유저만 반환
                return matchesGender && matchesAge;
            });

            // 필터링된 사용자만 표시
            displayedUsers = filteredUsers;
            start = 0;  // 필터 후 처음부터 시작
            loadInitialUsers();  // 필터링된 첫 9명만 로드
        }

        // 필터 변경 시마다 적용
        $('#gender-filters input').change(function() {
            applyFilters();
        });

        $('#ageRange').on('input', function() {
            applyFilters();
        });

        // 초기 로딩
        loadInitialUsers();
    });
</script>

<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>