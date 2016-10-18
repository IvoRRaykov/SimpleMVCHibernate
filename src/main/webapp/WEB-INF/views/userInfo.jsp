<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>User Info</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_messageMenu.jsp"/>

<h1>User Info</h1>
<br>
<c:if test="${loggedUserId == user.id}">
    <c:if test="${unreadMessages != 0}">
        <a href="${pageContext.request.contextPath}/message/received"><h1 style="color: #cf0007">You
            Have ${unreadMessages} unread messages</h1></a>
    </c:if>
</c:if>
<img src="${user.avatar}"/>
<h1>name: ${user.userName}</h1>
<h2>email: ${user.email}</h2>
<h4>gender: ${user.gender}</h4>
<h3>money: ${user.money} BGN</h3>


<br>
<a href="${pageContext.request.contextPath}/manageProducts">Manage My Products</a>
<br>
<br>
<br>
<c:if test="${loggedUserId != user.id}"><a href="${pageContext.request.contextPath}/message/to/${user.userName}">Send
    mail</a></c:if>
<br>
<br>
<br>

<jsp:include page="_footer.jsp"/>

</body>
</html>
