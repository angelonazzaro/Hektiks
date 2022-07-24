const selects = document.querySelectorAll("select[name='quantita']");
const cart_total = document.querySelector("#cart-total");
const remove_cart_btns = document.querySelectorAll(".remove-cart-btn");

//per ogni elemento della select aggiungo un evento

selects.forEach(select => {
    select.addEventListener("change", function () {
        const parent = select.parentElement;
        $.ajax({
            url: base_url() + "/carrello",
            method: "POST",
            data: {
                "codice_gioco": parent.dataset.code, //richiesta ajax per aggiornare il carrello
                "quantita": select.value,
                "action": "update"
            }
        }).done((response) => {
            update_cart_caret(response.value);

            //recupero il prezzo del singolo gioco, prezzo totale e quantità

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

//aggiungo un evento per il bottone di rimozione

remove_cart_btns.forEach(btn => {
    btn.addEventListener("click", function () {
        const parent = btn.parentElement;

        $.ajax({
            url: base_url() + "/carrello",
            method: "POST",
            data: {
                "codice_gioco": parent.dataset.code, //richiesta ajax per aggiornare il carrello
                "quantita": 0,
                "action": "remove"
            }
        }).done((response) => {

            update_cart_caret(response.value);

            //recupero quantità e prezzo

            const quantity = parseInt(parent.dataset.quantity);
            const game_price = parseFloat(parent.dataset.price);

            //calcolo il totale

            cart_total.textContent = (parseFloat(cart_total.textContent) - (game_price * quantity)).toFixed(2).replace(".", ",");
            $(`.${parent.dataset.code}`).remove();

            const cart_items = $(".cart-item");
            const main_content = $('.main-content');

            // se rimuovo tutti gli elementi dal carrello, modifico la pagina e mostro
            // "Il tuo carrello è vuoto."

            if (cart_items.length === 0) {

                main_content.empty();
                main_content.append(`<div style="width: 100%; text-align: center; color: white;">
                                                <h1 class="hs-3">Il tuo carrello è vuoto.</h1>
                                           </div>`);
            }

        }).fail(() => {
            notifier.alert("Qualcosa è andato storto");
        });
    });
});