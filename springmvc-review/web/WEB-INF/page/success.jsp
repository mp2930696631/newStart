<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/14
  Time: 9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String key = (String) session.getAttribute("key");
    System.out.println("页面执行。。。。。。");
%>
操作成功
<%=key %>
</body>
</html>
