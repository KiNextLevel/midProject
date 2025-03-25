
document.addEventListener("DOMContentLoaded", function() {
    const cartBlock = document.querySelector('.top-cart-block');
    const cartIcon = document.querySelector('.fa-shopping-cart');  // 장바구니 아이콘

    // 아이콘 클릭 시 알림 영역 토글
    cartIcon.addEventListener('click', function() {
    cartBlock.classList.toggle('active');  // active 클래스 토글
    });
});