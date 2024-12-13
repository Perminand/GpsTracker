// Функция для создания таблицы из данных JSON
function createTableFromData(data) {
    const table = document.getElementById('item-table');
    const tbody = table.querySelector('tbody');

    data.forEach(item => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${item.id}</td>
            <td>${item.created}</td>
            <td>${item.message}</td>
          `;
        tbody.appendChild(tr);
    });
}

// Функция для загрузки данных JSON с помощью GET запроса
async function loadData() {
    const response = await fetch('http://62.109.21.174:8081/api/v1/apps/items');
    if (!response.ok) {
        throw new Error('Ошибка при загрузке данных');
    }
    const data = await response.json();
    createTableFromData(data);
}

// Функция для фильтрации строк таблицы
function filterTable(event) {
    const filterInput = document.getElementById('message-filter');
    const filterValue = filterInput.value.toLowerCase();
    const table = document.getElementById('item-table');
    const tbody = table.querySelector('tbody');

    Array.from(tbody.querySelectorAll('tr')).forEach(row => {
        const messageCell = row.querySelector('td:nth-child(3)');
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