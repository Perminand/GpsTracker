// Функция для создания таблицы из данных JSON
function createTableFromData(data) {
    const table = document.getElementById('item-table');
    const tbody = table.querySelector('tbody');

    // Сортируем данные по дате в порядке убывания перед созданием таблицы
    data.sort((a, b) => {
        const dateA = new Date(a.created);
        const dateB = new Date(b.created);
        return dateB - dateA;
    });

    data.forEach(item => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${item.created}</td>
            <td>${item.message}</td>
        `;
        tbody.appendChild(tr);
    });

    // Добавляем сортировку по дате
    table.addEventListener('click', (event) => {
        if (event.target.tagName === 'TH') {
            const table = event.target.closest('table');
            const rows = table.querySelectorAll('tbody tr');

            // Добавляем сортировку по клику на заголовок
            const sortedRows = rows.sort((a, b) => {
                const dateA = new Date(a.querySelector('td').textContent);
                const dateB = new Date(b.querySelector('td').textContent);
                return dateB - dateA; // Сортировка по убыванию даты
            });

            table.tBodies0.replaceChildren(...sortedRows);
        }
    });
}

// Функция для загрузки данных JSON с помощью GET запроса
async function loadData() {
    const response = await fetch('/api/v1/apps/items');
    if (!response.ok) {
        throw new Error('Ошибка при загрузке данных');
    }
    const data = await response.json();
    createTableFromData(data);
    document.getElementById('loading-progress').classList.remove('hidden');
}

// Функция для фильтрации строк таблицы
function filterTable(event) {
    const filterInput = document.getElementById('message-filter');
    const filterValue = filterInput.value.toLowerCase();
    const table = document.getElementById('item-table');
    const tbody = table.querySelector('tbody');

    Array.from(tbody.querySelectorAll('tr')).forEach(row => {
        const messageCell = row.querySelector('td:nth-child(2)');
        const messageText = messageCell.textContent.toLowerCase();

        if (messageText.indexOf(filterValue) === -1) {
            row.style.display = 'none';
        } else {
            row.style.display = 'table-row';
        }
    });
}

// Инициализация загрузки данных и привязки события ввода
loadData();
document.getElementById('message-filter').addEventListener('input', filterTable);

function redirectToAdminPage() {
    window.location.href = '/admin/users-list';
}