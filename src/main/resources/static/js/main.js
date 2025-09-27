// src/main/resources/static/js/main.js

function confirmDelete(event, formId) {
    // Impede que o link '#' faça a página saltar
    event.preventDefault();

    // Exibe uma caixa de confirmação
    if (confirm('Tem a certeza que deseja excluir este item?')) {
        // Se o utilizador clicar "OK", envia o formulário correspondente
        document.getElementById(formId).submit();
    }
}