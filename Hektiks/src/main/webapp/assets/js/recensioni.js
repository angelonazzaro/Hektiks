$("#recensioni-form").submit(function (e) {
    e.preventDefault();

    $.ajax({
        url: $(this).attr("action"),
        method: $(this).attr("method"),
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