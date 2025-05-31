<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>График врача</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/content-simple.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/ui.css"/>">
    <style>
        .calendar-table th, td { padding: 25px 0; border: 1px solid #686262 }
        .working-day { background-color: #d4edda; position: relative; }
        .non-working-day { background-color: #f8d7da; position: relative; }
        .today { text-decoration-line: underline; font-weight: bold }
        .day-cell:hover .pseudo-button { display: flex; }
        .pseudo-button {
            display: none;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
<jsp:include page="/jsp/layout/header.jsp"/>

<div class="main">
    <jsp:include page="/jsp/layout/menu.jsp"/>
    <div class="content-area">
        <section>
            <div class="variants">
                <div>
                    <h2>График работы врача "<c:out value="${doctor.getFullName()}"/>" на месяц</h2>
                    <p class="red-text"><c:out value="${errorMessage}"/></p>
                </div>
                <a class="button green m0" href="<c:url value="/controller?command=get_all_doctors"/>">Назад</a>
            </div>
        </section>

        <section>
            <table class="calendar-table text-center">
                <thead>
                <tr>
                    <th>Пн</th><th>Вт</th><th>Ср</th><th>Чт</th><th>Пт</th><th>Сб</th><th>Вс</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="week" items="${weeklySchedule}">
                    <tr>
                        <c:forEach var="dayData" items="${week}">
                            <c:choose>
                                <c:when test="${dayData.isNull()}">
                                    <td></td>
                                </c:when>
                                <c:otherwise>
                                    <td class="day-cell ${dayData.isWorking() ? 'working-day' : 'non-working-day'} ${dayData.isToday() ? 'today' : ''}">
                                        <div><c:out value="${dayData.date}"/></div>
                                        <c:if test="${sessionScope.get('user').getRole().isAdministrator()}">
                                            <form name="editSchedule" method="POST" action="controller">
                                                <input type="hidden" name="command" value="edit_schedule" />
                                                <input type="hidden" name="doctorId" value="${param.get("doctorId")}" />
                                                <input type="hidden" name="date" value="${dayData.date}" />
                                                <div class="pseudo-button">
                                                    <c:choose>
                                                        <c:when test="${dayData.isWorking()}">
                                                            <button class="button s100 no-hover red" type="submit" name="vacation">Назначить выходной</button>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <button class="button s100 no-hover green" type="submit" name="workday">Назначить рабочий день</button>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </form>
                                        </c:if>
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>

    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>

</body>
</html>