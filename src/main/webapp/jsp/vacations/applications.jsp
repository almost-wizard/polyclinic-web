<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Заявления в отпуск</title>
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
                <div>
                    <h2>Заявления в отпуск</h2>
                    <p class="red-text"><c:out value="${errorMessage}"/></p>
                </div>
                <c:if test="${sessionScope.get('user').getRole().isDoctor()}">
                    <a class="button m0" href="<c:url value="/controller?command=get_create_vacation_form"/>">Написать</a>
                </c:if>
            </div>
        </section>

        <section>
            <table class="text-center">
                <thead>
                <tr>
                    <th>ФИО</th>
                    <th>Дата начала</th>
                    <th>Дата окончания</th>
                    <th>Длительность (дней)</th>
                    <c:if test="${sessionScope.get('user').getRole().isAdministrator()}">
                        <th colspan="2">Действия</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${applications}" var="application">
                    <tr>
                        <td class="p0"><c:out value="${application.getDoctor().getFullName()}"/></td>
                        <td class="p0"><c:out value="${application.getStartDate()}"/></td>
                        <td class="p0"><c:out value="${application.getEndDate()}"/></td>
                        <td class="p0"><c:out value="${application.getDaysDuration()}"/></td>

                        <c:if test="${sessionScope.get('user').getRole().isAdministrator()}">
                            <td class="p0">
                                <form name="approveVacationForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="approve_vacation" />
                                    <input type="hidden" name="doctorId" value="${application.getDoctor().getId()}" />
                                    <input type="hidden" name="startDate" value="${application.getStartDate()}" />
                                    <input type="hidden" name="endDate" value="${application.getEndDate()}" />
                                    <button class="button link w100 green">Одобрить</button>
                                </form>
                            </td>
                            <td class="p0">
                                <form name="approveVacationForm" method="POST" action="controller">
                                    <input type="hidden" name="command" value="reject_vacation" />
                                    <input type="hidden" name="doctorId" value="${application.getDoctor().getId()}" />
                                    <input type="hidden" name="startDate" value="${application.getStartDate()}" />
                                    <input type="hidden" name="endDate" value="${application.getEndDate()}" />
                                    <button class="button link w100 red">Отказать</button>
                                </form>
                            </td>
                        </c:if>
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