<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="padding: 50px; background: #86c0e0;">

    Hello ${loggedUserId}

    <c:if test="${loggedUserId != 0 or not empty loggedUserId}">
        <a href="${pageContext.request.contextPath}/user/logout">Log out</a>
    </c:if>

</div>