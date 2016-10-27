<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Sent Messages</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>
    <style>
        table {
            table-layout: fixed;
            width: 310px;
        }

        table td {
            width: 100px;
            word-wrap: break-word;
        }
    </style>
</head>
<body>


<jsp:include page="_menu.jsp"/>

<div id="content">
<h3 class="text">Sent messages</h3>
<c:if test="${!empty sentMessagesList}">
    <table class="tg">
        <tr>
            <th width="80">Date sent</th>
            <th width="120">To</th>
            <th width="320">Text</th>

        </tr>
        <c:forEach items="${sentMessagesList}" var="entry">
            <tr>
                <td align="center">${entry.key.dateSent}</td>
                <td align="center"><a href="${pageContext.request.contextPath}/message/to/${entry.value}">${entry.value}</a></td>
                <td align="center">${entry.key.text}</td>
                <td align="center"> <a href="<c:url value='${pageContext.request.contextPath}/message/s/${entry.key.messageId}' />"><img class="no_border" id="icon" src="${pageContext.request.contextPath}/../resources/icons/trash.png"></a></td>

                <%--<td><a href="<c:url value='/product/update/${product.code}' />">Edit</a></td>
                <td><a href="<c:url value='/product/remove/${product.code}' />">Delete</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</c:if>

</div>
<div id="back"><jsp:include page="_footer.jsp"/></div>

</body>
</html>
