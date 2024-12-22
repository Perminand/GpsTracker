document.getElementById('back').addEventListener('click', function() {
  window.location.href = document.referrer;
});

function redirectToAdminPage() {
    window.location.href = '/admin/users-list';
}