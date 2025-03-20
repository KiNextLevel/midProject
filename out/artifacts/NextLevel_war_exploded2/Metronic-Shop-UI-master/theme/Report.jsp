<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="reportDTO" class="org.example.webapp.model.dto.ReportDTO" scope="page"/>
<html>
<head>
    <meta charset="utf-8">
    <title>신고 페이지</title>

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
    <link href="css/Report.css" rel="stylesheet">
    <!-- Fonts START -->
    <link
            href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700|PT+Sans+Narrow|Source+Sans+Pro:200,300,400,600,700,900&amp;subset=all"
            rel="stylesheet" type="text/css">
    <!-- Fonts END -->

    <!-- Global styles START -->
    <link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Global styles END -->

    <!-- Page level plugin styles START -->
    <link href="assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet">
    <link href="assets/plugins/owl.carousel/assets/owl.carousel.css" rel="stylesheet">
    <link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin styles END -->

    <!-- Theme styles START -->
    <link href="assets/pages/css/components.css" rel="stylesheet">
    <link href="assets/corporate/css/style.css" rel="stylesheet">
    <link href="assets/pages/css/style-shop.css" rel="stylesheet" type="text/css">
    <link href="assets/corporate/css/style-responsive.css" rel="stylesheet">
    <link href="assets/corporate/css/themes/red.css" rel="stylesheet" id="style-color">
    <link href="assets/corporate/css/custom.css" rel="stylesheet">
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
            </div>
            <!-- END TOP BAR LEFT PART -->
            <!-- BEGIN TOP BAR MENU -->
            <div class="col-md-6 col-sm-6 additional-nav">
                <ul class="list-unstyled list-inline pull-right">
                    <li><a href="MyPage.jsp">마이페이지</a></li>
                    <li><a href="">로그아웃</a></li>
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
        <a class="site-logo" href="MainPage.jsp"><img src="assets/corporate/img/logos/3.png"
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
        <!-- BEGIN SIDEBAR & CONTENT -->
        <div class="row margin-bottom-40">
            <!-- BEGIN CONTENT -->
            <div class="col-md-12 col-sm-12">
                <h1>REPORT</h1>
                <!-- BEGIN CHECKOUT PAGE -->
                <div class="panel-group checkout-page accordion scrollable" id="checkout-page">

                    <!-- BEGIN CHECKOUT -->
                    <div id="checkout" class="panel panel-default">
                        <div class="panel-heading">
                            <h2 class="panel-title">
                                <a data-toggle="collapse" data-parent="#checkout-page" href="#checkout-content"
                                   class="accordion-toggle">
                                    신고하기
                                </a>
                            </h2>
                        </div>
                        <div id="checkout-content" class="panel-collapse collapse in">
                            <div class="panel-body row">
                                <div class="col-md-6 col-sm-6">
                                    <h3><%= reportDTO.getReportReported() %> 신고하기</h3>
                                    <div class="radio-list">
                                        <label>
                                            <input type="checkbox" name="REPORT_REASON" value="REPORT_REASON"> 욕설
                                        </label>
                                        <label>
                                            <input type="checkbox" name="REPORT_REASON" value="REPORT_REASON"> 성희롱
                                        </label>
                                        <label>
                                            <input type="checkbox" name="REPORT_REASON" value="REPORT_REASON"> 광고
                                        </label>
                                        <label>
                                            <input type="checkbox" name="REPORT_REASON" value="REPORT_REASON"> 버그 악용
                                        </label>
                                        <label>
                                            <input type="checkbox" name="REPORT_REASON" value="REPORT_REASON"> 기타
                                        </label>
                                        <label style="display: block; margin-top: 10px;">
                        <textarea name="REPORT_DESCRIPTION" placeholder="사유를 입력해주세요."
                                  style="width: 100%; height: 120px; padding: 10px; border: 1px solid #ccc; border-radius: 4px; resize: vertical;"></textarea>
                                        </label>
                                    </div>
                                    <button class="btn btn-primary" type="button" value="report-submit">제출하기</button>

                                </div>
                                <div class="col-md-6 col-sm-6">
                                    <h3>신고하기에 관하여</h3>
                                    <div class="payment-section">
                                        <p>
                                            신고하기는 관리자에게 정보가 전달됩니다. 신고자와 피신고자의 정보가 들어가며, 대화 내용이 함께 전송됩니다.
                                            개인정보가 포함되어 있을 수 있으며, 이는 「개인정보 보호법」 제15조에 따라 신고 처리 목적으로만 수집·이용됩니다. 수집된 개인정보는 신고 처리 완료 후 관련 법령에 따른 보관 기간이 지나면 안전하게 파기됩니다.
                                        </p>
                                    </div>
                                    <form role="form" action="#">
                                        <div class="form-group">
                                            <label for="agree">동의한다면 체크해주세요.</label>
                                            <input type="checkbox" id="AGREE">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END CHECKOUT -->
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
                        <a class="twitter-timeline" href="https://github.com/KiNextLevel" data-tweet-limit="2"
                           data-theme="dark"
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
                    <!-- Load javascripts at bottom, this will reduce page load time -->
                    <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
                    <!--[if lt IE 9]>
                    <script src="assets/plugins/respond.min.js"></script>
                    <![endif]-->
                    <script src="assets/plugins/jquery.min.js" type="text/javascript"></script>
                    <script src="assets/plugins/jquery-migrate.min.js" type="text/javascript"></script>
                    <script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
                    <script src="assets/corporate/scripts/back-to-top.js" type="text/javascript"></script>
                    <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js"
                            type="text/javascript"></script>
                    <!-- END CORE PLUGINS -->

                    <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
                    <script src="assets/plugins/fancybox/source/jquery.fancybox.pack.js"
                            type="text/javascript"></script>
                    <!-- pop up -->
                    <script src="assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
                    <!-- slider for products -->

                    <script src="assets/corporate/scripts/layout.js" type="text/javascript"></script>
                    <script type="text/javascript">
                        jQuery(document).ready(function () {
                            Layout.init();
                            Layout.initOWL();
                            Layout.initTwitter();
                        });
                    </script>
                    <!-- END PAGE LEVEL JAVASCRIPTS -->
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- END BODY -->
<script src="js/Submit.js"></script>

</html>