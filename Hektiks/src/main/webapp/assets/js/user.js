// Riscatto la giftcard, si potrebbe anche togliere perchè le notifiche le gestiamo tramite la sessione adesso
$("#giftcard-form").submit(function (e) {
    e.preventDefault();

    $.ajax({
        url: $(this).attr('action'),
        method: $(this).attr('method'), //post
        data: $(this).serializeArray()
    }).done((response) => {
        response = JSON.parse(response);

        //notifico se l'operazione è andata a buon fine o no

        if (response.type === 'error') notifier.alert(response.value);
        else if (response.type === 'success') {
            notifier.success(response.value);
            setTimeout(() => window.location.reload(), 2000);
        }

    }).fail(() => notifier.alert("Qualcosa è andato storto!"));

})