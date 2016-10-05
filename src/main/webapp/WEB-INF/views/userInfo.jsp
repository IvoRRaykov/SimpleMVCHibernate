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
<br>
USER INFO

<h1>${user.userName}</h1>
<h1>${user.gender}</h1>
<h1>${user.money}</h1>


<br>
<a href="${pageContext.request.contextPath}manageProducts">Manage My Products</a>


<jsp:include page="_footer.jsp"/>

</body>
</html>
