<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Sent Messages</title>
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


<jsp:include page="_header.jsp"/>
<jsp:include page="_menu.jsp"/>
<jsp:include page="_messageMenu.jsp"/>

<h3>Sent messages</h3>
<c:if test="${!empty sentMessagesList}">
    <table class="tg">
        <tr>
            <th width="80">Date sent</th>
            <th width="120">To</th>
            <th width="320">Text</th>
            <th>Delete</th>

        </tr>
        <c:forEach items="${sentMessagesList}" var="entry">
            <tr>
                <td>${entry.key.dateSent}</td>
                <td><a href="${pageContext.request.contextPath}/message/to/${entry.value}">${entry.value}</a></td>
                <td>${entry.key.text}</td>
                <td><a href="${pageContext.request.contextPath}/message/s/${entry.key.messageId}">Delete</a></td>

                <%--<td><a href="<c:url value='/product/update/${product.code}' />">Edit</a></td>
                <td><a href="<c:url value='/product/remove/${product.code}' />">Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</c:if>

<jsp:include page="_footer.jsp"/>

</body>
</html>
