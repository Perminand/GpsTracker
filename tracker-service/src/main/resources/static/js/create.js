  document.addEventListener('DOMContentLoaded', function() {
  document.querySelector('form').addEventListener('submit', function(e) {
   e.preventDefault(); // Остановить стандартное поведение формы


   // Получить данные из формы
   const username = document.querySelector('#username').value;
   const realPassword = document.querySelector('#realPassword').value;
   const confirmPassword = document.querySelector('#confirmPassword').value;
   const email = document.querySelector('#email').value;

   if (realPassword !== confirmPassword) {
   alert('Пароли не совпадают. Попробуйте ещё раз.');
   return;
   }

   // Преобразовать данные в JSON
   const data = {
   username,
   realPassword,
   email,
   };


   // Отправить JSON запрос
   createUser(data).then(() => {
   // Очистить поля формы при успешной отправке
   document.querySelector('#username').value = '';
   document.querySelector('#realPassword').value = '';
   document.querySelector('#email').value = '';
   document.querySelector('#confirmPassword').value = '';

   // Показать сообщение пользователю с помощью alert
   alert('Пользователь сохранен');

   // Также логировать сообщение в консоль
   console.log('Поля формы очищены после успешной отправки');
   });
   });


  function createUser(data) {
    return fetch(`/admin/users/create`, {
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

document.getElementById('user-edit-form').addEventListener('submit', (event) => {
            event.preventDefault();
            document.querySelector('.hidden').click();
        });

        function submitForm() {
            document.querySelector('.hidden').click();
        }