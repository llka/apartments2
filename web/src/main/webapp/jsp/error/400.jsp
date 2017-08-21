<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="context" scope="page" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>400</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link type="text/css" rel="stylesheet" href="${context}/css/common.css"/>
    <%-- icon --%>
    <link rel="shortcut icon" href="${context}/image/bj_icon.ico"/>
</head>
<body>
    <c:import url="${context}/WEB-INF/jspf/header.jsp"/>

    <div class="row">
        <div class="col-1"></div>
        <div class="col-10">
            <div class = "description">
                <h1 style="text-align: center">400 Error - Bad Request</h1>
                Request from ${pageContext.errorData.requestURI} is failed
                <br/>
                Servlet name or type: ${pageContext.errorData.servletName}
                <br/>
                Status code: ${pageContext.errorData.statusCode}
                <br/>
                Exception: ${pageContext.errorData.throwable}
            </div>
        </div>
    </div>
</body>
</html>

