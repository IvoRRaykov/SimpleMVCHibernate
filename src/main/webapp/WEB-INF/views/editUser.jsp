<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Product Page</title>

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<h1>
    Login
</h1>

<c:url var="addAction" value="/user/doEditUser"/>

<form:form action="${addAction}" commandName="user">
    <table>
        <c:if test="${!empty user.userName}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" size="8"  disabled="true" />
                    <form:hidden path="id" />
                </td>
            </tr>
        </c:if>
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
            <td>
                <form:label path="money">
                    <spring:message text="Money"/>
                </form:label>
            </td>
            <td>
                <form:input path="money" />
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="gender">
                    <spring:message text="Gender"/>
                </form:label>
            </td>
            <td>
                <form:input path="gender" />
            </td>
        </tr>

        <tr>
            <td colspan="2">

                <input type="submit"
                       value="<spring:message text="Edit user"/>"/>

            </td>
        </tr>
    </table>
</form:form>

<jsp:include page="_footer.jsp"/>

</body>
</html>