
document.addEventListener('DOMContentLoaded', function() {
    console.log("AdotePet JS: O script principal foi carregado com sucesso.");
    const deleteButtons = document.querySelectorAll('.btn-delete');

    deleteButtons.forEach(button => {
        button.addEventListener('click', function(event) {

            const confirmed = confirm('Você tem certeza que deseja excluir este item?');

            if (!confirmed) {
                event.preventDefault();
            }
        });
    });

});