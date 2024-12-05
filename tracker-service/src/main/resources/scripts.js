// Получаем форму входа
const loginForm = document.forms['loginForm'];

// Обработчик отправки формы
loginForm.addEventListener('submit', (event) => {
  event.preventDefault(); // Предотвращаем стандартное поведение формы

  // Получаем данные из полей формы
  const username = loginForm['username'].value;
  const password = loginForm['password'].value;

  // Здесь можно отправить данные на сервер для аутентификации
  // Пример отправки данных на сервер с использованием fetch
  fetch('/api/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, password }),
  })
    .then(response => response.json())
    .then(data => {
      // Обработка ответа от сервера
      if (data.success) {
        // Аутентификация прошла успешно, можно установить токен
        localStorage.setItem('jwtToken', data.token);
        window.location.href = '/profile'; // Перенаправление на профиль пользователя
      } else {
        alert('Неправильные учетные данные. Попробуйте еще раз.');
      }
    })
    .catch(error => {
      console.error('Ошибка при отправке данных на сервер:', error);
    });
});