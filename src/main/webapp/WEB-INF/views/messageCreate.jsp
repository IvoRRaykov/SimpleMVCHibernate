<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Create Message</title>

    <style type="text/css">
        .error {
            color: red;
            font-weight: bold;
        }

    </style>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_messageMenu.jsp"/>

<h1>
    Create Message
</h1>

<%--
<a style="color: red; font-weight: bold;">${errorString}</a>
--%>
<p style="color: red; font-weight: bold;">${errorString}</p>
<form method="post" action="${pageContext.request.contextPath}/message/to">
    <table border="0">
        <tr>
            <td>To</td>
            <td><input type="text" name="to" value="${to}" <c:if test="${not empty to}"> disabled</c:if> /> </td>
            <td><input type="submit" value= "find" <c:if test="${not empty to}"> disabled</c:if> /> </td>
        </tr>
    </table>
</form>
    <c:forEach items="${similarNames}" var="name">
           <a href="${pageContext.request.contextPath}/message/to/${name}"> ${name}</a><br>
    </c:forEach>
<br><br>

<c:if test="${not empty to}">
<form method="post" action="${pageContext.request.contextPath}/message/${to}">
    <table border="0">
        <tr>
            <td>Text</td>
            <td><input type="text" name="text" width="500" height="300"/> </td>
            <td><input type="submit" value= "send" /> </td>
        </tr>
    </table>
</form>
</c:if>

<jsp:include page="_footer.jsp"/>

</body>
</html>