// 폼 유효성 검사
document.addEventListener('DOMContentLoaded', function () {
    $('#userInfoForm').submit(function (e) {
        const nickname = $('#nickname').val();
        const gender = $('#gender').val();
        const birth = $('#birth').val();
        const region = $('#region').val();
        const profile = $('#profile').val();

        console.log('닉네임: ' + nickname);
        console.log('성별: ' + gender);
        console.log('생일: ' + birth);
        console.log('지역: ' + region);
        console.log('프로필: ' + profile);

        if (!nickname || !gender || !birth || !region || !profile) {
            alert('필수 항목을 모두 입력해주세요.');
            e.preventDefault();
            return false;
        }

        return true;
    });
});

// 주소찾기 팝업 함수
function openAddressPopup() {
    window.open("/API/addressAPI.html", "주소찾기", "width=600,height=500,scrollbars=yes");
}
