<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lat" value="${param.lat}" />
<c:set var="lng" value="${param.lng}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>지도</title>
    <style>
        /* 지도를 표시할 div의 크기를 전체 화면으로 설정 */
        #map { width: 100%; height: 100%; }
        /* body와 html 태그에 여백 없이 100% 높이 설정 */
        body, html { margin: 0; padding: 0; height: 100%; }
    </style>
</head>
<body>
<div id="map"></div>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=16e5b4c908303629d0e034ffce98abc8&libraries=services"></script>
<script>
    // JSP에서 받은 위도와 경도 값을 JavaScript 변수로 설정
    const lat = ${lat};
    const lng = ${lng};

    // 지도 생성: 지정된 위도/경도를 중심으로 하고, 줌 레벨은 4
    const map = new kakao.maps.Map(document.getElementById('map'), {
        center: new kakao.maps.LatLng(lat, lng),  // 중심 좌표 설정
        level: 7  // 확대 수준 (숫자가 작을수록 더 확대됨)
    });

    // 마커 생성: 사용자의 위치에 마커 표시
    // 개인 정보 보호를 위해 마커는 없애고, 범위만 표시!
    // const marker = new kakao.maps.Marker({
    //     position: new kakao.maps.LatLng(lat, lng),  // 마커 위치
    //     map: map  // 마커를 표시할 지도 객체
    // });

    // 반경 100km의 원(circle) 생성: 중심은 마커와 동일한 위치
    const circle = new kakao.maps.Circle({
        center: new kakao.maps.LatLng(lat, lng),  // 원의 중심 좌표
        radius: 3000,  // 반경:  30km
        strokeWeight: 2,   // 원의 테두리 두께
        strokeColor: '#FF5A5A',  // 테두리 색상
        fillColor: '#FFCCCC',  // 내부 색상
        fillOpacity: 0.3  // 내부 색상 투명도
    });
    // 생성한 원을 지도에 표시
    circle.setMap(map);
</script>
</body>
</html>


