<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Выйти</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/content-centered.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/ui.css"/>">
</head>
<body>
<jsp:include page="/jsp/layout/header.jsp"/>

<div class="main">
    <jsp:include page="/jsp/layout/menu.jsp"/>
    <div class="content-area">
        <section>
            <h2>Вы действительно хотите выйти из аккаунта?</h2>
            <p>Подтвердите для продолжения</p>

            <form name="logoutForm" method="POST" action="controller">
                <input type="hidden" name="command" value="logout"/>
                <button type="submit" class="button red m0 my5">Выйти</button>
            </form>
        </section>
    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>
