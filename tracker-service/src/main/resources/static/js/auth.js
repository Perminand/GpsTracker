window.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById("form");

    // Функция для обработки отправки формы
    const handleLoginSubmit = (event) => {
        event.preventDefault(); // Предотвращаем стандартное поведение формы

        // Получаем данные из формы
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // Ваш AJAX запрос для отправки данных на сервер
        fetch('/api/v1/apps/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка сервера');
            }
            return response.json();
        })
        .then(data => {
            // Если сервер вернул данные, сохраняем их в куки
            if (data.token) {
                document.cookie = `token=${data.token}; Path=/`;
                window.location.href = '/company';
            } else {
                alert('Неверные логин или пароль');
            }
        })
        .catch(error => {
            alert('Неверные логин или пароль');
            console.error('Произошла ошибка:', error);
            document.getElementById('username').value = '';
            document.getElementById('password').value = '';
        });
    };

    // Привязываем обработчик события к форме
    loginForm.addEventListener('submit', handleLoginSubmit);
});