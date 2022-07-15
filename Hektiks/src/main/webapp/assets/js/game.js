/* GIOCHI */
const add_to_cart_btn = document.getElementById("add-to-cart-btn");
const p_error = document.getElementById("p-error");
const game_card = p_error.parentElement;
const subinfos = game_card.querySelector(".subinfos > p");

add_to_cart_btn.addEventListener("click", () => {

    // Se la quantità disponibile è 0, non posso più aggiungere al carrello
    if (add_to_cart_btn.dataset.quantity === "0") {
        subinfos.classList.remove("tick");
        subinfos.classList.add("cross");
        subinfos.innerText = "Non disponibile";
        p_error.innerText = game_card.dataset.title + " non è più disponibile.";
        return;
    }

    // Aggiungo l'elemento al carrello e aggiorno il conteggio
    $.ajax({
        url: base_url() + "/carrello",
        method: "POST",
        data: {
            "codice_gioco": game_card.dataset.code,
            "quantita": 1,
            "action": "add"
        }
    }).done((response) => {
        update_cart_caret(response.value);
        add_to_cart_btn.dataset.quantity =
            parseInt(add_to_cart_btn.dataset.quantity) - 1;

        if (subinfos.classList.contains("cross")) {
            subinfos.classList.remove("cross");
            subinfos.classList.add("tick");
            subinfos.innerText = "Disponibile";
            p_error.innerText = "";
        }
    });
});