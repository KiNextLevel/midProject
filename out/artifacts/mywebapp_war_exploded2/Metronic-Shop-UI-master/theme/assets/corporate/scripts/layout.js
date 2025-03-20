var Layout = function () {

    // IE mode
    var isRTL = false;
    var isIE8 = false;
    var isIE9 = false;
    var isIE10 = false;
    var isIE11 = false;

    var responsive = true;

    var responsiveHandlers = [];

    // 페이지네이션을 위한 변수
    var currentPage = 1; // 기본 페이지
    var totalPages = 5; // 페이지 수 (기본값 설정)

    // AJAX로 데이터를 불러오는 함수
    var loadPageData = function (pageNumber) {
        $.ajax({
            url: '/get-data', // 데이터를 가져오는 API 주소 (예시)
            type: 'GET',
            data: { page: pageNumber }, // 페이지 번호를 전송
            success: function (response) {
                // 데이터가 성공적으로 로드되면
                // 1. 페이지 데이터 출력 (예: 상품 리스트 업데이트)
                // 2. 페이지네이션 UI 갱신
                updatePagination(response.totalPages, pageNumber);
                renderData(response.data); // 데이터를 HTML로 렌더링
            },
            error: function () {
                alert("페이지 데이터를 불러오는 데 실패했습니다.");
            }
        });
    };

    // 페이지네이션 UI 업데이트 함수
    var updatePagination = function (totalPages, currentPage) {
        // 페이지 수가 변경되면 totalPages를 업데이트
        var paginationHtml = '';
        for (var i = 1; i <= totalPages; i++) {
            paginationHtml += '<li' + (i === currentPage ? ' class="active"' : '') + '><a href="javascript:;" data-page="' + i + '">' + i + '</a></li>';
        }
        // 페이지네이션 HTML을 업데이트
        $('.pagination').html(paginationHtml);

        // "이전", "다음" 버튼 갱신
        if (currentPage > 1) {
            $('.pagination').prepend('<li><a href="javascript:;" data-page="' + (currentPage - 1) + '">&laquo;</a></li>');
        } else {
            $('.pagination').prepend('<li class="disabled"><a href="javascript:;">&laquo;</a></li>');
        }

        if (currentPage < totalPages) {
            $('.pagination').append('<li><a href="javascript:;" data-page="' + (currentPage + 1) + '">&raquo;</a></li>');
        } else {
            $('.pagination').append('<li class="disabled"><a href="javascript:;">&raquo;</a></li>');
        }
    };

    // 데이터를 HTML로 렌더링하는 함수
    var renderData = function (data) {
        var htmlContent = '';
        // 예시: 데이터가 상품 리스트일 경우
        data.forEach(function (item) {
            htmlContent += '<div class="item">' +
                '<h4>' + item.name + '</h4>' +
                '<p>' + item.description + '</p>' +
                '</div>';
        });
        $('#item-container').html(htmlContent); // 데이터를 표시할 컨테이너에 삽입
    };

    // 페이지네이션 버튼 클릭 이벤트
    var handlePaginationClick = function () {
        $('body').on('click', '.pagination a', function () {
            var pageNumber = $(this).data('page'); // 클릭된 페이지 번호
            if (pageNumber && pageNumber !== currentPage) {
                currentPage = pageNumber;
                loadPageData(currentPage); // 페이지 데이터 로딩
            }
        });
    };

    // 페이지 데이터 로딩 및 페이지네이션 초기화
    var initPagination = function () {
        loadPageData(currentPage); // 첫 번째 페이지 데이터 로딩
        handlePaginationClick();    // 페이지네이션 클릭 이벤트 처리
    };

    var handleInit = function() {

        if ($('body').css('direction') === 'rtl') {
            isRTL = true;
        }

        isIE8 = !! navigator.userAgent.match(/MSIE 8.0/);
        isIE9 = !! navigator.userAgent.match(/MSIE 9.0/);
        isIE10 = !! navigator.userAgent.match(/MSIE 10.0/);
        isIE11 = !! navigator.userAgent.match(/MSIE 11.0/);

        if (isIE10) {
            jQuery('html').addClass('ie10'); // detect IE10 version
        }
        if (isIE11) {
            jQuery('html').addClass('ie11'); // detect IE11 version
        }
    }

    // runs callback functions set by App.addResponsiveHandler().
    var runResponsiveHandlers = function () {
        // reinitialize other subscribed elements
        for (var i in responsiveHandlers) {
            var each = responsiveHandlers[i];
            each.call();
        }
    }

    // handle the layout reinitialization on window resize
    var handleResponsiveOnResize = function () {
        var resize;
        if (isIE8) {
            var currheight;
            $(window).resize(function () {
                if (currheight == document.documentElement.clientHeight) {
                    return; //quite event since only body resized not window.
                }
                if (resize) {
                    clearTimeout(resize);
                }
                resize = setTimeout(function () {
                    runResponsiveHandlers();
                }, 50); // wait 50ms until window resize finishes.
                currheight = document.documentElement.clientHeight; // store last body client height
            });
        } else {
            $(window).resize(function () {
                if (resize) {
                    clearTimeout(resize);
                }
                resize = setTimeout(function () {
                    runResponsiveHandlers();
                }, 50); // wait 50ms until window resize finishes.
            });
        }
    }

    var handleIEFixes = function() {
        //fix html5 placeholder attribute for ie7 & ie8
        if (isIE8 || isIE9) { // ie8 & ie9
            // this is html5 placeholder fix for inputs, inputs with placeholder-no-fix class will be skipped(e.g: we need this for password fields)
            jQuery('input[placeholder]:not(.placeholder-no-fix), textarea[placeholder]:not(.placeholder-no-fix)').each(function () {

                var input = jQuery(this);

                if (input.val() == '' && input.attr("placeholder") != '') {
                    input.addClass("placeholder").val(input.attr('placeholder'));
                }

                input.focus(function () {
                    if (input.val() == input.attr('placeholder')) {
                        input.val('');
                    }
                });

                input.blur(function () {
                    if (input.val() == '' || input.val() == input.attr('placeholder')) {
                        input.val(input.attr('placeholder'));
                    }
                });
            });
        }
    }

    // Handles scrollable contents using jQuery SlimScroll plugin.
    var handleScrollers = function () {
        $('.scroller').each(function () {
            var height;
            if ($(this).attr("data-height")) {
                height = $(this).attr("data-height");
            } else {
                height = $(this).css('height');
            }
            $(this).slimScroll({
                allowPageScroll: true, // allow page scroll when the element scroll is ended
                size: '7px',
                color: ($(this).attr("data-handle-color")  ? $(this).attr("data-handle-color") : '#bbb'),
                railColor: ($(this).attr("data-rail-color")  ? $(this).attr("data-rail-color") : '#eaeaea'),
                position: isRTL ? 'left' : 'right',
                height: height,
                alwaysVisible: ($(this).attr("data-always-visible") == "1" ? true : false),
                railVisible: ($(this).attr("data-rail-visible") == "1" ? true : false),
                disableFadeOut: true
            });
        });
    }

    var handleSearch = function() {
        $('.search-btn').click(function () {
            if($('.search-btn').hasClass('show-search-icon')){
                if ($(window).width()>767) {
                    $('.search-box').fadeOut(300);
                } else {
                    $('.search-box').fadeOut(0);
                }
                $('.search-btn').removeClass('show-search-icon');
            } else {
                if ($(window).width()>767) {
                    $('.search-box').fadeIn(300);
                } else {
                    $('.search-box').fadeIn(0);
                }
                $('.search-btn').addClass('show-search-icon');
            }
        });

        // close search box on body click
        if($('.search-btn').size() != 0) {
            $('.search-box, .search-btn').on('click', function(e){
                e.stopPropagation();
            });

            $('body').on('click', function() {
                if ($('.search-btn').hasClass('show-search-icon')) {
                    $('.search-btn').removeClass("show-search-icon");
                    $('.search-box').fadeOut(300);
                }
            });
        }
    }

    var handleMenu = function() {
        $(".header .navbar-toggle").click(function () {
            if ($(".header .navbar-collapse").hasClass("open")) {
                $(".header .navbar-collapse").slideDown(300)
                    .removeClass("open");
            } else {
                $(".header .navbar-collapse").slideDown(300)
                    .addClass("open");
            }
        });
    }
    var handleSubMenuExt = function() {
        $(".header-navigation .dropdown").on("hover", function() {
            if ($(this).children(".header-navigation-content-ext").show()) {
                if ($(".header-navigation-content-ext").height()>=$(".header-navigation-description").height()) {
                    $(".header-navigation-description").css("height", $(".header-navigation-content-ext").height()+22);
                }
            }
        });
    }

    var handleSidebarMenu = function () {
        $(".sidebar .dropdown > a").click(function (event) {
            if ($(this).next().hasClass('dropdown-menu')) {
                event.preventDefault();
                if ($(this).hasClass("collapsed") == false) {
                    $(this).addClass("collapsed");
                    $(this).siblings(".dropdown-menu").slideDown(300);
                } else {
                    $(this).removeClass("collapsed");
                    $(this).siblings(".dropdown-menu").slideUp(300);
                }
            }
        });
    }

    function handleDifInits() {
        $(".header .navbar-toggle span:nth-child(2)").addClass("short-icon-bar");
        $(".header .navbar-toggle span:nth-child(4)").addClass("short-icon-bar");
    }

    function handleUniform() {
        if (!jQuery().uniform) {
            return;
        }
        var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
        if (test.size() > 0) {
            test.each(function () {
                if ($(this).parents(".checker").size() == 0) {
                    $(this).show();
                    $(this).uniform();
                }
            });
        }
    }

    var handleFancybox = function () {
        if (!jQuery.fancybox) {
            return;
        }

        jQuery(".fancybox-fast-view").fancybox();

        if (jQuery(".fancybox-button").size() > 0) {
            jQuery(".fancybox-button").fancybox({
                groupAttr: 'data-rel',
                prevEffect: 'none',
                nextEffect: 'none',
                closeBtn: true,
                helpers: {
                    title: {
                        type: 'inside'
                    }
                }
            });

            $('.fancybox-video').fancybox({
                type: 'iframe'
            });
        }
    }

    // Handles Bootstrap Accordions.
    var handleAccordions = function () {

        jQuery('body').on('shown.bs.collapse', '.accordion.scrollable', function (e) {
            Layout.scrollTo($(e.target), -100);
        });

    }

    // Handles Bootstrap Tabs.
    var handleTabs = function () {
        // fix content height on tab click
        $('body').on('shown.bs.tab', '.nav.nav-tabs', function () {
            handleSidebarAndContentHeight();
        });

        //activate tab if tab id provided in the URL
        if (location.hash) {
            var tabid = location.hash.substr(1);
            $('a[href="#' + tabid + '"]').click();
        }
    }

    var handleMobiToggler = function () {
        $(".mobi-toggler").on("click", function(event) {
            event.preventDefault();//the default action of the event will not be triggered

            $(".header").toggleClass("menuOpened");
            $(".header").find(".header-navigation").toggle(300);
        });
    }

    var handleTheme = function () {

        var panel = $('.color-panel');

        // handle theme colors
        var setColor = function (color) {
            $('#style-color').attr("href", "../../assets/corporate/css/themes/" + color + ".css");
            $('.corporate .site-logo img').attr("src", "../../assets/corporate/img/logos/logo-corp-" + color + ".png");
            $('.ecommerce .site-logo img').attr("src", "../../assets/corporate/img/logos/logo-shop-" + color + ".png");
        }

        $('.icon-color', panel).click(function () {
            $('.color-mode').show();
            $('.icon-color-close').show();
        });

        $('.icon-color-close', panel).click(function () {
            $('.color-mode').hide();
            $('.icon-color-close').hide();
        });

        $('li', panel).click(function () {
            var color = $(this).attr("data-style");
            setColor(color);
            $('.inline li', panel).removeClass("current");
            $(this).addClass("current");
        });
    }

    return {
        init: function () {
            // init core variables
            handleTheme();
            handleInit();
            handleResponsiveOnResize();
            handleIEFixes();
            handleSearch();
            handleFancybox();
            handleDifInits();
            handleSidebarMenu();
            handleAccordions();
            handleMenu();
            handleScrollers();
            handleSubMenuExt();
            handleMobiToggler();

            // 페이지네이션 초기화
            initPagination(); // 페이지네이션 관련 함수 호출
        },

        initUniform: function (els) {
            if (els) {
                jQuery(els).each(function () {
                    if ($(this).parents(".checker").size() == 0) {
                        $(this).show();
                        $(this).uniform();
                    }
                });
            } else {
                handleUniform();
            }
        },

        initTwitter: function () {
            !function(d,s,id){
                var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}
            }(document,"script","twitter-wjs");
        },

        initTouchspin: function () {
            $(".product-quantity .form-control").TouchSpin({
                buttondown_class: "btn quantity-down",
                buttonup_class: "btn quantity-up"
            });
            $(".quantity-down").html("<i class='fa fa-angle-down'></i>");
            $(".quantity-up").html("<i class='fa fa-angle-up'></i>");
        },

        initFixHeaderWithPreHeader: function () {
            jQuery(window).scroll(function() {
                if (jQuery(window).scrollTop()>37){
                    jQuery("body").addClass("page-header-fixed");
                }
                else {
                    jQuery("body").removeClass("page-header-fixed");
                }
            });
        },

        initNavScrolling: function () {
            function NavScrolling () {
                if (jQuery(window).scrollTop()>60){
                    jQuery(".header").addClass("reduce-header");
                }
                else {
                    jQuery(".header").removeClass("reduce-header");
                }
            }

            NavScrolling();

            jQuery(window).scroll(function() {
                NavScrolling ();
            });
        },

        initOWL: function () {
            $(".owl-carousel6-brands").owlCarousel({
                pagination: false,
                navigation: true,
                items: 6,
                addClassActive: true,
                itemsCustom : [
                    [0, 1],
                    [320, 1],
                    [480, 2],
                    [700, 3],
                    [975, 5],
                    [1200, 6],
                    [1400, 6],
                    [1600, 6]
                ],
            });

            $(".owl-carousel5").owlCarousel({
                pagination: false,
                navigation: true,
                items: 5,
                addClassActive: true,
                itemsCustom : [
                    [0, 1],
                    [320, 1],
                    [480, 2],
                    [660, 2],
                    [700, 3],
                    [768, 3],
                    [992, 4],
                    [1024, 4],
                    [1200, 5],
                    [1400, 5],
                    [1600, 5]
                ],
            });

            $(".owl-carousel4").owlCarousel({
                pagination: false,
                navigation: true,
                items: 4,
                addClassActive: true,
            });

            $(".owl-carousel3").owlCarousel({
                pagination: false,
                navigation: true,
                items: 3,
                addClassActive: true,
                itemsCustom : [
                    [0, 1],
                    [320, 1],
                    [480, 2],
                    [700, 3],
                    [768, 2],
                    [1024, 3],
                    [1200, 3],
                    [1400, 3],
                    [1600, 3]
                ],
            });

            $(".owl-carousel2").owlCarousel({
                pagination: false,
                navigation: true,
                items: 2,
                addClassActive: true,
                itemsCustom : [
                    [0, 1],
                    [320, 1],
                    [480, 2],
                    [700, 3],
                    [975, 2],
                    [1200, 2],
                    [1400, 2],
                    [1600, 2]
                ],
            });
        },



        initImageZoom: function () {
            $('.product-main-image').zoom({url: $('.product-main-image img').attr('data-BigImgSrc')});
        },

        initAgeSliderRange: function () { //initSliderRange 에서 이름 변경함함
            $( "#height-slider-range" ).slider({
                range: true,
                min: 19,   // 최소값을 19로 설정 (나이의 시작)
                max: 100,  // 최대값을 100으로 설정 (나이의 끝)
                values: [ 25, 60 ],  // 초기 값 (예: 25세 ~ 60세)
                slide: function( event, ui ) {
                    // 슬라이더가 움직일 때마다 나이 범위를 업데이트
                    $( "#ageAmount" ).val( ui.values[ 0 ] + "세 - " + ui.values[ 1 ] + "세" );
                }
            });

            // 슬라이더 초기값을 표시
            $( "#ageAmount" ).val( $( "#age-slider-range" ).slider( "values", 0 ) + "세 - " + $( "#age-slider-range" ).slider( "values", 1 ) + "세" );
        },

        initHeightSliderRange: function () { //initSliderRange 에서 이름 변경함함
            $( "#height-slider-range" ).slider({
                range: true,
                min: 0,   // 최소값을 0로 설정
                max: 300,  // 최대값을 300으로 설정
                values: [ 140, 190 ],  // 초기 값
                slide: function( event, ui ) {
                    // 슬라이더가 움직일 때마다 나이 범위를 업데이트
                    $( "#heightAmount" ).val( ui.values[ 0 ] + "cm - " + ui.values[ 1 ] + "cm" );
                }
            });

            // 슬라이더 초기값을 표시
            $( "#heightAmount" ).val( $( "#height-slider-range" ).slider( "values", 0 ) + "세 - " + $( "#height-slider-range" ).slider( "values", 1 ) + "세" );
        },

        // wrapper function to scroll(focus) to an element
        scrollTo: function (el, offeset) {
            var pos = (el && el.size() > 0) ? el.offset().top : 0;
            if (el) {
                if ($('body').hasClass('page-header-fixed')) {
                    pos = pos - $('.header').height();
                }
                pos = pos + (offeset ? offeset : -1 * el.height());
            }

            jQuery('html,body').animate({
                scrollTop: pos
            }, 'slow');
        },

        //public function to add callback a function which will be called on window resize
        addResponsiveHandler: function (func) {
            responsiveHandlers.push(func);
        },

        scrollTop: function () {
            App.scrollTo();
        },

        gridOption1: function () {
            $(function(){
                $('.grid-v1').mixitup();
            });
        }

    };
}();