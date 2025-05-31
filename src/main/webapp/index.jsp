<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head><title>Index</title></head>
<body>
<c:choose>
    <c:when test="${sessionScope.get('user') != null}">
        <jsp:forward page="/controller?command=get_profile"/>
    </c:when>
    <c:otherwise>
        <jsp:forward page="/controller?command=get_login"/>
    </c:otherwise>
</c:choose>
</body>
</html>
