function createUser() {
        window.location.href = "/admin/create";
        }

// document.addEventListener('DOMContentLoaded', function() {
// const deleteButtons = document.querySelectorAll('.btn-danger');
//
// for (let i = 0; i < deleteButtons.length; i++) {
// deleteButtons[i].addEventListener('click', function(e) {
// e.preventDefault(); // предотвращаем стандартное поведение кнопки
// var userId = this.dataset.id;
// var url = '/admin/users/' + userId;
// var confirmMessage = 'Вы уверены, что хотите удалить пользователя?';
//
// if (confirm(confirmMessage)) {
// $.ajax({
// url: url,
// method: 'DELETE',
// success: function() {
// alert('Пользователь удален успешно');
// },
// error: function(xhr, status, error) {
// alert('Ошибка при удалении пользователя: ' + error);
// }
// });
// }
// });
// }
//});