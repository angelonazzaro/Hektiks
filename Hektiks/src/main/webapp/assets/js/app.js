// Serve per ritardare l'esecuzione di una funzione
function debounce(cb, delay = 1000) {
    let timeout;

    return (...args) => {
        clearTimeout(timeout);
        timeout = setTimeout(() => {
            cb(...args);
        }, delay);
    };
}

const burger = document.getElementById("burger");
const header = document.querySelector("header");
const headerStyle = window.getComputedStyle(header);
const login_registration_section = document.getElementById(
    "login-registration-section"
);

// non restituisce il valore della proprietà, ti permette solo di settarlo
// header.style.position

// Esegui tutto questo blocco di codice solo se l'utente non è loggato
if (login_registration_section !== null) {
    burger.addEventListener("click", () => {
        // Se la classe "active" è presente, rimuovo la classe "active" altrimenti l'aggiungo
        burger.classList.toggle("active");
        document.body.classList.toggle("no-scroll");
        login_registration_section.classList.toggle("active");
        // Fix "overflow" burger
        if (headerStyle.getPropertyValue("position") === "fixed")
            header.style.setProperty("position", "static");
        else
            setTimeout(
                () => header.style.setProperty("position", "fixed"),
                500
            );
    });

    const next_btns = document.querySelectorAll("[data-next-form]");
    // Vai dal form di registration a quello di login e viceversa.
    next_btns.forEach((btn) => {
        btn.addEventListener("click", () => {
            const next_form = document.getElementById(
                `${btn.dataset.nextForm}-form`
            );
            const prev_form = document.getElementById(
                `${btn.dataset.prevForm}-form`
            );

            prev_form.classList.toggle("active");
            prev_form.classList.toggle("hide");

            next_form.classList.toggle("hide");
            next_form.classList.toggle("active");
        });
    });

    const registration_form = document.getElementById("registration-form");

    const password = registration_form.querySelector("input[name=password]");
    const confirm_password = registration_form.querySelector(
        "input[name=confirm-password]"
    );

    // Verifica se le due password corrispondono.
    const match_password_regex = (elem) => {
        if (elem.value.length === 0) return false;

        /*
        * "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}"
        * '(?=' matcha un gruppo dopo l'espressione principale senza includerlo nel risultato.
        * '.' matcha un carattere qualsiasi
        * '*' matcha il token precedente da 0 a infinite volte
        * '\d' corrisponde a una cifra (equivalente a [0-9])
        * '[a-z]' a-z matcha un singolo carattere nell'intervallo compreso tra a (indice 97) e z (indice 122) (case sensitive)
        * '[A-Z]' A-Z matcha un singolo carattere nell'intervallo compreso tra A (indice 65) e Z (indice 90) (case sensitive)
        * '{8,16}' indica che la stringa deve avere almeno 8 caratteri e massimo 16.
        */

        const pattern = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}/;

        if (!(pattern.test(elem.value))) {
            elem.setCustomValidity(
                "La password deve essere lunga almeno 8 caratteri e massimo 16.\n" +
                "\nDeve contenere almeno: \n- 1 numero\n- 1 lettera maiuscola\n- 1 lettera minuscola\n- 1 carattere speciale"
            );
        } else {
            elem.setCustomValidity("");
        }

        elem.reportValidity();
    };

    const match_password_value = (first, second) => {
        if (first.value.length === 0 || second.value.length === 0) return false;

        let matches;

        if ((matches = first.value !== second.value))
            second.setCustomValidity("Le password non corrispondono.");
        else second.setCustomValidity("");

        second.reportValidity();

        return !matches;
    };

    // Ritardano l'esecuzione delle due funzioni immediatamente sopra
    const match_password_regex_debounce = debounce((elem) =>
        match_password_regex(elem), 500
    );

    const match_password_value_debounce = debounce((first, second) =>
        match_password_value(first, second), 500
    );

    password.addEventListener("input", () => {
        match_password_regex_debounce(password);
    });

    confirm_password.addEventListener("input", () => {
        match_password_value_debounce(password, confirm_password);
    });

    // La richiesta ajax viene effettuata per mostrare la notifica, si potrebbe anche togliere dato che
    // adesso la gestiamo in un altro modo
    $(".login-registration-form").on("submit", function (e) {
        e.preventDefault();

        // Se ritorna vero, non inviamo la richiesta
        if ($(this).attr("id") === "registration-form") {
            if (!match_password_value(password, confirm_password)) {
                return;
            }
        }

        const submit_btn = $(this).find("button[type=submit]").first();
        submit_btn.prop("disabled", true); // L'utente non può effettuare più richieste fin quando quella in corso non è finita.

        $.ajax({
            url: $(this).attr("action"),
            method: $(this).attr("method"), //post
            data: $(this).serializeArray(), // processa i dati del form,
        })
            .done((response) => {
                if (response.type === "success") {
                    $(this).trigger("reset"); // pulisco i campi del form

                    if ($(this).attr("id") === "registration-form")
                        notifier.success(response.value);
                    else window.location.reload();
                } else notifier.alert(response.value);
            })
            .fail()
            .always(() => {
                submit_btn.prop("disabled", false);
            });
    });
}

const password_icons = document.querySelectorAll(".password-icon-js");
// Cambia il tipo dell'input da password a testo e viceversa quando si clicca sull'icona dell'occhio
password_icons.forEach((icon) => {
    icon.addEventListener("click", () => {
        let current_type = "password",
            new_type = "text";
        const i = icon.firstChild;

        i.classList.toggle("fa-eye-slash");

        if (!i.classList.contains("fa-eye-slash")) {
            current_type = "text";
            new_type = "password";
        }

        icon.parentElement
            .querySelector(`input[type=${current_type}]`)
            .setAttribute("type", new_type);
    });
});

const profile_pic = document.getElementById("profile-pic");
// Clicchi sull'immagine utente e ti porta alla pagina dell'utente
if (profile_pic !== null)
    profile_pic.addEventListener("click", () => window.location.href = base_url() + "/utente");

const cart_caret = document.querySelector(".cart .caret");

const update_cart_caret = (quantity) => {
    cart_caret.textContent = quantity;
}

// Restituisce l'url base dell'applicazione, come il request.getContextPath()
const base_url = () => {

    const base_url = window.location.origin;

    const pathArray = window.location.pathname.split('/');

    return base_url + "/" + pathArray[1];
}

/* SEARCH BAR */

let current_page = $(".main-content");
let current_page_content = current_page.html();
const search_bars = document.querySelectorAll(".search-bar > input");

const search = debounce((value) => {
    // Rimetto la pagina come stava prima
    if (value.length === 0 || value === "undefined") {
        current_page.html(current_page_content);
        return;
    }

    $.ajax({
        url: base_url() + "/search",
        method: "GET",
        data: {"q": value}
    }).done((response) => {
        // Elimino i possibili duplicati dovuti alla join
        const codici = [];
        const giochi = response.filter(function (item) {
            if (!codici.includes(item.codice_gioco)) {
                codici.push(item.codice_gioco);
                return true;
            }
            return false;
        });

        // Serve per evitare che la search bar venga rimossa nel responsive siccome la rimuoviamo dall'header
        if (window.innerWidth <= 470) {
            current_page = $(".products-container");
            current_page_content = current_page.html();
        } else
            current_page.empty();

        if (giochi.length === 0) {
            current_page.html(`<p class="hs-3" style="color: white; text-align: center">Nessun risultato per la ricerca "${value}"</p>`);
            return;
        }

        // Creo il nuovo contenuto della pagina
        let content = `<div class="products-container"><div class="products-content">`;

        for (const gioco of giochi) {
            content += `<div class="card">
                <div class="card-header">
                    <a href="gioco?codice_gioco=${gioco.codice_gioco}"><img class="card-img" src="${gioco.copertina}" alt="${gioco.titolo} - Copertina"></a>
                   `;

            if (gioco.percentuale_sconto > 0) {
                content += `<div class="discount text">-${parseFloat(gioco.percentuale_sconto).toFixed(2)}%</div>`;
            }

            content += `</div><div class="card-body">
                    <div class="name text">
                        ${gioco.titolo}
                    </div>
                    <div class="price hs-4">`;

            if (gioco.prezzo > 0) {
                if (gioco.percentuale_sconto > 0) {
                    content += `${parseFloat(gioco.prezzo - ((gioco.prezzo * gioco.percentuale_sconto) / 100)).toFixed(2)}€`;
                } else
                    content += `${parseFloat(gioco.prezzo).toFixed(2)}€`;
            } else
                content += `Gratis`;

            content += `</div></div></div>`;
        }

        content += `</div></div>`;
        current_page.html(content);
    })

}, 350);


search_bars.forEach(search_bar =>
    search_bar.addEventListener("input", () => {
        search(search_bar.value);
    })
);

/* MODAL */

// Mostro il modello e rendo il body non scrollable
$("[data-toggle='modal']").click(function () {
    $(`${$(this).attr('data-target')}`).fadeToggle();
    document.body.classList.toggle("no-scroll");
});

// Tolgo il modello e rendo il body scrollable
$(".modal-header > .close").click(function () {
    $(`${$(this).attr('data-target')}`).fadeOut();
    document.body.classList.toggle("no-scroll");
})