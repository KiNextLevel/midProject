<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>사용자 상세 페이지</title>

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
    <style>
        .top-bar-right {
            float: right;
            margin-right: 20px;
            margin-top: 10px;
        }

        .top-bar-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .top-bar-list li {
            display: inline-block;
            margin-left: 10px;
        }

        @media (max-width: 767px) {
            .top-bar-right {
                margin-top: 0;
                margin-bottom: 10px;
                text-align: center;
                float: none;
            }
        }
    </style>

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
    <link href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" rel="stylesheet" type="text/css">
    <!-- for slider-range -->
    <link href="assets/plugins/rateit/src/rateit.css" rel="stylesheet" type="text/css">
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

<!-- BEGIN HEADER -->
<div class="header">
    <div class="container">
        <a class="site-logo" href="shop-index.html"><img src="assets/corporate/img/logos/logo-shop-red.png"
                                                         alt="Metronic Shop UI"></a>

        <a href="javascript:void(0);" class="mobi-toggler"><i class="fa fa-bars"></i></a>

        <!-- BEGIN TOP BAR MENU -->
        <div class="top-bar-right">
            <ul class="top-bar-list">
                <li><a href="mypage.html" class="btn btn-primary">마이페이지</a></li>
            </ul>
        </div>
        <!-- END TOP BAR MENU -->

        <!-- BEGIN CART -->
        <div class="top-cart-block">
            <div class="top-cart-content-wrapper">
                <div class="top-cart-content">
                    <ul class="scroller" style="height: 250px;">
                        <!-- 장바구니 내용 -->
                    </ul>
                    <div class="text-right">
                        <a href="shop-shopping-cart.html" class="btn btn-default">View Cart</a>
                        <a href="shop-checkout.html" class="btn btn-primary">Checkout</a>
                    </div>
                </div>
            </div>
        </div>
        <!--END CART -->


        <!-- BEGIN NAVIGATION -->
        <div class="header-navigation">
            <ul>
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
        <!-- BEGIN CONTENT -->
        <div class="col-md-9 col-sm-7">
            <div class="product-page">
                <div class="row">
                    <div class="col-md-6 col-sm-6">
                        <div class="product-main-image">
                            <img src="assets/pages/img/products/model7.jpg" alt="Cool green dress with red bell"
                                 class="img-responsive" data-BigImgsrc="assets/pages/img/products/model7.jpg">
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6">
                        <h1>{users} 의 프로필</h1>
                        <div class="price-availability-block clearfix">
                            <div class="price">
                                <strong>{users}</strong>
                            </div>
                            <div class="availability">
                                지역 : <strong>서울</strong>
                            </div>
                        </div>
                        <div class="description">
                            <p>users description</p>
                        </div>
                        <div class="product-page-cart">
                            <button class="btn btn-primary" type="submit">1:1 채팅하기</button>
                        </div>
                    </div>

                    <div class="product-page-content">
                        <ul id="myTab" class="nav nav-tabs">
                            <li><a href="#Description" data-toggle="tab">Description</a></li>
                            <li><a href="#Information" data-toggle="tab">Information</a></li>
                        </ul>
                        <div id="myTabContent" class="tab-content">
                            <div class="tab-pane fade" id="Description">
                                <p>Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed sit nonumy nibh sed euismod laoreet
                                    dolore magna aliquarm erat sit volutpat Nostrud duis molestie at dolore. Lorem ipsum dolor ut sit
                                    ame dolore adipiscing elit, sed sit nonumy nibh sed euismod laoreet dolore magna aliquarm erat sit
                                    volutpat Nostrud duis molestie at dolore. Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed
                                    sit nonumy nibh sed euismod laoreet dolore magna aliquarm erat sit volutpat Nostrud duis molestie
                                    at dolore. </p>
                            </div>
                            <div class="tab-pane fade" id="Information">
                                <table class="datasheet">
                                    <tr>
                                        <th colspan="2">Additional features</th>
                                    </tr>
                                    <tr>
                                        <td class="datasheet-features-type">Value 1</td>
                                        <td>21 cm</td>
                                    </tr>
                                    <tr>
                                        <td class="datasheet-features-type">Value 2</td>
                                        <td>700 gr.</td>
                                    </tr>
                                    <tr>
                                        <td class="datasheet-features-type">Value 3</td>
                                        <td>10 person</td>
                                    </tr>
                                    <tr>
                                        <td class="datasheet-features-type">Value 4</td>
                                        <td>14 cm</td>
                                    </tr>
                                    <tr>
                                        <td class="datasheet-features-type">Value 5</td>
                                        <td>plastic</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="tab-pane fade in active" id="Reviews">
                                <!--<p>There are no reviews for this product.</p>-->
                                <div class="review-item clearfix">
                                    <div class="review-item-submitted">
                                        <strong>Bob</strong>
                                        <em>30/12/2013 - 07:37</em>
                                        <div class="rateit" data-rateit-value="5" data-rateit-ispreset="true" data-rateit-readonly="true">
                                        </div>
                                    </div>
                                    <div class="review-item-content">
                                        <p>Sed velit quam, auctor id semper a, hendrerit eget justo. Cum sociis natoque penatibus et
                                            magnis dis parturient montes, nascetur ridiculus mus. Duis vel arcu pulvinar dolor tempus
                                            feugiat id in orci. Phasellus sed erat leo. Donec luctus, justo eget ultricies tristique, enim
                                            mauris bibendum orci, a sodales lectus purus ut lorem.</p>
                                    </div>
                                </div>
                                <div class="review-item clearfix">
                                    <div class="review-item-submitted">
                                        <strong>Mary</strong>
                                        <em>13/12/2013 - 17:49</em>
                                        <div class="rateit" data-rateit-value="2.5" data-rateit-ispreset="true"
                                             data-rateit-readonly="true"></div>
                                    </div>
                                    <div class="review-item-content">
                                        <p>Sed velit quam, auctor id semper a, hendrerit eget justo. Cum sociis natoque penatibus et
                                            magnis dis parturient montes, nascetur ridiculus mus. Duis vel arcu pulvinar dolor tempus
                                            feugiat id in orci. Phasellus sed erat leo. Donec luctus, justo eget ultricies tristique, enim
                                            mauris bibendum orci, a sodales lectus purus ut lorem.</p>
                                    </div>
                                </div>

                                <!-- BEGIN FORM-->
                                <form action="#" class="reviews-form" role="form">
                                    <h2>Write a review</h2>
                                    <div class="form-group">
                                        <label for="name">Name <span class="require">*</span></label>
                                        <input type="text" class="form-control" id="name">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input type="text" class="form-control" id="email">
                                    </div>
                                    <div class="form-group">
                                        <label for="review">Review <span class="require">*</span></label>
                                        <textarea class="form-control" rows="8" id="review"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Rating</label>
                                        <input type="range" value="4" step="0.25" id="backing5">
                                        <div class="rateit" data-rateit-backingfld="#backing5" data-rateit-resetable="false"
                                             data-rateit-ispreset="true" data-rateit-min="0" data-rateit-max="5">
                                        </div>
                                    </div>
                                    <div class="padding-top-20">
                                        <button type="submit" class="btn btn-primary">Send</button>
                                    </div>
                                </form>
                                <!-- END FORM-->
                            </div>
                        </div>
                    </div>

                    <div class="sticker sticker-sale"></div>
                </div>
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

<!-- BEGIN fast view of a product -->
<div id="product-pop-up" style="display: none; width: 700px;">
    <div class="product-page product-pop-up">
        <div class="row">
            <div class="col-md-6 col-sm-6 col-xs-3">
                <div class="product-main-image">
                    <img src="assets/pages/img/products/model7.jpg" alt="Cool green dress with red bell" class="img-responsive">
                </div>
            </div>
            <div class="col-md-6 col-sm-6 col-xs-9">
                <h2>Cool green dress with red bell</h2>
                <div class="price-availability-block clearfix">
                    <div class="price">
                        <strong><span>$</span>47.00</strong>
                        <em>$<span>62.00</span></em>
                    </div>
                    <div class="availability">
                        Availability: <strong>In Stock</strong>
                    </div>
                </div>
                <div class="description">
                    <p>Lorem ipsum dolor ut sit ame dolore adipiscing elit, sed nonumy nibh sed euismod laoreet dolore magna
                        aliquarm erat volutpat
                        Nostrud duis molestie at dolore.</p>
                </div>
                <div class="product-page-options">
                    <div class="pull-left">
                        <label class="control-label">Size:</label>
                        <select class="form-control input-sm">
                            <option>L</option>
                            <option>M</option>
                            <option>XL</option>
                        </select>
                    </div>
                    <div class="pull-left">
                        <label class="control-label">Color:</label>
                        <select class="form-control input-sm">
                            <option>Red</option>
                            <option>Blue</option>
                            <option>Black</option>
                        </select>
                    </div>
                </div>
                <div class="product-page-cart">
                    <div class="product-quantity">
                        <input id="product-quantity2" type="text" value="1" readonly class="form-control input-sm">
                    </div>
                    <button class="btn btn-primary" type="submit">Add to cart</button>
                    <a href="shop-item.html" class="btn btn-default">More details</a>
                </div>
            </div>

            <div class="sticker sticker-sale"></div>
        </div>
    </div>
</div>
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
<script src="assets/plugins/owl.carousel/owl.carousel.min.js" type="text/javascript"></script>
<!-- slider for products -->
<script src='assets/plugins/zoom/jquery.zoom.min.js' type="text/javascript"></script><!-- product zoom -->
<script src="assets/plugins/bootstrap-touchspin/bootstrap.touchspin.js" type="text/javascript"></script>
<!-- Quantity -->
<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="assets/plugins/rateit/src/jquery.rateit.js" type="text/javascript"></script>

<script src="assets/corporate/scripts/layout.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function () {
        Layout.init();
        Layout.initOWL();
        Layout.initTwitter();
        Layout.initImageZoom();
        Layout.initTouchspin();
        Layout.initUniform();
    });
</script>
<!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->

</html>