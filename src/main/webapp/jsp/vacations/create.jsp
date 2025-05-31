<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Написать завявление</title>
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
            <div class="variants flex-column">
                <h2>Написать заявление на отпуск или отгул</h2>
                <p class="red-text"><c:out value="${errorMessage}"/></p>
            </div>
        </section>

        <form name="createVacationForm" method="POST" action="controller">
            <input type="hidden" name="command" value="create_vacation" />

            <section class="variants-align-start flex-column">
                <div class="w100 flex-between">
                    <label for="e">Выберите дату окончания</label>
                    <input class="ml15" id="e" type="date" name="startDate">
                </div>
                <div class="w100 flex-between">
                    <label for="s">Выберите дату начала</label>
                    <input class="ml15" id="s" type="date" name="endDate">
                </div>
            </section>

            <section class="variants">
                <button class="button green" type="submit">Отправить</button>
                <a class="button red" href="<c:url value="/controller?command=get_all_vacation_applications"/>">Отмена</a>
            </section>
        </form>
    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
</body>
</html>
