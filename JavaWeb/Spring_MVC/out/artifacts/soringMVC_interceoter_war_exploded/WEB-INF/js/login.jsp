<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆页面</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/login" method="post">
    用户名：<input type="text" name="username" required>
    密码 ：<input type="text" name="password" required>
    <input type="submit" value="提交">
</form>

</body>
</html>
