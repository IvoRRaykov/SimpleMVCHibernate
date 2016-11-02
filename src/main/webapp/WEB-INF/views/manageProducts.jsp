<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Manage Products Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" type="text/css"/>


    <script src="${pageContext.request.contextPath}/../resources/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/../resources/js/scripts.js"></script>

</head>
<body>
<script>var ctx = "${pageContext.request.contextPath}"</script>

<jsp:include page="_menu.jsp"/>
<div id="content">
    <table>
        <tr>
            <td>

            </td>
            <td>
                <c:if test="${not empty productToUpdate.code}">

                    <h1 class="text">Update Product</h1>


                    <c:if test="${not empty errorString}">
                        <div class="error"></div>
                        <p> ${errorString}</p>
                    </c:if>

                    <c:url var="downloadu" value="/uploadPicture/u"/>
                    <form method="POST" action="${downloadu}" enctype="multipart/form-data">
                        <a class="text">File to upload:</a> <input type="file" name="file"><br/>
                        <a class="text">Name:</a> <input type="text" name="name"><br/> <br/>
                        <input type="hidden" name="code" value="${productToUpdate.code}">
                        <input type="submit" value="Upload">
                    </form>
                    <c:if test="${not empty productToUpdate.pictureFilePath}">
                        <img id="productPicture1" style="height: 150px" ; width="150px" ;
                             src="data:image/jpeg;base64,${productToUpdate.pictureBase64String}">
                    </c:if>

                    <c:url var="actionUpdate" value="/product/doUpdate"/>
                    <form:form method="post" action="${actionUpdate}" commandName="productToUpdate">
                        <form:errors path="*" cssClass="error" element="div"/>

                        <table>
                            <form:hidden path="pictureFilePath" value="${productToUpdate.pictureFilePath}" id="updt"/>
                            <tr>
                                <td>
                                    <form:label path="code">
                                        <a class="text">Code:</a>
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
                                </c:if>

                            </tr>
                            <tr>
                                <td>
                                    <form:label path="genre">
                                        <a class="text">Genre:</a>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="genre"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="name">
                                        <a class="text">Name:</a>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <form:label path="price">
                                        <a class="text">Price:</a>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="price"/>

                                </td>
                            </tr>
                            <security:authorize access="hasRole('ROLE_ADMIN')">
                                <tr>
                                    <td>
                                        <a class="text">Owner:</a>
                                    </td>
                                    <td><select name="listUsersNames" form="updt">
                                        <c:forEach items="${listUsersNames}" var="userName">
                                            <option value="${userName}">${userName}</option>
                                        </c:forEach>
                                    </select></td>
                                </tr>
                            </security:authorize>
                            <tr>
                                <td>
                                    <form:label path="forSale">
                                        <a class="text">For Sale:</a>
                                    </form:label>
                                </td>
                                <td>
                                    <form:input path="forSale"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <input type="submit" class="button"
                                           value="<spring:message text="Edit Product"/>"/>
                                </td>
                            </tr>

                        </table>

                    </form:form>
                </c:if>
            </td>


            <td>
                <div class="border">
                    <c:if test="${not empty message}">
                        <div class="msg">${message}</div>
                    </c:if>

                    <c:if test="${empty attach}">
                        <div class="heading">
                            <h1 class="text" style="margin-bottom: 0px">Create Product</h1>

                            <c:if test="${empty productToCreate.pictureFilePath && empty productToCreateCode}">
                                <img class="no_border" style="height: 47px;"
                                     src="${pageContext.request.contextPath}/../resources/icons/1.png">
                            </c:if>
                            <c:if test="${not empty productToCreate.pictureFilePath && empty productToCreateCode}">
                                <img class="no_border" style="height: 47px;"
                                     src="${pageContext.request.contextPath}/../resources/icons/2.png">
                            </c:if>
                            <c:if test="${not empty productToCreateCode}">
                                <img class="no_border" style="height: 47px;"
                                     src="${pageContext.request.contextPath}/../resources/icons/3.png">
                            </c:if>
                        </div>
                    </c:if>

                    <c:if test="${empty productToCreate.pictureFilePath && empty productToCreateCode}">
                        <div id="pictureDiv">
                            <h3 class="text" style="text-align: center">Upload Picture</h3>

                            <c:url var="download" value="/uploadPicture/c"/>
                            <form method="POST" action="${download}" enctype="multipart/form-data">
                                <a class="text">File to upload:</a> <input type="file" name="file"><br/>
                                <a class="text">Name:</a> <input type="text" name="name"><br/> <br/>
                                <input type="submit" class="button" value="Upload">
                            </form>
                        </div>
                    </c:if>

                    <c:if test="${not empty productToCreate.pictureFilePath && empty productToCreateCode}">
                        <div id="productDiv">

                            <img id="productPicture" style="height: 150px" ; width="150px" ;
                                 src="data:image/jpeg;base64,${productToCreate.pictureBase64String}">

                            <h3 class="text" style="text-align: center">Fill info</h3>

                            <c:if test="${not empty errorString}">
                                <div class="error"></div>
                                <p> ${errorString}</p>
                            </c:if>

                            <c:url var="addAction" value="/product/create"/>
                            <form:form method="post" action="${addAction}" commandName="productToCreate" id="crt">
                                <form:errors path="*" cssClass="error" element="div"/>

                                <table>
                                    <form:hidden path="pictureFilePath" value="${productToCreate.pictureFilePath}"/>

                                    <tr>
                                        <td>
                                            <form:label path="code">
                                                <a class="text">Code:</a>
                                            </form:label>
                                        </td>
                                        <td>
                                            <form:input path="code"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <form:label path="genre">
                                                <a class="text">Genre:</a>
                                            </form:label>
                                        </td>
                                        <td>
                                            <form:input path="genre"/>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>
                                            <form:label path="name">
                                                <a class="text">Name:</a>
                                            </form:label>
                                        </td>
                                        <td>
                                            <form:input path="name"/>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>
                                            <form:label path="price">
                                                <a class="text">Price:</a>
                                            </form:label>
                                        </td>
                                        <td>
                                            <form:input path="price"/>
                                        </td>
                                    </tr>
                                    <security:authorize access="hasRole('ROLE_ADMIN')">
                                        <tr>
                                            <td><a class="text">Owner:</a></td>
                                            <td><select name="listUsersNames" form="crt">
                                                <c:forEach items="${listUsersNames}" var="userName">
                                                    <option value="${userName}">${userName}</option>
                                                </c:forEach>
                                            </select></td>
                                        </tr>
                                    </security:authorize>
                                    <tr>
                                        <td>
                                            <form:label path="forSale">
                                                <a class="text">For Sale:</a>

                                            </form:label>
                                        </td>
                                        <td>
                                            <form:input path="forSale"/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="2">
                                            <input type="submit" class="button"
                                                   value="<spring:message text="Fill"/>"/>
                                        </td>
                                    </tr>
                                </table>

                            </form:form>
                        </div>
                    </c:if>

                    <c:if test="${not empty productToCreateCode}">
                        <div id="songsDiv">
                            <h3 class="text" style="text-align: center"> Attach Songs </h3>
                            <table>
                                <tr>
                                    <td>
                                        <input style="width: 300px" type="text" id="input" placeholder="Add Order">
                                    </td>
                                    <td>
                                        <a id="btn" name="btn"><img id="icon" class="no_border"
                                                                    src="${pageContext.request.contextPath}/../resources/icons/plus.png"/></a>
                                    </td>
                                </tr>
                            </table>
                            <ul>
                                    <%--<li><span><i class="fa fa-trash-o"></i></span>Item 1</li>--%>
                            </ul>
                            <input type="hidden" id="codeHandler" value="${productToCreateCode}">
                            <button id="submitbutton" class="button" value="sbmt">Attach</button>
                        </div>
                    </c:if>
                </div>
            </td>
        </tr>
    </table>

    <br>
    <h3 class="text">My Products</h3>
    <c:if test="${!empty productList}">
        <table class="tg">
            <tr>
                <th width="80">Product Picture</th>
                <th width="80">Product Code</th>
                <th width="120">Product Genre</th>
                <th width="120">Product Name</th>
                <th width="120">Product Price</th>
                <th width="120">Product For Sale</th>

                <th width="60">Add Songs</th>
                <th width="60">Edit</th>
                <th width="60">Delete</th>
            </tr>
            <c:forEach items="${productList}" var="product">
                <tr>
                    <td><img class="no_border" id="productImage" style="width: 100px" height="100px"
                             src="data:image/jpeg;base64,${product.pictureBase64String}"></td>
                    <td align="center">${product.code}</td>
                    <td align="center">${product.genre}</td>
                    <td align="center">${product.name}</td>
                    <td align="center">${product.price} $</td>
                    <td align="center">
                        <c:if test="${product.forSale==true}"><img class="no_border" id="icon"
                                                                   src="${pageContext.request.contextPath}/../resources/icons/y.ico"></c:if>
                        <c:if test="${product.forSale!=true}"><img class="no_border" id="icon"
                                                                   src="${pageContext.request.contextPath}/../resources/icons/x.png"></c:if>
                    </td>
                    <td align="center"><a href="<c:url value='/product/attachSongs/${product.code}' />"><img
                            class="no_border" id="icon"
                            src="${pageContext.request.contextPath}/../resources/icons/plus-song.png"></a></td>
                    <td align="center"><a href="<c:url value='/product/update/${product.code}' />"><img
                            class="no_border" id="icon"
                            src="${pageContext.request.contextPath}/../resources/icons/settings.ico"></a></td>
                    <td align="center"><a href="<c:url value='/product/remove/${product.code}' />"><img
                            class="no_border" id="icon"
                            src="${pageContext.request.contextPath}/../resources/icons/trash.png"></a></td>
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