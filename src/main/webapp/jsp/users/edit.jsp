<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Изменить пользователя</title>
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
            <h2>Изменить пользователя</h2>
        </section>
        <section>
            <p class="red-text"><c:out value="${errorMessage}"/></p>
        </section>

        <hr/>

        <section>
            <form name="createUserForm" method="POST" action="controller">
                <input type="hidden" name="command" value="edit_user"/>
                <input type="hidden" name="userId" value="${param.get("userId")}"/>
                <h3>Данные</h3>

                <div class="variants m5">
                    <div class="m5">
                        <label for="f">Фамилия</label>
                        <input id="f" class="w100 m0" type="text" placeholder="Фамилия" name="surname"
                               value="${user.getSurname()}"/>
                    </div>
                    <div class="m5">
                        <label for="i">Имя</label>
                        <input id="i" class="w100 m0" type="text" placeholder="Имя" name="name"
                               value="${user.getName()}"/>
                    </div>
                    <div class="m5">
                        <label for="o">Отчество</label>
                        <input id="o" class="w100 m0" type="text" placeholder="Отчество" name="patronymic"
                               value="${user.getPatronymic()}"/>
                    </div>
                </div>

                <div class="variants m5">
                    <div class="m5 w100">
                        <label for="l">Логин</label>
                        <input id="l" class="w100 m0" type="text" placeholder="Логин" name="login"
                               value="${user.getLogin()}"/>
                    </div>
                    <div class="m5 w100">
                        <label for="p">Пароль</label>
                        <input id="p" class="w100 m0" type="password" placeholder="Пароль" name="password"
                               value="${user.getPassword()}"/>
                    </div>
                </div>

                <div class="variants m5">
                    <div class="m5">
                        <label for="r">Роль</label>
                        <select id="r" class="w100 py5 m0" name="role">
                            <c:forEach items="${roles}" var="role">
                                <option
                                    value="${role.getDatabaseRoleId()}"
                                    <c:if test="${user.getRole() == role}">
                                        selected="selected"
                                    </c:if>
                                ><c:out value="${role.getName()}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="variants m5">
                    <div class="m5">
                        <label for="z">Заблокирован</label>
                        <select id="z" class="w100 py5 m0" name="isBlocked">
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

                <section class="flex-center">
                    <div class="variants">
                        <button class="button blue" type="submit">Изменить</button>
                        <a
                            class="button red"
                            href="<c:url value="/controller?command=get_all_users"/>"
                        >
                            Вернуться к пользователям
                        </a>
                    </div>
                </section>
            </form>
        </section>
    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>