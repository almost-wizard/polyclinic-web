<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Врачи</title>
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
            <h2>Доступные врачи</h2>
            <p>Врачи, к которым можно записаться на прием</p>
        </section>

        <section>
            <table class="text-center">
                <thead>
                <tr>
                    <th>ФИО</th>
                    <th>Специализации</th>
                    <th colspan="2">Действия</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${doctors}" var="doctor">
                    <tr>
                        <td class="p0"><c:out value="${doctor.getFullName()}"/></td>
                        <td class="p0"><c:out value="${doctor.getSpecializationsString()}"/></td>
                        <td class="p0">
                            <a
                                class="button link w100"
                                href="<c:url value="/controller?command=get_all_appointments&doctorId=${doctor.getId()}"/>"
                            >
                                Записи
                            </a>
                        </td>
                        <td class="p0">
                            <a
                                class="button link w100"
                                href="<c:url value="/controller?command=get_schedules&doctorId=${doctor.getId()}"/>"
                            >
                                График работы
                            </a>
                        </td>
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