<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Обновить историю болезни</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/main.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/content-simple.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="/styles/ui.css"/>">
</head>
<body>
<jsp:include page="/jsp/layout/header.jsp"/>

<div class="main">
    <jsp:include page="/jsp/layout/menu.jsp"/>
    <div class="content-area">
        <form name="createMedicalCardHistory" method="POST" action="controller">
            <input type="hidden" name="command" value="create_medical_card_history"/>
            <input type="hidden" name="appointmentId" value="${appointment.getId()}"/>
            <section>
                <h2>Новая запись в истории болезней</h2>
                <h3>От <c:out value="${appointment.getDatetimeString()}"/></h3>
            </section>

            <section>
                <label for="d">Диагноз</label>
                <input id="d" class="w100 m0" type="text" placeholder="Диагноз" name="diagnosis"/>
            </section>

            <section>
                <label for="t">Лечение</label>
                <textarea id="t" class="w100 m0" placeholder="Лечение" rows="5" name="therapy"></textarea>
            </section>

            <section class="flex-center">
                <button class="button green" type="submit">Добавить</button>
                <p class="red-text"><c:out value="${errorMessage}"/></p>
            </section>
        </form>
    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>
