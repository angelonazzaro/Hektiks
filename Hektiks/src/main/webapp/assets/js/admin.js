// Inizializzo la dataTable
$(() => {
    $("#basic-table").DataTable({
        "language": {
            "url": "//cdn.datatables.net/plug-ins/1.12.1/i18n/it-IT.json"
        }
    });

    $("#select-generi").select2({
        placeholder: "Seleziona uno o più generi",
    });
})
