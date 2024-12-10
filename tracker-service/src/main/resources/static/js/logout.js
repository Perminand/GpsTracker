document.getElementById('logout-button').addEventListener('click', function () {
    // Получаем все куки
    const cookies = document.cookie.split(';');

    // Удаляем все куки
    for (let i = 0; i < cookies.length; i++) {
        document.cookie = cookies[i].trim().replace(/^ +/gm, '').split('=')[0] + '=;expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    }

    // Перенаправляем пользователя на страницу авторизации
    window.location.href = '/';
});