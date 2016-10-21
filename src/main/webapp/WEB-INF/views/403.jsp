<%--
  Created by IntelliJ IDEA.
  User: Ivo Raykov
  Date: 18.10.2016 г.
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

</head>
<body style="background: url(images/restricted_area.jpg) no-repeat;  margin:0; padding:0; display:block"  >

<script>
    function goBack() {
        window.history.back();
    }
</script>
</td>

<h1 style="color: #000;">You have no access to this page!</h1>
<table>
    <tr>
        <td><button onclick="goBack()">Go Back</button></td>
        <td><a href="${pageContext.request.contextPath}/login?logout">Log out</a></td>
    </tr>
</table>
</body>
</html>
