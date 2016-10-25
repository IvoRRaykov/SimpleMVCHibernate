<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Register User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>

</head>
<body>
<jsp:include page="_menu.jsp"/>


<div id="content">
    <div id="login-box">

        <h3 style="margin-bottom: 30px" id="center_vertically" class="text">Register</h3>

        <c:if test="${not empty errorString}">
            <div style="margin-bottom: 30px; margin-top: 60px" class="error">${errorString}</div>
        </c:if>
        <c:if test="${empty errorString}">
            <div style="margin-bottom: 30px; margin-top: 60px"></div>
        </c:if>


        <Table style="margin-bottom: 20px">
            <tr>
                <td class="text">Pick avatar</td>
                <td><a href="${pageContext.request.contextPath}/account/create"><img style="margin-left: 13px"
                                                                                     src="${avatar}"/></a></td>
            </tr>
        </Table>


        <c:url var="addAction" value="/account/doCreate/${avatar.substring(34)}"/>
        <form:form method="post" action="${addAction}" commandName="user">

            <form:errors path="*" cssClass="error" element="div"/>
            <table>

                <tr>
                    <td>
                        <form:label path="userName">
                            <a class="text">User Name:</a>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="userName"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        <form:label path="password">
                            <a class="text">Password:</a>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="password"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        <form:label path="email">
                            <a class="text">Email:</a>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="email"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        <form:label path="money">
                            <a class="text">Money:</a>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="money"/>
                    </td>

                </tr>

                <tr>
                    <td>
                        <form:label path="gender">
                            <a class="text">Gender:</a>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="gender"/>
                    </td>

                </tr>

                <tr>
                    <td colspan="2">
                        <input style="margin-top: 20px" class="button" type="submit"
                               value="Register"/>

                    </td>
                </tr>
            </table>
        </form:form>
    </div>
</div>
<div id="back"><jsp:include page="_footer.jsp"/></div>

</body>
</html>