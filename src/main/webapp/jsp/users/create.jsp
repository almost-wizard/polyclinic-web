<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Добавить пользователя</title>
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
            <h2>Добавить пользователя</h2>
        </section>
        <section>
            <p class="red-text"><c:out value="${errorMessage}"/></p>
        </section>

        <hr/>

        <section>
            <form name="createUserForm" method="POST" action="controller">
                <input type="hidden" name="command" value="create_user"/>
                <h3>Данные</h3>

                <div class="variants m5">
                    <div class="m5">
                        <label for="f">Фамилия</label>
                        <input id="f" class="w100 m0" type="text" placeholder="Фамилия" name="surname"/>
                    </div>
                    <div class="m5">
                        <label for="i">Имя</label>
                        <input id="i" class="w100 m0" type="text" placeholder="Имя" name="name"/>
                    </div>
                    <div class="m5">
                        <label for="o">Отчество</label>
                        <input id="o" class="w100 m0" type="text" placeholder="Отчество" name="patronymic"/>
                    </div>
                </div>

                <div class="variants m5">
                    <div class="m5 w100">
                        <label for="l">Логин</label>
                        <input id="l" class="w100 m0" type="text" placeholder="Логин" name="login"/>
                    </div>
                    <div class="m5 w100">
                        <label for="p">Пароль</label>
                        <input id="p" class="w100 m0" type="password" placeholder="Пароль" name="password"/>
                    </div>
                </div>

                <div class="variants m5">
                    <div class="m5">
                        <label for="roles">Роль</label>
                        <select id="roles" class="w100 py5 m0" name="role">
                            <c:forEach items="${roles}" var="role">
                                <option
                                        data-role-id="${role.getDatabaseRoleId()}"
                                        value="${role.getDatabaseRoleId()}"
                                >
                                        <c:out value="${role.getName()}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div id="specializations-container" class="m5">
                        <label for="specialization">Специализация</label>
                        <select id="specialization" class="w100 py5 m0" name="specialization">
                            <c:forEach items="${specializations}" var="specialization">
                                <option
                                        value="${specialization.getId()}"
                                >
                                    <c:out value="${specialization.getName()}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="variants m5">
                    <div class="m5">
                        <label for="z">Заблокирован</label>
                        <select id="z" class="w100 py5 m0" name="isBlocked">
                            <option value="false">Нет</option>
                            <option value="true">Да</option>
                        </select>
                    </div>
                </div>

                <section class="flex-center">
                    <div class="variants">
                        <button class="button green" type="submit">Добавить</button>
                        <a
                            class="button red"
                            href="<c:url value="/controller?command=get_all_users"/>"
                        >
                            Вернуться к пользователям
                        </a>
                    </div>
                </section>
            </form>
        </section>
    </div>
</div>
<jsp:include page="/jsp/layout/footer.jsp"/>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const roleSelect = document.getElementById('roles');
        const specializationsContainer = document.getElementById('specializations-container');

        function toggleSpecializationVisibility() {
            const selectedOption = roleSelect.options[roleSelect.selectedIndex];
            const selectedRoleName = selectedOption.getAttribute('data-role-id');

            if (selectedRoleName == ${doctorRoleId}) {
                specializationsContainer.style.display = 'block';
            } else {
                specializationsContainer.style.display = 'none';
            }
        }

        toggleSpecializationVisibility();
        roleSelect.addEventListener('change', toggleSpecializationVisibility);
    });
</script>
</body>
</html>