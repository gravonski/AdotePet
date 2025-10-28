document.addEventListener('DOMContentLoaded', function() {
    console.log("AdotePet JS: O script principal foi carregado com sucesso.");
    const deleteModalElement = document.getElementById('deleteModal');
    const deleteModal = new bootstrap.Modal(deleteModalElement);
    const confirmDeleteButton = document.getElementById('confirmDeleteButton');
    const deleteButtons = document.querySelectorAll('.btn-delete');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();
            const deleteUrl = button.getAttribute('href');
            confirmDeleteButton.setAttribute('href', deleteUrl);
            deleteModal.show();
        });
    });

});