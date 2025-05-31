<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="menu">
    <ul>
        <li><a class="button link" href="<c:url value="/controller?command=get_profile"/>">Профиль</a></li>
        <c:if test="${sessionScope.get('user').getRole().isSystemObservable()}">
            <li><a class="button link" href="<c:url value="/controller?command=get_all_users"/>">Пользователи</a></li>
        </c:if>
        <c:if test="${sessionScope.get('user').getRole().isEmployee()}">
            <li><a class="button link" href="<c:url value="/controller?command=get_all_doctors"/>">Врачи</a></li>
            <li><a class="button link" href="<c:url value="/controller?command=get_all_vacation_applications"/>">Отпуска</a></li>
        </c:if>
        <li><a class="button link" href="<c:url value="/controller?command=get_logout"/>">Выйти</a></li>
    </ul>
</div>
