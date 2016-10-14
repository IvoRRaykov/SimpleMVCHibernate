<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <style>
        .error {
            color: red; font-weight: bold;
        }
    </style>
    <title>Edit User</title>

</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_messageMenu.jsp"/>

<h1>
    Edit User
</h1>

<tr><td><img src="${avatar}"/></td></tr>

<a style="color: red;">${errorString}</a>
<c:url var="addAction" value="/user/doUpdate/${avatar.substring(34)}"/>
<form:form method="POST" action="${addAction}" commandName="user">
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
                <form:input path="money" />
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
                <form:input path="gender" />
            </td>
            <td>
                <form:errors path="gender" cssClass="error"/>
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