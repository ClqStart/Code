<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.4.1.min.js"></script>
    <script>
      function a() {
        $.post({
          url:"${pageContext.request.contextPath}/a1",
          data:{"name":$("#username").val()},
          success:function (data) {
              alert(data)
          }
        })
      }
    </script>

  </head>

  <body>
  用户名：<input type="text" id = "username" onblur="a()">

  </body>
</html>
