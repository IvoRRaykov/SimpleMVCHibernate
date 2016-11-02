<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>User Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>

</head>
<body>
<jsp:include page="_menu.jsp"/>

<div id="content">

<c:if test="${empty user.userName}">
    <h1 class="text">
        Register User
    </h1>
</c:if>
<c:if test="${!empty user.userName}">
    <h1 class="text">
        Update User
    </h1>
</c:if>


<Table>
    <tr>
        <td class="text" >Pick avatar</td>
        <td><a href="${pageContext.request.contextPath}/admin/users"><img src="${avatar}"/></a></td>
    </tr>
</Table>

<select name="roles" form="submt">
    <c:forEach items="${roles}" var="role">
        <option value="${role.role}">${role.role}</option>
    </c:forEach>
</select>

<c:url var="addAction" value="/admin/registerUser"/>
<form:form method="post" action="${addAction}" commandName="user" id="submt">
    <table>
        <form:hidden path="avatar" value="${avatar}"/>


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
        </tr>

        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.userName}">
                    <input type="submit" class="button"
                           value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.userName}">
                    <input type="submit" class="button"
                           value="<spring:message text="Register User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


<br>
<h3 class="text">User List</h3>
<c:if test="${!empty listUsers}">
    <table class="tg">
        <tr>
            <th width="80">User ID</th>
            <th width="120">User Name</th>
            <th width="120">User Password</th>
            <th width="120">User Money</th>

            <th width="120">User Gender</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>${user.password}</td>
                <td>${user.money}</td>
                <td>${user.gender}</td>
                <td><a href="<c:url value='/admin/editUser/${user.id}' />">Edit</a></td>
                <td><a href="<c:url value='/admin/removeUser/${user.id}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</div>
<div id="back"><jsp:include page="_footer.jsp"/></div>

</body>
</html>