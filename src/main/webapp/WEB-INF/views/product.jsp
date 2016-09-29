<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Product Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
    </style>
</head>
<body>
<h1>
    Create Product
</h1>

<c:url var="addAction" value="/product/add" />

<form:form action="${addAction}" commandName="product">
    <table>
        <c:if test="${!empty product.name}">
            <tr>
                <td>
                    <form:label path="code">
                        <spring:message text="Code"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="code"/>
                    <form:hidden path="code" />
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="name">
                    <spring:message text="Product Name"/>
                </form:label>
            </td>
            <td>
                <form:input path="name" />
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">
                    <spring:message text="Price"/>
                </form:label>
            </td>
            <td>
                <form:input path="price" />
            </td>
        </tr>

        <tr>
            <td>
                <form:label path="forSale">
                    <spring:message text="For Sale "/>
                </form:label>
            </td>
            <td>
                <form:input path="forSale" />
            </td>
        </tr>



        <tr>
            <td colspan="2">
                <c:if test="${!empty product.name}">
                    <input type="submit"
                           value="<spring:message text="Edit Product"/>" />
                </c:if>
                <c:if test="${empty product.name}">
                    <input type="submit"
                           value="<spring:message text="Create Product"/>" />
                </c:if>
            </td>
        </tr>
    </table>
</form:form>


<br>
<h3>User List</h3>
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
                <td><a href="<c:url value='/editProduct/${product.code}' />" >Edit</a></td>
                <td><a href="<c:url value='/removeProduct/${product.code}' />" >Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>