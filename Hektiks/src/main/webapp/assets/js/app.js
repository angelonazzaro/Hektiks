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

if (login_registration_section !== null) {
	burger.addEventListener("click", () => {
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

	// Verifica se le due password corrispondono. L'esecuzione della funzione è ritardata tramite il debounce
	const match_password_regex = (elem) => {
		if (elem.value.length === 0) return;

		const pattern =
			/^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;

		if (!pattern.test(elem.value)) {
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
		if (first.value.length === 0 || second.value.length === 0) return;

		if (first.value !== second.value)
			second.setCustomValidity("Le password non corrispondono.");
		else second.setCustomValidity("");

		second.reportValidity();
	};

	const match_password_regex_debounce = debounce((elem) =>
		match_password_regex(elem)
	);
	const match_password_value_debounce = debounce((first, second) =>
		match_password_value(first, second)
	);

	password.addEventListener("input", () => {
		match_password_regex_debounce(password);
		match_password_value_debounce(password, confirm_password);
	});

	confirm_password.addEventListener("input", () => {
		match_password_regex_debounce(confirm_password);
		match_password_value_debounce(password, confirm_password);
	});

	$(".login-registration-form").on("submit", function (e) {
		e.preventDefault();

		const submit_btn = $(this).find("button[type=submit]").first();
		submit_btn.prop("disabled", true); // L'utente non può effettuare più richieste fin quando quella in corso non è finita.

		$.ajax({
			url: $(this).attr("action"),
			method: $(this).attr("method"),
			data: $(this).serializeArray(),
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

const cart_caret = document.querySelector("#cart .caret");

function update_cart_caret(quantity) {
	console.log("aaaa");
	console.log(quantity)
	cart_caret.textContent = quantity;
}

function base_url() {

	const base_url = window.location.origin;

	const pathArray = window.location.pathname.split( '/' );

	return base_url + "/" + pathArray[1];
}