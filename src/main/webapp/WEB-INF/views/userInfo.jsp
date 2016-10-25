<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>User Info</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>

</head>
<body>
<jsp:include page="_menu.jsp"/>

<div id="content">
    <h1 class="text">User Info</h1>
    <br>
    <c:if test="${loggedUserId == user.id}">
        <c:if test="${unreadMessages != 0}">
            <div class="error">
                <a href="${pageContext.request.contextPath}/message/received"><h1 style="color: #cf0007">You
                Have ${unreadMessages} unread messages</h1></a>
            </div>
        </c:if>
    </c:if>
    <img src="${user.avatar}"/>
    <h1 class="text">name: ${user.userName}</h1>
    <h2 class="text">email: ${user.email}</h2>
    <h4 class="text">gender: ${user.gender}</h4>
    <h3 class="text">money: ${user.money} BGN</h3>




    <c:if test="${loggedUserId != user.id}"><a href="${pageContext.request.contextPath}/message/to/${user.userName}">Send
        mail</a></c:if>


</div>
<div id="back"><jsp:include page="_footer.jsp"/></div>
</body>
</html>
