<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>

</head>
<body onload='document.loginForm.username.focus();'>

<jsp:include page="_menu.jsp"/>

<h1></h1>
<div id="content">
    <div id="login-box">

        <h3>Login with Username and Password</h3>

        <c:if test="${not empty errorString}">
            <div class="error">${errorString}</div>
        </c:if>
        <c:if test="${not empty msg}">
            <div class="msg">${msg}</div>
        </c:if>
        <c:if test="${not empty confirmedMessage}">
            <div class="msg">${confirmedMessage}</div>
        </c:if>

        <form name='loginForm'
              action="<c:url value='/login' />" method='POST'>

            <table>
                <tr>
                    <td class="text">User:</td>
                    <td><input type='text' name='username'></td>
                </tr>
                <tr>
                    <td class="text">Password:</td>
                    <td><input type='password' name='password'/></td>
                </tr>
                <tr>

                    <td colspan='2'><input style="margin-top: 20px;" class="button" name="submit" type="submit"
                                           value="Log in"/></td>
                </tr>
            </table>

        </form>
    </div>
</div>
<div id="back"><jsp:include page="_footer.jsp"/></div>
</body>
</html>
