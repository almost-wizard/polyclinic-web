<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Войти</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/content-centered.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/ui.css"/>">
</head>
<body>
<jsp:include page="/jsp/layout/header.jsp"/>

<div class="main">
    <div class="content-area">
        <section class="text-center">
            <h2>Авторизация</h2>
            <p class="red-text"><c:out value="${errorMessage}"/></p>
        </section>

        <section>
            <form name="loginForm" method="POST" action="<c:url value="/controller"/>">
                <input type="hidden" name="command" value="login" />
                <input type="text" placeholder="Логин" name="login"/>
                <input type="password" placeholder="Пароль" name="password"/>
                <button class="button green" type="submit">Войти</button>
            </form>
        </section>
    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>
