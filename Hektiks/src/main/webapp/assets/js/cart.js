const selects = document.querySelectorAll("select[name='quantita']");
const cart_total = document.querySelector("#cart-total");
const remove_cart_btns = document.querySelectorAll(".remove-cart-btn");

selects.forEach(select => {
    select.addEventListener("change", function () {
        const parent = select.parentElement;
        $.ajax({
            url: base_url() + "/carrello",
            method: "POST",
            data: {
                "codice_gioco": parent.dataset.code,
                "quantita": select.value,
                "action": "update"
            }
        }).done((response) => {
            update_cart_caret(response.value);

            let cart_total_price = parseFloat(cart_total.textContent);
            const game_price = parseFloat(parent.dataset.price).toFixed(2);
            const quantity = parseInt(parent.dataset.quantity);

            cart_total_price += game_price * (select.value - quantity);
            cart_total.textContent = cart_total_price.toFixed(2);

            parent.dataset.quantity = select.value;
        }).fail(() => {
            notifier.alert("Qualcosa è andato storto");
        });
    });
});

remove_cart_btns.forEach(btn => {
    btn.addEventListener("click", function () {
        const parent = btn.parentElement;

        $.ajax({
            url: base_url() + "/carrello",
            method: "POST",
            data: {
                "codice_gioco": parent.dataset.code,
                "quantita": 0,
                "action": "remove"
            }
        }).done((response) => {

            update_cart_caret(response.value);

            const quantity = parseInt(parent.dataset.quantity);
            const game_price = parseFloat(parent.dataset.price);

            cart_total.textContent = (parseFloat(cart_total.textContent) - (game_price * quantity)).toFixed(2).replace(".", ",");
            $(`.${parent.dataset.code}`).remove();
        }).fail(() => {
            notifier.alert("Qualcosa è andato storto");
        });
    });
});