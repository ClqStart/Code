<%@ page pageEncoding="utf-8" %>
<html>
<body>

<form action="${pageContext.request.contextPath}/login" method="get">
    用户名：<input type="text" name="username"><br>
    密码： <input type="text" name="password"><br>
    <input type="submit">
</form>
</body>
</html>
