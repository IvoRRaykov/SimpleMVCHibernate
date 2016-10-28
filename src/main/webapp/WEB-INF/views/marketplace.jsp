<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Marketplace</title>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>

</head>
<body>

<jsp:include page="_menu.jsp"/>
<div id="content">
    <div id="login-box" style="border: 0; border-radius: 0">
        <h3 class="text" align="center">Marketplace</h3>
        <br>
        <br>
        <c:if test="${!empty productList}">
            <table class="tg">
                <tr>
                    <th>
                        <div>
                            <ul class="the_dropdown">
                                <li><span class="selectedoption">Genre &#x25BC</span>
                                    <ul>
                                        <li><a style="text-decoration: none" href="/product/marketplace">all</a></li>
                                        <c:forEach items="${genresList}" var="genre">
                                            <li><a style="text-decoration: none" href="/product/marketplace/${genre}">${genre}</a></li>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </th>
                    <th width="80">Image</th>
                    <th width="80">Code</th>
                    <th width="250">Name</th>
                    <th width="120">Price</th>
                    <th width="120">Owner</th>
                    <th></th>
                    <th width="60"><a href="<c:url value='/product/downloadList' />"><img class="no_border" id="icon"
                                                                                          src="${pageContext.request.contextPath}/../resources/icons/download.png"></a>
                    </th>
                </tr>
                <c:forEach items="${productList}" var="product">
                    <td style="border-bottom: 0;"></td>
                    <td align="center"><img id="productPicture" class="no_border" style="width: 100px"
                                            height="100px"
                                            src="data:image/jpeg;base64,${product.pictureBase64String}"></td>
                    <td align="center">${product.code}</td>
                    <%--<td align="center" style="font: italic bold 12px/30px Georgia, serif;">${product.name}</td>--%>
                    <td align="center">

                        <p style="color: #9387ff; top: 0;"><sup>${product.genre}</sup></p>
                        <a style="font: italic bold 12px/30px Georgia, serif;">${product.name}</a>

                    </td>
                    <td align="center">
                        <c:if test="${product.price <= loggedUserMoney }">
                            <a>   ${product.price} <c:if test="${product.userAccount.userName != loggedUserName}"><a
                                    style="color: #0ede1a;"><sup> +<fmt:formatNumber
                                    value="${loggedUserMoney-product.price - 0.0005}" maxFractionDigits="2"/></sup></a>
                            </c:if></a>
                        </c:if>
                        <c:if test="${product.price > loggedUserMoney}">

                            <a style="color: #ec0013;">${product.price}<sup> -<fmt:formatNumber
                                    value="${product.price-loggedUserMoney - 0.0005}" maxFractionDigits="2"/></sup></a>
                        </c:if> $
                    </td>
                    <td align="center">
                        <table>
                            <tr style="border: 0;">
                                <td style="border: 0;"><img src="${product.userAccount.avatar}"
                                                            style="width: 30px; height: 30px"></td>
                                <td style="border: 0;"><a
                                        href="<c:url value='/user/${product.userAccount.id}'/> ">${product.userAccount.userName}
                                    <c:if test="${product.userAccount.userName == loggedUserName}"><a class="text">
                                        (You)</a></c:if></a></td>
                            </tr>
                        </table>
                    </td>
                    <td align="center">
                        <c:if test="${product.userAccount.userName == loggedUserName || product.price>loggedUserMoney }">
                            <img class="no_border" id="icon"
                                 src="${pageContext.request.contextPath}/../resources/icons/buyNo.png">
                        </c:if>

                        <sec:authorize access="hasRole('ROLE_USER')">
                            <c:if test="${product.userAccount.userName != loggedUserName && product.price<=loggedUserMoney}">
                                <a href="<c:url value='/product/buy/${product.code}' />"><img class="no_border"
                                                                                              id="icon"
                                                                                              src="${pageContext.request.contextPath}/../resources/icons/buy.png"></a>
                            </c:if>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <c:if test="${product.userAccount.userName != loggedUserName}">
                                <img class="no_border" id="icon"
                                     src="${pageContext.request.contextPath}/../resources/icons/buyNo.png">
                            </c:if>
                        </sec:authorize>
                    </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
        <br>

    </div>
</div>
<div id="back">
    <jsp:include page="_footer.jsp"/>
</div>

</body>
</html>
