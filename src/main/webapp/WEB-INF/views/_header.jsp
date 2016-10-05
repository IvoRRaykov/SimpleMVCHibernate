<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="padding: 50px; background: #86c0e0;">

    Hello ${loggedUser.userName}

    <c:if test="${not empty loggedUser.userName}">
        <a href="${pageContext.request.contextPath}/user/logout">Log out</a>
    </c:if>

</div>