window.addEventListener('DOMContentLoaded', function () {
    const loginForm = document.getElementById("form");

    // Функция для обработки отправки формы
    const handleLoginSubmit = (event) => {
        event.preventDefault(); // Предотвращаем стандартное поведение формы

        // Получаем данные из формы
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        // Ваш AJAX запрос для отправки данных на сервер
        fetch('http://62.109.21.174:8080/api/v1/apps/auth/login', {
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
                window.location.href = '/';
            } else {
                alert('Неверные логин или пароль');
            }
        })
        .catch(error => {
            console.error('Произошла ошибка:', error);
        });
    };

    // Привязываем обработчик события к форме
    loginForm.addEventListener('submit', handleLoginSubmit);
});