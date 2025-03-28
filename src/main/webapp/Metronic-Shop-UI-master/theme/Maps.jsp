<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Maps and Places Autocomplete</title>
    <script>
        async function init() {
            await customElements.whenDefined('gmp-map');

            const map = document.querySelector('gmp-map');
            const marker = document.querySelector('gmp-advanced-marker');
            const userMarker = document.querySelector('#user-marker');
            const placePicker = document.querySelector('gmpx-place-picker');
            const infowindow = new google.maps.InfoWindow();

            // 역삼역 좌표
            const yeoksamStation = { lat: 37.45603534726248, lng: 126.70680636393934  };

            map.innerMap.setOptions({
                mapTypeControl: false
            });

            // 역삼역에 마커 표시
            marker.position = yeoksamStation;
            infowindow.setContent(
                '<strong>역삼역</strong><br>' +
                '<span>서울특별시 강남구 역삼동</span>'
            );
            infowindow.open(map.innerMap, marker);

            // 직선을 그리기 위한 변수
            let polyline = null;

            // 사용자 위치 가져오기
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(
                    (position) => {
                        const userLocation = {
                            lat: position.coords.latitude,
                            lng: position.coords.longitude
                        };

                        // 사용자 위치에 마커 표시
                        userMarker.position = userLocation;

                        // 지도 범위 조정하여 두 지점이 모두 보이게 함
                        const bounds = new google.maps.LatLngBounds();
                        bounds.extend(userLocation);
                        bounds.extend(yeoksamStation);
                        map.innerMap.fitBounds(bounds);

                        // 두 지점 사이 직선 그리기
                        polyline = new google.maps.Polyline({
                            path: [userLocation, yeoksamStation],
                            geodesic: true,
                            strokeColor: '#FF0000',
                            strokeOpacity: 1.0,
                            strokeWeight: 2
                        });

                        polyline.setMap(map.innerMap);

                        // 거리 계산
                        const distance = google.maps.geometry.spherical.computeDistanceBetween(
                            new google.maps.LatLng(userLocation),
                            new google.maps.LatLng(yeoksamStation)
                        );

                        // 거리 정보 표시 - 수정된 부분
                        const distanceInfoWindow = new google.maps.InfoWindow({
                            position: {
                                lat: (userLocation.lat + yeoksamStation.lat) / 2,
                                lng: (userLocation.lng + yeoksamStation.lng) / 2
                            },
                            content: '<div><strong>직선 거리:</strong> ' + (distance / 1000).toFixed(2) + ' km</div>'
                        });

                        distanceInfoWindow.open(map.innerMap);
                    },
                    (error) => {
                        console.error("위치 정보를 가져오는 데 실패했습니다:", error);
                        alert("위치 정보를 가져오는 데 실패했습니다. 브라우저 설정에서 위치 정보 접근을 허용해주세요.");
                    }
                );
            } else {
                alert("이 브라우저에서는 위치 정보 기능을 지원하지 않습니다.");
            }

            placePicker.addEventListener('gmpx-placechange', () => {
                const place = placePicker.value;

                if (!place.location) {
                    window.alert(
                        "No details available for input: '" + place.name + "'"
                    );
                    infowindow.close();
                    marker.position = null;
                    return;
                }

                if (place.viewport) {
                    map.innerMap.fitBounds(place.viewport);
                } else {
                    map.center = place.location;
                    map.zoom = 17;
                }

                marker.position = place.location;
                infowindow.setContent(
                    '<strong>' + place.displayName + '</strong><br>' +
                    '<span>' + place.formattedAddress + '</span>'
                );
                infowindow.open(map.innerMap, marker);

                // 새로운 장소가 선택되면 기존 직선 제거
                if (polyline) {
                    polyline.setMap(null);
                }

                // 사용자 위치가 있다면 새로운 직선 그리기
                if (userMarker.position) {
                    polyline = new google.maps.Polyline({
                        path: [userMarker.position, place.location],
                        geodesic: true,
                        strokeColor: '#FF0000',
                        strokeOpacity: 1.0,
                        strokeWeight: 2
                    });

                    polyline.setMap(map.innerMap);

                    // 거리 계산
                    const distance = google.maps.geometry.spherical.computeDistanceBetween(
                        new google.maps.LatLng(userMarker.position),
                        new google.maps.LatLng(place.location)
                    );

                    // 거리 정보 표시 - 수정된 부분
                    const distanceInfoWindow = new google.maps.InfoWindow({
                        position: {
                            lat: (userMarker.position.lat + place.location.lat) / 2,
                            lng: (userMarker.position.lng + place.location.lng) / 2
                        },
                        content: '<div><strong>직선 거리:</strong> ' + (distance / 1000).toFixed(2) + ' km</div>'
                    });

                    distanceInfoWindow.open(map.innerMap);
                }
            });
        }

        document.addEventListener('DOMContentLoaded', init);
    </script>
    <script type="module" src="https://ajax.googleapis.com/ajax/libs/@googlemaps/extended-component-library/0.6.11/index.min.js">
    </script>
    <!-- Google Maps Geometry 라이브러리 추가 -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDkK3z2I5OQ-SiHobZJ6ULJSx3TxKkEzEc&libraries=geometry"></script>
    <style>
        html,
        body {
            height: 100%;
            margin: 0;
            padding: 0;
        }

        .place-picker-container {
            padding: 20px;
        }
    </style>
</head>
<body>
<gmpx-api-loader key="AIzaSyDkK3z2I5OQ-SiHobZJ6ULJSx3TxKkEzEc" solution-channel="GMP_GE_mapsandplacesautocomplete_v2">
</gmpx-api-loader>
<gmp-map center="37.5006,127.0365" zoom="15" map-id="DEMO_MAP_ID">
    <div slot="control-block-start-inline-start" class="place-picker-container">
        <gmpx-place-picker placeholder="Enter an address"></gmpx-place-picker>
    </div>
    <gmp-advanced-marker></gmp-advanced-marker>
    <gmp-advanced-marker id="user-marker" title="내 위치" icon="https://maps.google.com/mapfiles/ms/icons/blue-dot.png"></gmp-advanced-marker>
</gmp-map>
</body>
</html>
