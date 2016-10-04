<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>User Info</title>
</head>
<body>
<jsp:include page="_menu.jsp"/>
USER INFO

${user.userName}
${user.gender}
${user.money}


<jsp:include page="_footer.jsp"/>

</body>
</html>
