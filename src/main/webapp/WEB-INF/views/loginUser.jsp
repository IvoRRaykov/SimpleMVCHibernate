<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Log in</title>

</head>
<body>
<jsp:include page="_menu.jsp"/>
<h1>
    Login
</h1>

<c:url var="addAction" value="/user/doLogin"/>

<form:form action="${addAction}" commandName="user">
    <table>
        <tr>
            <td>
                <form:label path="userName">
                    <spring:message text="User Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="userName"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password">
                    <spring:message text="Password"/>
                </form:label>
            </td>
            <td>
                <form:input path="password"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">

                <input type="submit"
                       value="<spring:message text="Log In"/>"/>

            </td>
        </tr>
    </table>
</form:form>

<jsp:include page="_footer.jsp"/>

</body>
</html>