// 알림 아이콘을 클릭했을 때 토글 기능 추가
document.getElementById("cartBlock").addEventListener("click", function() {
    var cartContent = document.querySelector(".top-cart-content-wrapper");

    // 현재 펼쳐져 있으면 숨기고, 숨겨져 있으면 펼침
    if (cartContent.style.display === "block") {
        cartContent.style.display = "none"; // 숨기기
    } else {
        cartContent.style.display = "block"; // 펼치기
    }
});

// 클릭 시 알림 목록 토글
document.querySelector(".top-cart-block").addEventListener("click", function() {
    this.classList.toggle("active"); // active 클래스 추가/제거
});