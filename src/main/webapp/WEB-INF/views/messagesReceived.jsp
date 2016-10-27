<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Received Messages</title>
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

    <h3 class="text">Received messages</h3>


    <c:if test="${!empty receivedMessagesList}">
        <table class="tg">
            <tr>
                <th width="80" class="text">Date sent</th>
                <th width="120" class="text">From</th>
                <th width="500" class="text">Text</th>
            </tr>
            <c:forEach items="${receivedMessagesList}" var="entry">
                <tr>
                    <td>${entry.key.dateSent}</td>
                    <td><a href="${pageContext.request.contextPath}/message/to/${entry.value}">${entry.value}</a></td>
                    <td>${entry.key.text}</td>
                    <td align="center"><a
                            href="<c:url value='${pageContext.request.contextPath}/message/r/${entry.key.messageId}' />"><img
                            class="no_border" id="icon"
                            src="${pageContext.request.contextPath}/../resources/icons/trash.png"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

</div>
<div id="back">
    <jsp:include page="_footer.jsp"/>
</div>

</body>
</html>
