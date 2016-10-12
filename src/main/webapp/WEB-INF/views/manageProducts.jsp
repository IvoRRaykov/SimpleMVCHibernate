<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Manage Products  Page</title>
    <style type="text/css">
        .error {
            font-weight: bold;
            color: #ff0000;
        }
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
<c:if test="${empty loggedUserId}">
    <jsp:include page="_adminHeader.jsp"/>
    <jsp:include page="_adminMenu.jsp"/>
</c:if>

<c:if test="${not empty loggedUserId}">
    <jsp:include page="_header.jsp"/>
    <jsp:include page="_menu.jsp"/>

</c:if>
<table>
    <tr>
        <td>
            <c:if test="${not empty productToUpdate.code}">

                <h1>Update Product</h1>

                <c:url var="actionUpdate" value="/product/doUpdate"/>
                <form:form method="post" action="${actionUpdate}" commandName="productToUpdate">
                    <table>

                        <tr>
                            <td>
                                <form:label path="code">
                                    <spring:message text="Code"/>
                                </form:label>
                            </td>

                            <c:if test="${!empty productToUpdate.code}">
                                <td>
                                    <form:input path="code" readonly="true" size="8" disabled="true"/>
                                    <form:hidden path="code"/>
                                </td>
                            </c:if>
                            <c:if test="${empty productToUpdate.code}">
                                <td>
                                    <form:input path="code"/>
                                </td>
                                <td>
                                    <form:errors path="code" cssClass="error"/>
                                </td>
                            </c:if>

                        </tr>
                        <tr>
                            <td>
                                <form:label path="name">
                                    <spring:message text="Product Name"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="name"/>
                            </td>
                            <td>
                                <form:errors path="name" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="price">
                                    <spring:message text="Price"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="price"/>
                                <form:errors path="price" cssClass="error"/>

                            </td>
                        </tr>

                        <tr>
                            <td>
                                <form:label path="forSale">
                                    <spring:message text="For Sale "/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="forSale"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit"
                                       value="<spring:message text="Edit Product"/>"/>
                            </td>
                        </tr>

                    </table>

                </form:form>
            </c:if>
        </td>


        <td>
            <c:if test="${not empty loggedUserId}">
                <h1>Create Product</h1>
                <c:url var="addAction" value="/product/create"/>
                <form:form method="post" action="${addAction}" commandName="productToCreate">
                    <table>
                        <tr>
                            <td>
                                <form:label path="code">
                                    <spring:message text="Code"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="code"/>
                            </td>
                            <td>
                                <form:errors path="code" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="name">
                                    <spring:message text="Product Name"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="name"/>
                            </td>
                            <td>
                                <form:errors path="name" cssClass="error"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="price">
                                    <spring:message text="Price"/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="price"/>
                                <form:errors path="price" cssClass="error"/>
                            </td>
                            <td>
                                <form:errors path="price" cssClass="error"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <form:label path="forSale">
                                    <spring:message text="For Sale "/>
                                </form:label>
                            </td>
                            <td>
                                <form:input path="forSale"/>
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2">
                                <input type="submit"
                                       value="<spring:message text="Create Product"/>"/>
                            </td>
                        </tr>
                    </table>

                </form:form>
            </c:if>
        </td>
    </tr>
</table>

<br>
<h3>Product List</h3>
<c:if test="${!empty listProducts}">
    <table class="tg">
        <tr>
            <th width="80">Product Code</th>
            <th width="120">Product Name</th>
            <th width="120">Product Price</th>
            <th width="120">Product For Sale</th>

            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${listProducts}" var="product">
            <tr>
                <td>${product.code}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.forSale}</td>
                <td><a href="<c:url value='/product/update/${product.code}' />">Edit</a></td>
                <td><a href="<c:url value='/product/remove/${product.code}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<c:if test="${empty loggedUser.userName}">
    <jsp:include page="_adminFooter.jsp"/>
</c:if>

<c:if test="${not empty loggedUser.userName}">
    <jsp:include page="_footer.jsp"/>
</c:if>

</body>
</html>