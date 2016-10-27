<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="line-height" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create Message</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"> </script>

</head>
<body>
<jsp:include page="_menu.jsp"/>
<div id="content">
    <h1 class="text">
        Create Message
    </h1>

    <%--
    <a style="color: red; font-weight: bold;">${errorString}</a>
    --%>
    <p style="color: red; font-weight: bold;">${errorString}</p>
    <form method="post" action="${pageContext.request.contextPath}/message/to">
        <table border="0">
            <tr>
                <td class="text">To:</td>
                <td><input type="text" name="to" value="${to}" <c:if test="${not empty to}"> disabled</c:if> /></td>
                <td><input type="submit" class="button" value="find" <c:if test="${not empty to}"> disabled</c:if> /></td>
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
                    <td class="text">Text:</td>
                    <td><label for="uxMessage"></label><textarea style="height: 100px; width: 400px" id="uxMessage" name="text"></textarea></td>
                    <%--<script>$("#uxMessage").replaceWith('<textarea style="height: 100px; width: 400px"  id="uxMessage" name="uxMessage"></textarea>');</script>--%>
                    <td><input type="submit" class="button" value="send"/></td>
                </tr>
            </table>
        </form>
    </c:if>
</div>
<div id="back">
    <jsp:include page="_footer.jsp"/>
</div>

</body>
</html>