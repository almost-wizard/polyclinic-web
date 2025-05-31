<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Отменить запись</title>
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
            <h2>Вы действительно хотите отменить запись?</h2>
            <p>Запись к доктору "<c:out value="${doctor.getFullName()}"/>" на <c:out value="${appointment.getDatetimeString()}"/></p>
        </section>

        <section>
            <p class="red-text"><c:out value="${errorMessage}"/></p>
        </section>

        <section class="variants">
            <a class="button red" href="<c:url value="/controller?command=delete_appointment&appointmentId=${appointment.getId()}"/>">Да, отменить</a>
            <a class="button green" href="<c:url value="/controller?command=get_all_appointments&doctorId=${appointment.getDoctor().getId()}"/>">Нет, вернуться назад</a>
        </section>

    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>
