<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    <script>function validate(event) {
        var myLength = document.getElementById("pass").value.length;
        if (myLength < 6) {
            alert("Password must contain at least 6 characters")
            event.preventDefault();
            return false;

        } else {
            var string = document.getElementById('pass').value;
            var re = new RegExp("^[a-zA-Z0-9.]*$");
            if (!re.test(string)) {
                alert("Password must contain only letters and digits.")
                event.preventDefault();
                return false;
            }
        }
    }</script>
</head>
<body>
<jsp:include page="_menu.jsp"/>
<div id="content">
    <h1 class="text">
        Edit User
    </h1>

    <tr>
        <td><img src="${avatar}"/></td>
    </tr>
    <c:if test="${not empty errorString}">
        <div class="error"><a>${errorString}</a></div>
    </c:if>
    <c:url var="addAction" value="/user/doUpdate/${avatar.substring(34)}"/>
    <form:form method="POST" action="${addAction}" commandName="user">
        <form:errors path="*" cssClass="error" element="div"/>
        <table>
            <c:if test="${!empty user.userName}">
                <tr>
                    <td>
                        <form:label path="id">
                            <a class="text">Id:</a>
                        </form:label>
                    </td>
                    <td>
                        <form:input path="id" readonly="true" size="8" disabled="true"/>
                        <form:hidden path="id"/>
                    </td>
                </tr>
            </c:if>

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
                    <form:input id="pass" path="password" placeholder="old/new password"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="email">
                        <a class="text">E-mail:</a>
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

                    <input onclick="validate(event)" class="button" type="submit"
                           value="Update Info"/>

                </td>
            </tr>
        </table>
    </form:form>

</div>
<div id="back">
    <jsp:include page="_footer.jsp"/>
</div>

</body>
</html>