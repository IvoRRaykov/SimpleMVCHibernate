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
<h1>User Info</h1>
<br>


<h1>${user.userName}</h1>
<h2>${user.email}</h2>
<h4>${user.gender}</h4>
<h3>${user.money}</h3>


<br>
<a href="${pageContext.request.contextPath}manageProducts">Manage My Products</a>
<br>

<jsp:include page="_footer.jsp"/>

</body>
</html>
