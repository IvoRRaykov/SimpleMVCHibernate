<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div style="padding: 5px; background: #86c0e0;">

    <c:if test="${not empty loggedUserId}">
        <a href="${pageContext.request.contextPath}/message">Create Message </a>
    </c:if>
    <c:if test="${not empty loggedUserId}">
        |
        <a href="${pageContext.request.contextPath}/message/sent">Sent Messages</a>
    </c:if>

    <c:if test="${not empty loggedUserId}">
        |
        <a href="${pageContext.request.contextPath}/message/received">Received Messages</a>
    </c:if>

</div>