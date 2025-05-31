<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="с" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Записать к врачу</title>
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
            <h2>Записать пациента на прием</h2>
            <p>Записать пациента на прием к врачу "<c:out value="${doctor.getFullName()}"/>"</p>
        </section>

        <hr/>

        <section>
            <h3>1. Выбранные дата и время приема: <c:out value="${param.get('datetime')}"/></h3>
        </section>

        <jsp:useBean id="selectedUser" scope="request" type="ru.rsreu.gorobchenko.polyclinicweb.model.user.User"/>
        <с:choose>
            <c:when test="${!selectedUser.isNull()}">
                <section>
                    <h3>2. Выбранный пользователь: <c:out value="${selectedUser.getFullName()}"/></h3>
                </section>
            </c:when>
            <c:otherwise>
                <section>
                    <h3>2. Выберите пациента</h3>

                    <form name="searchUserToCreateAppointmentForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="search_patient_to_create_appointment"/>
                        <input type="hidden" name="doctorId" value="${doctor.getId()}"/>
                        <input type="hidden" name="datetime" value="${param.get("datetime")}"/>
                        <div class="my5">
                            <label class="w100 m0" for="fio">ФИО пациента:</label>
                            <div class="variants w100">
                                <input
                                    id="fio"
                                    class="w100 m0"
                                    type="text"
                                    placeholder="ФИО пацента"
                                    name="query"
                                    value="${param.get('query')}"
                                />
                                <button class="button green mr0" type="submit">Найти</button>
                            </div>
                        </div>
                    </form>

                    <jsp:useBean id="foundUsers" scope="request" type="java.util.List"/>
                    <c:if test="${!foundUsers.isEmpty()}">
                        <p>Результаты поиска:</p>

                        <table class="text-center">
                            <thead>
                            <tr>
                                <th>ФИО</th>
                                <th>Логин</th>
                                <th>Дата регистрации в базе</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${foundUsers}" var="user">
                                <tr>
                                    <td class="p0">
                                        <form name="selectUserToCreateAppointmentForm" method="POST" action="controller">
                                            <input type="hidden" name="command" value="select_patient_to_create_appointment"/>
                                            <input type="hidden" name="doctorId" value="${doctor.getId()}"/>
                                            <input type="hidden" name="selectedUserId" value="${user.getId()}"/>
                                            <input type="hidden" name="datetime" value="${param.get("datetime")}"/>
                                            <button class="button link w100"><c:out value="${user.getFullName()}"/></button>
                                        </form>
                                    </td>
                                    <td class="p0"><c:out value="${user.getLogin()}"/></td>
                                    <td class="p0"><c:out value="${user.getRegistrationDate()}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </section>
            </c:otherwise>
        </с:choose>

        <c:if test="${!selectedUser.isNull()}">
            <hr/>

            <section class="flex-center">
                <div class="variants py5">
                    <p class="red-text"><c:out value="${errorMessage}"/></p>
                </div>
                <div class="variants">
                    <form name="createAppointmentForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="create_appointment"/>
                        <input type="hidden" name="doctorId" value="${doctor.getId()}"/>
                        <input type="hidden" name="patientId" value="${selectedUser.getId()}"/>
                        <input type="hidden" name="datetime" value="${param.get("datetime")}"/>
                        <button class="button green">Записать</button>
                    </form>
                    <button
                       class="button blue"
                       onclick="window.history.back()"
                    >
                        Выбрать другого пользователя
                    </button>
                    <a
                        class="button red"
                        href="<c:url value="/controller?command=get_all_appointments&doctorId=${doctor.getId()}"/>"
                    >
                        Вернуться к записям
                    </a>
                </div>
            </section>
        </c:if>
    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>
