$(document).ready(function() {
    $("#loadData").click(function() {
        $.ajax({
            url: "api/data",
            type: "GET",
            dataType: "json",
            success: function(response) {
                $("#result").html("<p>데이터 로드 성공!</p><pre>" + JSON.stringify(response, null, 2) + "</pre>");
            },
            error: function(xhr, status, error) {
                $("#result").html("<p>오류 발생: " + error + "</p>");
            }
        });
    });
});
