<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Register User</title>
    <style type="text/css">
        .error {
            color: red; font-weight: bold;
        }

    </style>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<h1>
    Register User
</h1>

<c:url var="addAction" value="/user/doRegister"/>

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
            <td>
                <form:errors path="userName" cssClass="error"/>
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
            <td>
                <form:errors path="password" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="email">
                    <spring:message text="Email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email"/>
            </td>
            <td>
                <form:errors path="email" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="money">
                    <spring:message text="Money"/>
                </form:label>
            </td>
            <td>
                <form:input path="money"/>
            </td>
            <td>
                <form:errors path="money" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="gender">
                    <spring:message text="Gender"/>
                </form:label>
            </td>
            <td>
                <form:input path="gender"/>
            </td>
            <td>
                <form:errors path="gender" cssClass="error"/>
            </td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit"
                       value="<spring:message text="Register User"/>"/>

            </td>
        </tr>
    </table>
</form:form>

<jsp:include page="_footer.jsp"/>

</body>
</html>