// Aggiungo la recensione,  si potrebbe anche togliere perchè le notifiche le gestiamo tramite la sessione adesso
$("#recensioni-form").submit(function (e) {
    e.preventDefault();

    //richiesta ajax per aggiungere la recensione
    $.ajax({
        url: $(this).attr("action"),
        method: $(this).attr("method"), //post
        data: $(this).serializeArray()
    }).done((response) => {

        if (response.type === 'error')
            notifier.alert(response.value);
        else if (response.type === 'success') {
            notifier.success(response.value);
            setTimeout(() => window.location.reload(), 2000);
        }
    }).fail(() => {
        notifier.alert("Qualcosa è andato storto!");
    });
});