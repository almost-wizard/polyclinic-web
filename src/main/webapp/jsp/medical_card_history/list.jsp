<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Медицинская карта</title>
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
            <h2>Медицинская карта пациента "<c:out value="${patient.getFullName()}"/>"</h2>
        </section>

        <section>
            <table class="text-center">
                <thead>
                <tr>
                    <th>Дата и время</th>
                    <th>Доктор</th>
                    <th>Диагноз</th>
                    <th>Лечение</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${medicalCardHistoryList}" var="medicalCardHistory">
                    <tr>
                        <td class="p0"><c:out value="${medicalCardHistory.getAppointment().getDatetimeString()}"/></td>
                        <td class="p0"><c:out value="${medicalCardHistory.getAppointment().getDoctor().getFullName()}"/></td>
                        <td class="p0"><c:out value="${medicalCardHistory.getDiagnosis()}"/></td>
                        <td class="p0"><c:out value="${medicalCardHistory.getTherapy()}"/></td>
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
