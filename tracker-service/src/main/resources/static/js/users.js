 // Функция для добавления данных пользователей в таблицу
        function addUsersToTable(users) {
            for (const user of users) {
                const tr = document.createElement('tr');
                const idCell = document.createElement('td');
                const usernameCell = document.createElement('td');
                const emailCell = document.createElement('td');
                const roleCell = document.createElement('td');
                const actionsCell = document.createElement('td');

                idCell.textContent = user.id;
                usernameCell.textContent = user.username;
                emailCell.textContent = user.email;
                roleCell.textContent = user.roles.join(', ');
                actionsCell.textContent = 'Изменить';

                tr.appendChild(idCell);
                tr.appendChild(usernameCell);
                tr.appendChild(emailCell);
                tr.appendChild(roleCell);
                tr.appendChild(actionsCell);

                document.querySelector('tbody').appendChild(tr);
            }
        }

        // Функция для фильтрации данных
        function filterUsers(searchQuery) {
            const filter = new RegExp(searchQuery, 'i');
            const rows = document.querySelectorAll('tbody tr');

            for (let i = rows.length - 1; i >= 0; i--) {
                const row = rows[i];
                const username = row.querySelector('td:nth-child(2)').textContent.trim();

                if (!filter.test(username)) {
                    rows[i].style.display = 'none';
                } else {
                    rows[i].style.display = '';
                }
            }
        }

        // Пример использования фильтров
        const searchInputUsername = document.getElementById('username-filter');
        const searchInputEmail = document.getElementById('email-filter');

        searchInputUsername.addEventListener('input', () => {
            filterUsers(searchInputUsername.value);
        });

        searchInputEmail.addEventListener('input', () => {
            filterUsers(searchInputEmail.value);
        });

        // Загрузка начальных данных
        fetch('http://62.109.21.174:8081/api/v1/apps/admin/users')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Ошибка: ${response.statusText}`);
                }
                return response.json();
            })
            .then(users => {
                addUsersToTable(users);
            });