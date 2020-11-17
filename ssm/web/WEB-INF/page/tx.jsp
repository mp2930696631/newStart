<%@ page import="com.hz.entity.Emp" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2020/11/17
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Emp emp = (Emp) request.getAttribute("emp");
%>
emp name: <%=emp.getEname()%>
<hr>
<br>
emp name: ${emp.ename}
</body>
</html>
