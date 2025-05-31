<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Записи к врачу</title>
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
                <h2>Записи к врачу "<c:out value="${doctor.getFullName()}"/>"</h2>
                <a class="button green m0" href="<c:url value="/controller?command=get_all_doctors"/>">Назад</a>
            </div>
        </section>

        <section>
            <form name="getAllAppointments" method="GET" action="controller">
                <input type="hidden" name="command" value="get_all_appointments"/>
                <input type="hidden" name="doctorId" value="${param.get("doctorId")}"/>
                <label>
                    Выберите дату:
                    <input id="dateInput" type="date" name="date"/>
                </label>
                <button type="submit" class="button">Показать</button>
            </form>
        </section>

        <section>
            <table class="text-center">
                <thead>
                <tr>
                    <th>Дата и время</th>
                    <th>ФИО</th>
                    <th colspan="2">Действие</th>
                </tr>
                </thead>
                <tbody>

                <c:choose>
                    <c:when test="${sessionScope.get('user').getRole().isAdministrator()}">
                        <c:forEach items="${appointments}" var="appointment">
                            <tr>
                                <td class="p0"><c:out value="${appointment.getTime()}"/></td>
                                <c:choose>
                                    <c:when test="${!appointment.isEmpty()}">
                                        <td class="p0"><c:out value="${appointment.getPatient().getFullName()}"/></td>
                                        <td class="p0" colspan="2">
                                            <a
                                                class="button link w100 red"
                                                href="<c:url value="/controller?command=get_delete_appointment_form&appointmentId=${appointment.getId()}"/>"
                                            >
                                                Отменить запись
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="3" class="p0">
                                            <a
                                                class="button link w100"
                                                href="<c:url value="/controller?command=get_create_appointment_form&datetime=${appointment.getDatetimeString()}&doctorId=${doctor.getId()}"/>"
                                            >
                                                Записать
                                            </a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:when test="${sessionScope.get('user').getRole().isDoctor()}">
                        <c:forEach items="${appointments}" var="appointment">
                            <tr>
                                <td class="p0"><c:out value="${appointment.getTime()}"/></td>
                                <c:choose>
                                    <c:when test="${!appointment.isEmpty()}">
                                        <td class="p0">
                                            <a
                                                    class="button link w100"
                                                    href="<c:url value="/controller?command=get_medical_card_history&patientId=${appointment.getPatient().getId()}"/>"
                                            >
                                                    <c:out value="${appointment.getPatient().getFullName()}"/>
                                            </a>
                                        </td>
                                        <td class="p0">
                                            <a
                                                    class="button link w100 blue"
                                                    href="<c:url value="/controller?command=get_create_medical_card_history_form&appointmentId=${appointment.getId()}"/>"
                                            >
                                                Добавить запись в историю болезней
                                            </a>
                                        </td>
                                        <td class="p0">
                                            <a
                                                    class="button link w100 red"
                                                    href="<c:url value="/controller?command=get_delete_appointment_form&appointmentId=${appointment.getId()}"/>"
                                            >
                                                Отменить запись
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td colspan="3" class="p0">
                                            <a
                                                    class="button link w100"
                                                    href="<c:url value="/controller?command=get_create_appointment_form&datetime=${appointment.getDatetimeString()}&doctorId=${doctor.getId()}"/>"
                                            >
                                                Записать
                                            </a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
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
<script src="<c:url value="/scripts/date.js"/>"></script>
</body>
</html>
