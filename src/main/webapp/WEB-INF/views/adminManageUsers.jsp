<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>User Page</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_messageMenu.jsp"/>


<c:if test="${empty user.userName}">
    <h1>
        Register User
    </h1>
</c:if>
<c:if test="${!empty user.userName}">
    <h1>
        Update User
    </h1>
</c:if>


<Table>
    <tr>
        <td>Pick avatar</td>
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
                        <spring:message text="ID"/>
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
                <form:label path="email">
                    <spring:message text="Email"/>
                </form:label>
            </td>
            <td>
                <form:input path="email"/>
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
        </tr>
        </tr>

        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty user.userName}">
                    <input type="submit"
                           value="<spring:message text="Edit User"/>"/>
                </c:if>
                <c:if test="${empty user.userName}">
                    <input type="submit"
                           value="<spring:message text="Register User"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


<br>
<h3>User List</h3>
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

<jsp:include page="_footer.jsp"/>

</body>
</html>