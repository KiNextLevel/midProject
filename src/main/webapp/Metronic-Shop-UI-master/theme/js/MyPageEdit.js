document.addEventListener('DOMContentLoaded', function() {
    const introTextarea = document.getElementById('userDescription');
    const charCount = document.getElementById('charCount');

    // 초기 글자 수 표시
    charCount.textContent = introTextarea.value.length + ' / 200';

    // 입력할 때마다 글자 수 업데이트
    introTextarea.addEventListener('input', function() {
        charCount.textContent = this.value.length + ' / 200';
    });
});