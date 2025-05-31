<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/content-centered.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/ui.css"/>">
</head>
<body>
<jsp:include page="/jsp/layout/header.jsp"/>

<div class="main">
    <jsp:include page="/jsp/layout/menu.jsp"/>
    <div class="content-area">
        <section class="text-center">
            <h2>Профиль</h2>
            <p>Информация о текущем пользователе</p>
        </section>

        <hr />

        <section>
            <h3>Данные</h3>

            <div class="variants m5">
                <div class="m5">
                    <label for="f">Фамилия</label>
                    <input id="f" class="w100 m0" type="text" value="${user.getSurname()}" disabled/>
                </div>
                <div class="m5">
                    <label for="i">Имя</label>
                    <input id="i" class="w100 m0" type="text" value="${user.getName()}" disabled/>
                </div>
                <div class="m5">
                    <label for="o">Отчество</label>
                    <input id="o" class="w100 m0" type="text" value="${user.getPatronymic()}" disabled/>
                </div>
            </div>

            <div class="variants m5">
                <div class="m5 w100">
                    <label for="l">Логин</label>
                    <input id="l" class="w100 m0" type="text" value="${user.getLogin()}" disabled/>
                </div>
                <div class="m5 w100">
                    <label for="p">Пароль</label>
                    <input id="p" class="w100 m0" type="password" value="${user.getPassword()}" disabled/>
                </div>
            </div>

            <div class="variants m5">
                <div class="m5">
                    <label for="r">Роль</label>
                    <select id="r" class="w100 py5 m0" disabled>
                        <option
                            value="${user.getRole().getDatabaseRoleId()}"
                            selected="selected"
                        >
                            <c:out value="${user.getRole().getName()}"/>
                        </option>
                    </select>
                </div>
            </div>
            <div class="variants m5">
                <div class="m5">
                    <label for="z">Заблокирован</label>
                    <select id="z" class="w100 py5 m0" disabled>
                        <option
                            value="false"
                            <c:if test="${!user.getState().isBlocked()}">
                                selected="selected"
                            </c:if>
                        >
                            Нет
                        </option>
                        <option
                            value="true"
                            <c:if test="${user.getState().isBlocked()}">
                                selected="selected"
                            </c:if>
                        >
                            Да
                        </option>
                    </select>
                </div>
            </div>
        </section>

    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>