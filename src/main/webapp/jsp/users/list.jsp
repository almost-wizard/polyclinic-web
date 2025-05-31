<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Пользователи</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/content-simple.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/ui.css"/>">
</head>
<body>
<jsp:include page="/jsp/layout/header.jsp"/>
<div class="main">
    <jsp:include page="/jsp/layout/menu.jsp"/>
    <div class="content-area">
        <section>
            <div class="variants">
                <form method="GET" action="controller">
                    <input type="hidden" name="command" value="get_all_users"/>
                    <div class="variants">
                        <h2>Пользователи</h2>
                        <select class="py5 ml15" name="status">
                            <option value="">Все</option>
                            <option value="blocked">Заблокированные</option>
                            <option value="unblocked">Незаблокированные</option>
                            <option value="authorized">Авторизованные</option>
                        </select>
                        <button class="button" type="submit">Показать</button>
                    </div>
                </form>
                <c:if test="${sessionScope.get('user').getRole().isSystemAdministrator()}">
                    <a class="button m0" href="<c:url value="/controller?command=get_create_user_form"/>">Добавить</a>
                </c:if>
            </div>
        </section>

        <section>
        </section>

        <section>
            <table class="text-center">
                <thead>
                <tr>
                    <th>id</th>
                    <th>Логин</th>
                    <th>ФИО</th>
                    <th>Роль</th>
                    <th>Дата регистрации</th>
                    <th>Состояние</th>
                    <th colspan="2">Действия</th>
                </tr>
                </thead>
                <tbody>

                <c:choose>
                    <c:when test="${sessionScope.get('user').getRole().isSystemAdministrator()}">
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td class="p0"><c:out value="${user.getId()}"/></td>
                                <td class="p0"><c:out value="${user.getLogin()}"/></td>
                                <td class="p0"><c:out value="${user.getFullName()}"/></td>
                                <td class="p0"><c:out value="${user.getRole().getName()}"/></td>
                                <td class="p0"><c:out value="${user.getRegistrationDate()}"/></td>
                                <td class="p0"><c:out value="${user.getState().getName()}"/></td>
                                <c:choose>
                                    <c:when test="${user.getId() == sessionScope.get('user').getId()}">
                                        <td class="p0" colspan="2">
                                            <a class="button blue link w100"
                                               href="<c:url value="/controller?command=get_edit_user_form&userId=${user.getId()}"/>"
                                            >
                                                Изменить
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="p0">
                                            <a class="button blue link w100"
                                               href="<c:url value="/controller?command=get_edit_user_form&userId=${user.getId()}"/>"
                                            >
                                                Изменить
                                            </a>
                                        </td>
                                        <td class="p0">
                                            <a class="button red link w100"
                                               href="<c:url value="/controller?command=get_delete_user_form&userId=${user.getId()}"/>"
                                            >
                                                Удалить
                                            </a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${sessionScope.get('user').getRole().isModerator()}">
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td class="p0"><c:out value="${user.getId()}"/></td>
                                <td class="p0"><c:out value="${user.getLogin()}"/></td>
                                <td class="p0"><c:out value="${user.getFullName()}"/></td>
                                <td class="p0"><c:out value="${user.getRole().getName()}"/></td>
                                <td class="p0"><c:out value="${user.getRegistrationDate()}"/></td>
                                <td class="p0"><c:out value="${user.getState().getName()}"/></td>
                                <td colspan="2" class="p0">
                                    <c:choose>
                                        <c:when test="${user.getState().isBlocked()}">
                                            <a class="button green link w100"
                                               href="<c:url value="/controller?command=unblock_user&userId=${user.getId()}"/>"
                                            >
                                                Разблокировать
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a class="button red link w100"
                                               href="<c:url value="/controller?command=block_user&userId=${user.getId()}"/>"
                                            >
                                                Заблокировать
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                </c:choose>
                </tbody>
            </table>
        </section>

    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>