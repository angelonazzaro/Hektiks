const selects = document.querySelectorAll("select[name='quantita']");
const remove_cart_btns = document.querySelectorAll(".remove-cart-btn");

selects.forEach(select => {
    select.addEventListener("change", function () {
        $.ajax({
            url: base_url() + "/carrello",
            method: "POST",
            data: {
                "codice_gioco": select.parentElement.dataset.code,
                "quantita": select.value,
                "action": "update"
            }
        }).done((response) => {

        });
    });
});

remove_cart_btns.forEach(btn => {
    btn.addEventListener("click", function () {
        $.ajax({
            url: base_url() + "/carrello",
            method: "POST",
            data: {
                "codice_gioco": btn.parentElement.dataset.code,
                "quantita": 0,
                "action": "remove"
            }
        }).done((response) => {

        });
    });
});