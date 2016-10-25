<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="main_menu">

    <c:if test="${not empty loggedUserName}">
        <div id="right_align">
            <a class="text"> Hello ${loggedUserName}</a>
            <input class="button" type="button"
                   onclick="location.href='${pageContext.request.contextPath}/login?logout';"
                   value="Log out"/>
        </div>
    </c:if>
    <div id="main_menu_inner">
        <input class="button" type="button"
               onclick="location.href='${pageContext.request.contextPath}/';"
               value="Home"/>

        <c:if test="${not empty loggedUserId}">
            <div class="dropdown">

                <input class="button" type="button"
                       onclick="location.href='${pageContext.request.contextPath}/user/${loggedUserId}';"
                       value="My Account"/>
                <div class="dropdown-content">
                    <input style="margin-top: 7px"
                           class="button" type="button"
                           onclick="location.href='${pageContext.request.contextPath}/user/update';"
                           value="Edit"/>
                    <input style="margin-top: 3px"
                           class="button" type="button"
                           onclick="location.href='${pageContext.request.contextPath}/product/manage';"
                           value="My Products"/>

                </div>
            </div>
        </c:if>

        <c:if test="${not empty loggedUserId}">
            <div class="dropdown">

                <input class="button" disabled style="cursor: auto;" type="button"
                       value="Messages"/>
                <div class="dropdown-content">
                    <input style="margin-top: 7px"
                           class="button" type="button"
                           onclick="location.href='${pageContext.request.contextPath}/message';"
                           value="Create"/>
                    <input style="margin-top: 3px"
                           class="button" type="button"
                           onclick="location.href='${pageContext.request.contextPath}/message/sent';"
                           value="Sent"/>
                    <input style="margin-top: 3px"
                           class="button" type="button"
                           onclick="location.href='${pageContext.request.contextPath}/message/received';"
                           value="Received"/>

                </div>
            </div>
        </c:if>

        <c:if test="${not empty loggedUserId}">
            <input class="button" type="button"
                   onclick="location.href='${pageContext.request.contextPath}/product/marketplace';"
                   value="Marketplace"/>
        </c:if>

        <c:if test="${loggedUserId == 0 or empty loggedUserId}">
            <input class="button" type="button"
                   onclick="location.href='${pageContext.request.contextPath}/login';"
                   value="Login"/>
        </c:if>

        <c:if test="${loggedUserId == 0 or empty loggedUserId}">
            <input class="button" type="button"
                   onclick="location.href='${pageContext.request.contextPath}/account/create';"
                   value="Register"/>
        </c:if>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            |
            <a href="${pageContext.request.contextPath}/admin/users">Manage Users</a>
        </sec:authorize>

        <sec:authorize access="hasRole('ROLE_ADMIN')">
            |
            <a href="${pageContext.request.contextPath}/product/manage">Manage Products</a>
        </sec:authorize>
    </div>


</div>