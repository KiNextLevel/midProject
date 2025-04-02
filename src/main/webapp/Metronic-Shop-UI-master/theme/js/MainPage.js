// 전역 변수 (JSP에서 설정된 값 사용)
let allUsers = window.allUsers;
let filteredUsers = [];
let start = 0;
const limit = 9;
let currentUserEmail = "";
const currentYear = new Date().getFullYear();

jQuery(document).ready(function () {
    Layout.init();
    Layout.initOWL();
    Layout.initTwitter();
    Layout.initImageZoom();
    Layout.initTouchspin();
    Layout.initUniform();

    initSliders();
    applyFilters();

    $('#load-more-btn').on('click', function () {
        loadMoreUsers();
    });

    $('input[name="gender"], input[name="distance"], input[name="religion"], input[name="smoking"]').on('change', applyFilters);
});

const alertDatas = JSON.parse(window.alertDatasJson || '[]');
console.log("알람" + alertDatas);

function markAsRead(alertNumber) {
    console.log("로그: 알림 번호 [" + alertNumber + "]");
    $.ajax({
        url: "/updateAlertStatus",
        type: "POST",
        data: { alertNumber: alertNumber },
        dataType: 'json',
        success: function (response) {
            console.log("로그: 상태 업데이트 성공 [" + response + "]");
            $("#alert-" + alertNumber)
                .find(".alert-status")
                .css("background-color", "#4CAF50")
                .text("읽음");

            const alert = alertDatas.find(a => a.alertNumber === alertNumber);
            if (alert) {
                alert.alertIsWatch = true;
            }

            checkAllAlertsRead();
        },
        error: function () {
            console.log("비동기 처리 실패");
        }
    });
}

function checkAllAlertsRead() {
    let allRead = true;
    alertDatas.forEach(function (alert) {
        if (!alert.alertIsWatch) {
            allRead = false;
        }
    });
    if (allRead) {
        $(".top-cart-info").hide();
    } else {
        $(".top-cart-info").show();
    }
}

function renderAlerts() {
    const $scroller = $(".scroller");
    $scroller.empty();

    if (alertDatas.length === 0) {
        $scroller.append('<li><p>받은 알림이 아직 없습니다</p></li>');
    } else {
        alertDatas.forEach(function (data) {
            const alertHtml = `
                <li id="alert-${data.alertNumber}" onclick="markAsRead(${data.alertNumber})">
                    <span class="cart-content-count">${data.alertNumber}</span>
                    <a href="javascript:void(0);" class="alert-content">${data.alertContent}</a>
                    <em>${data.alertDate}</em>
                    <span class="alert-status" style="background-color: ${data.alertIsWatch ? '#4CAF50' : '#F44336'};">
                        ${data.alertIsWatch ? '읽음' : '읽지 않음'}
                    </span>
                </li>
            `;
            $scroller.append(alertHtml);
        });
    }
}

$(document).ready(function () {
    renderAlerts();
    checkAllAlertsRead();
});

$(window).on('load', function () {
    equalizeCardHeights();
});

$(window).on('resize', function () {
    equalizeCardHeights();
});

function equalizeCardHeights() {
    $('.product-item').css('height', 'auto');

    const cardsPerRow = $(window).width() > 992 ? 3 : ($(window).width() > 768 ? 2 : 1);

    for (let i = 0; i < $('.product-item').length; i += cardsPerRow) {
        let maxHeight = 0;

        for (let j = 0; j < cardsPerRow; j++) {
            if (i + j < $('.product-item').length) {
                const cardHeight = $('.product-item').eq(i + j).outerHeight();
                maxHeight = Math.max(maxHeight, cardHeight);
            }
        }

        for (let j = 0; j < cardsPerRow; j++) {
            if (i + j < $('.product-item').length) {
                $('.product-item').eq(i + j).css('height', maxHeight + 'px');
            }
        }
    }
}

function initSliders() {
    $('#age-slider-range').slider({
        range: true,
        min: 0,
        max: 100,
        values: [0, 100],
        slide: function (event, ui) {
            $('#ageAmount').val(ui.values[0] + ' - ' + ui.values[1]);
        },
        change: function (event, ui) {
            applyFilters();
        }
    });
    $('#ageAmount').val($('#age-slider-range').slider('values', 0) + ' - ' + $('#age-slider-range').slider('values', 1));

    $('#height-slider-range').slider({
        range: true,
        min: 130,
        max: 220,
        values: [130, 220],
        slide: function (event, ui) {
            $('#heightAmount').val(ui.values[0] + ' - ' + ui.values[1]);
        },
        change: function (event, ui) {
            applyFilters();
        }
    });
    $('#heightAmount').val($('#height-slider-range').slider('values', 0) + ' - ' + $('#height-slider-range').slider('values', 1));
}

console.log(allUsers)
function applyFilters() {
    const selectedGenders = $('input[name="gender"]:checked').map(function () {
        return $(this).val();
    }).get();
    const selectedDistance = $('input[name="distance"]:checked').val();
    const ageRange = $('#age-slider-range').slider('values');
    const heightRange = $('#height-slider-range').slider('values');
    const selectedReligions = $('input[name="religion"]:checked').map(function () {
        return $(this).val();
    }).get();
    const selectedSmoking = $('input[name="smoking"]:checked').map(function () {
        return $(this).val();
    }).get();

    const currentUserLatitude = parseFloat(window.currentUserLatitude);
    const currentUserLongitude = parseFloat(window.currentUserLongitude);

    console.log("Current User Lat/Lon:", currentUserLatitude, currentUserLongitude);

    filteredUsers = allUsers.filter(user => {
        const birthYear = parseInt(user.userBirth) || 0;
        const userAge = birthYear ? currentYear - birthYear : 0;
        const userGenderStr = user.userGender ? "남" : "여";
        const userSmokeStr = user.userSmoke ? "흡연" : "비흡연";

        const userLatitude = parseFloat(user.userLatitude);
        const userLongitude = parseFloat(user.userLongitude);

        const userDistance = calculateDistance(
            currentUserLatitude,
            currentUserLongitude,
            userLatitude,
            userLongitude
        );

        const passesRole = user.userRole === 0;
        const passesEmail = user.userEmail !== currentUserEmail;
        const passesGender = selectedGenders.length === 0 || selectedGenders.includes(userGenderStr);
        const passesDistance = userDistance <= parseInt(selectedDistance) || selectedDistance === '100';
        const passesAge = userAge >= ageRange[0] && userAge <= ageRange[1];
        const passesHeight = user.userHeight >= heightRange[0] && user.userHeight <= heightRange[1];
        const passesReligion = selectedReligions.length === 0 || selectedReligions.includes(user.userReligion);
        const passesSmoking = selectedSmoking.length === 0 || selectedSmoking.includes(userSmokeStr);

        return (
            passesRole &&
            passesEmail &&
            passesGender &&
            passesDistance &&
            passesAge &&
            passesHeight &&
            passesReligion &&
            passesSmoking
        );
    });

    console.log("Filtered Users:", filteredUsers);
    start = 0;
    $('#product-list').empty();
    loadInitialUsers();
}

function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371;
    const dLat = (lat2 - lat1) * Math.PI / 180;
    const dLon = (lon2 - lon1) * Math.PI / 180;
    const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
        Math.sin(dLon / 2) * Math.sin(dLon / 2);
    const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    const distance = R * c;
    return distance;
}

function loadInitialUsers() {
    const usersToDisplay = filteredUsers.slice(start, start + limit);
    updateProductList(usersToDisplay, false);
    start += limit;
    updateLoadMoreButton();
}

function loadMoreUsers() {
    const usersToDisplay = filteredUsers.slice(start, start + limit);
    updateProductList(usersToDisplay, true);
    start += limit;
    updateLoadMoreButton();
}

function updateLoadMoreButton() {
    if (start >= filteredUsers.length) {
        $('#load-more-btn').text('더 이상 없습니다').prop('disabled', true);
    } else {
        $('#load-more-btn').text('더 보기').prop('disabled', false).show();
    }
}

function updateProductList(users, append) {
    if (!users || users.length === 0) {
        $('#product-list').html('<p>회원이 없습니다</p>');
        return;
    }

    let productListHtml = '';
    users.forEach(function (data) {
        if (!data.userEmail || !data.userNickname) {
            console.log("Invalid user data:", data);
            return;
        }
        console.log("Rendering user:", data.userEmail, data.userNickname, data.userHeight, data.userDescription);
        let year = currentYear - parseInt(data.userBirth);
        console.log("나이 출력" + year);

        productListHtml += `
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="product-item">
                    <div class="pi-img-wrapper">
                        <img src="${data.userProfile || 'default.jpg'}" class="img-responsive" alt="userImage">
                        <div class="product-page-cart">
                            <button class="btn btn-primary" type="submit">메시지 보내기</button>
                            <a href="userDetailPage.do?userEmail=${data.userEmail}" class="btn btn-default">프로필 보기</a>
                        </div>
                    </div>
                    <h3><a href="userDetailPage.do?userEmail=${data.userEmail}">닉네임: <strong>${data.userNickname}</strong></a></h3>
                    <div class="region">지역: ${data.userRegion ? data.userRegion.split(' ')[0] : 'N/A'}</div>
                    <div class="age">나이: ${year}세</div>
                    <div class="description">소개: ${data.userDescription || '설명 없음'}</div>
                </div>
            </div>
        `;
    });

    if (append) {
        $('#product-list').append(productListHtml);
    } else {
        $('#product-list').html(productListHtml);
    }
    equalizeCardHeights();
}