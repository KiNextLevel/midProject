<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>로그인 회원가입</title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<!DOCTYPE html>
<html>
<head>
    <title>nextLevel</title>
    <link rel="stylesheet" type="text/css" href="slide navbar style.css">
    <link href="https://fonts.googleapis.com/css2?family=Jost:wght@500&display=swap" rel="stylesheet">
</head>
<body>
<div class="main">
    <input type="checkbox" id="chk" aria-hidden="true">

    <div class="signup">
        <form action="join.do" method="POST">
            <label for="chk" aria-hidden="true">회원가입</label>
            <input type="text" name="userName" placeholder="User name" required>
            <input type="email" name="userEmail" placeholder="Email" required>
            <input type="password" name="userPassword" placeholder="Password" required>
            <button>가입하기</button>
        </form>
    </div>

    <div class="login">
        <form action="login.do" method="POST">
            <label for="chk" aria-hidden="true">로그인</label>
            <input type="email" name="userEmail" placeholder="Email" required>
            <input type="password" name="userPassword" placeholder="Password" required>
            <button type="submit">로그인</button>
        </form>
    </div>
</div>
</body>
</html>
<!-- partial -->

</body>
</html>