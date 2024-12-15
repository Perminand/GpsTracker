  document.addEventListener('DOMContentLoaded', function() {
  document.querySelector('form').addEventListener('submit', function(e) {
    e.preventDefault(); // Остановить стандартное поведение формы

    // Получить данные из формы
    const username = document.querySelector('#username').value;
    const email = document.querySelector('#email').value;
    const tokenAccess = document.querySelector('#tokenAccess').value;

    // Получить ID пользователя из данных формы (предполагая, что ID передается как параметр)
    const id = document.querySelector('#id').value;

    // Преобразовать данные в JSON
    const data = {
      username,
      email,
      tokenAccess
    };

    // Отправить JSON запрос
    updateUser(id, data);
  });

  function updateUser(id, data) {
    return fetch(`/admin/update-user/${id}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Ошибка при отправке данных');
      }
      return response.json();
    })
    .then(data => {
      console.log('Данные успешно обновлены');
    })
    .catch(error => {
      console.error('Произошла ошибка:', error);
    });
  }
});
