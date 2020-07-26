<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <title>新增书籍 </title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/book/updateBook" method="post">
        <input type="hidden" name="bookID" value="${Qbooks.bookID}">
        书籍名称：<input type="text" name="bookName" value="${Qbooks.bookName}" required><br><br><br>
        书籍数量：<input type="text" name="bookCounts" value="${Qbooks.bookCounts}" required><br><br><br>
        书籍详情：<input type="text" name="detail" value="${Qbooks.detail}"><br><br><br>
        <input type="submit" value="修改">
    </form>

</div>
</body>
</html>
