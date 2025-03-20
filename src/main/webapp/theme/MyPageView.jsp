<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ page import="org.example.webapp.model.dto.UserDTO" %> --%>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="utf-8" />
    <title>마이페이지 | Next Level</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <!-- 메타 태그 -->
    <meta content="Next Level - 마이페이지" name="description" />
    <meta content="dating, social, next level" name="keywords" />
    <meta content="Next Level" name="author" />

    <link rel="shortcut icon" href="favicon.ico" />

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

    <style>/* 전체 레이아웃 스타일 */
    body {
        background-color: #f9f9f9;
    }

    .main-content {
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 0 15px rgba(0, 0, 0, 0.05);
        padding: 30px;
        margin-bottom: 30px;
    }

    /* 사이드바 스타일 */
    .sidebar-menu {
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.05);
    }

    .sidebar-menu .list-group-item {
        border-left: none;
        border-right: none;
        padding: 12px 20px;
        transition: all 0.3s ease;
    }

    .sidebar-menu .list-group-item:first-child {
        border-top: none;
    }

    .sidebar-menu .list-group-item:last-child {
        border-bottom: none;
    }

    .sidebar-menu .list-group-item i {
        margin-right: 10px;
        color: #e94d1c;
    }

    .sidebar-menu .list-group-item.active {
        background-color: #e94d1c;
        border-color: #e94d1c;
    }

    .sidebar-menu .list-group-item.active i {
        color: #fff;
    }

    .sidebar-menu .list-group-item:hover {
        background-color: #f5f5f5;
    }

    .sidebar-menu .list-group-item.active:hover {
        background-color: #d43e0a;
    }

    /* 파일 업로드 input 숨기기 */
    #profileUpload {
        display: none;
    }

    /* 프로필 섹션 스타일 - 가로 배치로 수정 */
    .profile-container {
        background: linear-gradient(135deg, #f9f9f9, #f5f5f5);
        border-radius: 10px;
        padding: 30px;
        margin-bottom: 30px;
        box-shadow: 0 3px 15px rgba(0, 0, 0, 0.05);
        position: relative;
    }

    .profile-header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 100%;
    }

    .profile-left {
        display: flex;
        align-items: center;
    }

    /* 프로필 이미지 컨테이너 스타일 수정 */
    .profile-image-container {
        position: relative;
        margin-right: 20px;
        cursor: pointer;
    }

    #profileImage {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        object-fit: cover;
        border: 4px solid #fff;
        box-shadow: 0 3px 10px rgba(0, 0, 0, 0.1);
        transition: all 0.3s ease;
    }

    /* 오버레이 효과 추가 */
    .image-overlay {
        position: absolute;
        top: 0;
        left: 0;
        width: 100%;
        height: 100%;
        border-radius: 50%;
        background-color: rgba(0, 0, 0, 0.5);
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: white;
        opacity: 0;
        transition: opacity 0.3s ease;
    }

    .image-overlay i {
        font-size: 20px;
        margin-bottom: 5px;
    }

    .image-overlay span {
        font-size: 12px;
        font-weight: 500;
    }

    /* 호버 시 오버레이 표시 */
    .profile-image-container:hover .image-overlay {
        opacity: 1;
    }

    .profile-info {
        text-align: left;
    }

    .profile-name {
        font-size: 22px;
        font-weight: 600;
        margin: 0 0 8px;
        color: #333;
    }

    .profile-role {
        display: inline-block;
        background-color: #e94d1c;
        color: #fff;
        padding: 3px 10px;
        border-radius: 20px;
        font-size: 13px;
        font-weight: 500;
    }

    .profile-actions {
        margin-left: auto;
    }

    .profile-actions .btn {
        padding: 8px 20px;
        border-radius: 30px;
        font-weight: 500;
        display: flex;
        align-items: center;
        gap: 5px;
        box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
    }

    /* 반응형 디자인을 위한 미디어 쿼리 */
    @media (max-width: 767px) {
        .profile-header {
            flex-direction: column;
            align-items: center;
        }

        .profile-left {
            flex-direction: column;
            align-items: center;
            margin-bottom: 15px;
        }

        .profile-image-container {
            margin-right: 0;
            margin-bottom: 15px;
        }

        .profile-info {
            text-align: center;
            margin-bottom: 15px;
        }

        .profile-actions {
            margin-left: 0;
        }
    }

    /* 정보 카드 스타일 */
    .info-card {
        background: #fff;
        border-radius: 8px;
        padding: 25px;
        margin-bottom: 30px;
        box-shadow: 0 3px 10px rgba(0, 0, 0, 0.05);
        position: relative;
    }

    .info-card h3 {
        font-size: 20px;
        font-weight: 600;
        margin-bottom: 20px;
        color: #333;
        border-bottom: 2px solid #e94d1c;
        padding-bottom: 10px;
        display: inline-block;
    }

    .info-list {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .info-item {
        display: flex;
        align-items: center;
        margin-bottom: 15px;
        padding-bottom: 15px;
        border-bottom: 1px solid #f0f0f0;
    }

    .info-item:last-child {
        margin-bottom: 0;
        padding-bottom: 0;
        border-bottom: none;
    }

    .info-icon {
        color: #e94d1c;
        font-size: 18px;
        width: 30px;
        text-align: center;
        margin-right: 15px;
    }

    .info-label {
        font-weight: 600;
        color: #555;
        width: 100px;
    }

    .info-value {
        color: #333;
        flex: 1;
    }

    /* 선호 취향 태그 스타일 */
    .preference-tags {
        display: flex;
        flex-wrap: wrap;
        gap: 10px;
    }

    .preference-tag {
        background-color: #f5f5f5;
        border-radius: 20px;
        padding: 5px 15px;
        font-size: 14px;
        color: #333;
        display: inline-flex;
        align-items: center;
        gap: 5px;
    }

    .preference-tag i {
        color: #e94d1c;
    }

    </style>
</head>
<body class="ecommerce">
<!-- 헤더 부분은 include로 처리하거나 그대로 유지 -->

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


                    <style>
                        /* 정보 카드 스타일 */
                        .info-card {
                            background: #fff;
                            border-radius: 8px;
                            padding: 25px;
                            margin-bottom: 30px;
                            box-shadow: 0 3px 10px rgba(0, 0, 0, 0.05);
                            position: relative;
                        }

                        .info-card h3 {
                            font-size: 20px;
                            font-weight: 600;
                            margin-bottom: 20px;
                            color: #333;
                            border-bottom: 2px solid #e94d1c;
                            padding-bottom: 10px;
                            display: inline-block;
                        }

                        .info-list {
                            list-style: none;
                            padding: 0;
                            margin: 0;
                        }

                        .info-item {
                            display: flex;
                            align-items: center;
                            margin-bottom: 15px;
                            padding-bottom: 15px;
                            border-bottom: 1px solid #f0f0f0;
                        }

                        .info-item:last-child {
                            margin-bottom: 0;
                            padding-bottom: 0;
                            border-bottom: none;
                        }

                        .info-icon {
                            color: #e94d1c;
                            font-size: 18px;
                            width: 30px;
                            text-align: center;
                            margin-right: 15px;
                        }

                        .info-label {
                            font-weight: 600;
                            color: #555;
                            width: 100px;
                        }

                        .info-value {
                            color: #333;
                            flex: 1;
                        }

                        /* 선호 취향 태그 스타일 */
                        .preference-tags {
                            display: flex;
                            flex-wrap: wrap;
                            gap: 10px;
                        }

                        .preference-tag {
                            background-color: #f5f5f5;
                            border-radius: 20px;
                            padding: 5px 15px;
                            font-size: 14px;
                            color: #333;
                            display: inline-flex;
                            align-items: center;
                            gap: 5px;
                        }

                        .preference-tag i {
                            color: #e94d1c;
                        }
                    </style>
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
<script type="text/javascript">
    $(document).ready(function() {
        // 프로필 이미지 업로드 처리
        document.getElementById('profileUpload').addEventListener('change', function(e) {
            const file = e.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(event) {
                    document.getElementById('profileImage').src = event.target.result;
                };
                reader.readAsDataURL(file);

                // 여기에 이미지 업로드 AJAX 코드 추가
                // const formData = new FormData();
                // formData.append('profileImage', file);
                // $.ajax({
                //     url: '/api/user/profile-image',
                //     type: 'POST',
                //     data: formData,
                //     contentType: false,
                //     processData: false,
                //     success: function(response) {
                //         console.log('프로필 이미지가 성공적으로 업로드되었습니다.');
                //     },
                //     error: function(error) {
                //         console.error('프로필 이미지 업로드 중 오류가 발생했습니다:', error);
                //     }
                // });
            }
        });

        // 사이드바 메뉴 활성화
        $('.sidebar-menu .list-group-item').on('click', function() {
            $('.sidebar-menu .list-group-item').removeClass('active');
            $(this).addClass('active');
        });
    });
</script>
</body>
</html>
