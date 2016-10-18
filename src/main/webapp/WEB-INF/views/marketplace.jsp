<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_messageMenu.jsp"/>



<br>
<h3>Product List</h3>
<c:if test="${!empty productList}">
    <table class="tg">
        <tr>
            <th width="80">Product Code</th>
            <th width="120">Product Name</th>
            <th width="120">Product Price</th>
            <th width="120">Product Owner</th>

            <th width="60"></th>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.code}</td>
                <td>${product.name}</td>
                <td>
                    <c:if test="${product.price <= loggedUserMoney }">
                        <p>   ${product.price} <c:if test="${product.userAccount.userName != loggedUserName}"><a
                                style="color: #19ac0a;"><sup> +<fmt:formatNumber
                                value="${loggedUserMoney-product.price - 0.0005}" maxFractionDigits="2"/></sup></a>
                        </c:if></p>
                    </c:if>
                    <c:if test="${product.price > loggedUserMoney}">

                        <p style="color: #cf0007;">${product.price}<sup> -<fmt:formatNumber
                                value="${product.price-loggedUserMoney - 0.0005}" maxFractionDigits="2"/></sup></p>
                    </c:if>
                </td>
                <td><a href="user/${product.userAccount.id}">${product.userAccount.userName}</a> </td>
                <td>
                    <c:if test="${product.userAccount.userName == loggedUserName}">
                        <a style="color: #7a7a7a;">Buy</a>
                    </c:if>
                    <c:if test="${product.userAccount.userName != loggedUserName}">
                        <a href="<c:url value='/product/buy/${product.code}' />">Buy</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<br>
<a href="<c:url value='/product/downloadList' />">Download List</a>


<jsp:include page="_footer.jsp"/>

</body>
</html>
