<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!-- 회원탈퇴 가운데로 정렬하기  -->
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
  <meta charset="utf-8"
  >
  <title>Checkout | Metronic Shop UI</title>

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

  <style>
    /* 회원탈퇴 안내 박스 스타일 */
    .main {
      display: flex;
      justify-content: center; /* 중앙 정렬 */
      align-items: center;
      min-height: 100vh; /* 화면 전체 높이 확보 */
      background-color: #f5f5f5; /* 배경색 변경 */
      padding: 20px;
    }

    /* 회원탈퇴 안내 박스 스타일 */
    .info-card {
      width: 100%;
      max-width: 600px; /* 박스 크기 조정 */
      background: #fff;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
    }

    /* 약관 텍스트 박스 */
    .form-group textarea {
      width: 100%;
      height: 150px;
      resize: none;
      overflow-y: auto;
      border: 1px solid #ccc;
      border-radius: 5px;
      padding: 10px;
      font-size: 14px;
      background-color: #f9f9f9;
    }

    /* 체크박스와 버튼 정렬 */
    .form-group {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    /* 탈퇴하기 버튼 스타일 */
    .btn-danger {
      width: 100%;
      max-width: 150px;
      display: block;
      margin: 10px auto;
      font-size: 16px;
      padding: 10px;
      border-radius: 5px;


    }

  </style>

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
          <li><i class="fa fa-phone"></i><span>+1 456 6717</span></li>
          <!-- BEGIN CURRENCIES -->
          <li class="shop-currencies">
            <a href="javascript:void(0);">€</a>
            <a href="javascript:void(0);">£</a>
            <a href="javascript:void(0);" class="current">$</a>
          </li>
          <!-- END CURRENCIES -->

        </ul>
      </div>
      <!-- END TOP BAR LEFT PART -->
      <!-- BEGIN TOP BAR MENU -->
      <div class="col-md-6 col-sm-6 additional-nav">
        <ul class="list-unstyled list-inline pull-right">
          <li><a href="shop-account.html">My Account</a></li>
          <li><a href="shop-wishlist.html">My Wishlist</a></li>
          <li><a href="shop-checkout.html">Checkout</a></li>
          <li><a href="page-login.html">Log In</a></li>
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



    <!-- BEGIN NAVIGATION -->
    <div class="header-navigation">
      <ul>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
            Woman

          </a>

          <!-- BEGIN DROPDOWN MENU -->
          <ul class="dropdown-menu">
            <li class="dropdown-submenu">
              <a href="shop-product-list.html">Hi Tops <i class="fa fa-angle-right"></i></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="shop-product-list.html">Second Level Link</a></li>
                <li><a href="shop-product-list.html">Second Level Link</a></li>
                <li class="dropdown-submenu">
                  <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
                    Second Level Link
                    <i class="fa fa-angle-right"></i>
                  </a>
                  <ul class="dropdown-menu">
                    <li><a href="shop-product-list.html">Third Level Link</a></li>
                    <li><a href="shop-product-list.html">Third Level Link</a></li>
                    <li><a href="shop-product-list.html">Third Level Link</a></li>
                  </ul>
                </li>
              </ul>
            </li>
            <li><a href="shop-product-list.html">Running Shoes</a></li>
            <li><a href="shop-product-list.html">Jackets and Coats</a></li>
          </ul>
          <!-- END DROPDOWN MENU -->
        </li>


        <li class="dropdown active">
          <a class="dropdown-toggle" data-toggle="dropdown" data-target="#" href="javascript:;">
            Pages

          </a>

          <ul class="dropdown-menu">
            <li><a href="shop-index.html">Home Default</a></li>
            <li><a href="shop-index-header-fix.html">Home Header Fixed</a></li>
            <li><a href="shop-index-light-footer.html">Home Light Footer</a></li>
            <li><a href="shop-product-list.html">Product List</a></li>
            <li><a href="shop-search-result.html">Search Result</a></li>
            <li><a href="shop-item.html">Product Page</a></li>
            <li><a href="shop-shopping-cart-null.html">Shopping Cart (Null Cart)</a></li>
            <li><a href="shop-shopping-cart.html">Shopping Cart</a></li>
            <li class="active"><a href="shop-checkout.html">Checkout</a></li>
            <li><a href="shop-about.html">About</a></li>
            <li><a href="shop-contacts.html">Contacts</a></li>
            <li><a href="shop-account.html">My account</a></li>
            <li><a href="shop-wishlist.html">My Wish List</a></li>
            <li><a href="shop-goods-compare.html">Product Comparison</a></li>
            <li><a href="shop-standart-forms.html">Standart Forms</a></li>
            <li><a href="shop-faq.html">FAQ</a></li>
            <li><a href="shop-privacy-policy.html">Privacy Policy</a></li>
            <li><a href="shop-terms-conditions-page.html">Terms &amp; Conditions</a></li>
          </ul>
        </li>


        <li><a href="http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes&amp;utm_source=download&amp;utm_medium=banner&amp;utm_campaign=metronic_frontend_freebie" target="_blank">Admin theme</a></li>

        <!-- BEGIN TOP SEARCH -->
        <li class="menu-search">
          <span class="sep"></span>
          <i class="fa fa-search search-btn"></i>
          <div class="search-box">
            <form action="#">
              <div class="input-group">
                <input type="text" placeholder="Search" class="form-control">
                <span class="input-group-btn">
                      <button class="btn btn-primary" type="submit">Search</button>
                    </span>
              </div>
            </form>
          </div>
        </li>
        <!-- END TOP SEARCH -->
      </ul>
    </div>
    <!-- END NAVIGATION -->
  </div>
</div>
<!-- Header END -->

<div class="main">
  <div class="container">

    <!-- BEGIN SIDEBAR & CONTENT -->
    <div class="row margin-bottom-40">
      <!-- BEGIN CONTENT -->
      <div class="col-md-12 col-sm-12">
        <h1>회원탈퇴</h1>
        <!-- BEGIN CHECKOUT PAGE -->
        <div class="panel-group checkout-page accordion scrollable" id="checkout-page">

          <!-- BEGIN CHECKOUT -->
          <!--  <div id="checkout" class="panel panel-default"> -->
          <div class="panel-heading">
            <h2 class="panel-title">

            </h2>
          </div>
          <!-- <div id="checkout-content" class="panel-collapse collapse in"> -->
          <!-- 회원탈퇴 안내 -->
          <div class="info-card">
            <h3>회원탈퇴 안내</h3>
            <p>회원탈퇴 약관</p>
            <form id="deleteForm" action="processDeleteAccount.jsp" method="post">
              <div class="form-group">
            <textarea class="form-control" rows="6" readonly>
                제1조
                이 약관은 샘플 약관입니다.
                ① 약관 내용 1
                ② 약관 내용 2
                ③ 약관 내용 3
                ④ 약관 내용 4

                제2조
                이 약관은 샘플 약관입니다.
                ① 약관 내용 1
            </textarea>
              </div>
              <div class="form-group">
                <input type="checkbox" id="agreeTerms" required>
                <label for="agreeTerms">위 약관에 동의합니다.</label>
              </div>
              <div class="padding-top-20">
                <button type="button" class="btn btn-danger" onclick="confirmDelete()">탈퇴하기</button>
              </div>
            </form>
          </div>

          <script>
            function confirmDelete() {
              if (confirm("정말로 회원 탈퇴를 진행하시겠습니까?\n탈퇴 후에는 계정을 복구할 수 없습니다.")) {
                document.getElementById("deleteForm").submit();
              }
            }
          </script>

          <!--   <div class="panel-body row">

              <div class="col-md-6 col-sm-6">
                <h3>회원탈퇴 안내</h3>
                <p>회원탈퇴 약관</p>
                <form role="form" action="#">
                  <div class="form-group">
                    <textarea class="form-control" rows="6" readonly>
              제1조
              이 약관은 샘플 약관입니다.
              ① 약관 내용 1
              ② 약관 내용 2
              ③ 약관 내용 3
              ④ 약관 내용 4

              제2조
              이 약관은 샘플 약관입니다.
              ① 약관 내용 1
                    </textarea>
                  </div>
                  <div class="form-group">
                    <input type="checkbox" id="agreeTerms">
                    <label for="agreeTerms">위 약관에 동의합니다.</label>
                  </div>
                  <div class="padding-top-20">
                    <button class="btn btn-danger" type="submit">탈퇴하기</button>
                  </div>
                </form>
              </div>

        </div> -->
          <!-- END CHECKOUT -->

          <!--
            </div> -->
          <!-- END CHECKOUT PAGE -->
          <!--  </div> -- -->
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
          <script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
          <!-- END CORE PLUGINS -->

          <!-- BEGIN PAGE LEVEL JAVASCRIPTS (REQUIRED ONLY FOR CURRENT PAGE) -->
          <script src="assets/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
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
</body>
<!-- END BODY -->

</html>