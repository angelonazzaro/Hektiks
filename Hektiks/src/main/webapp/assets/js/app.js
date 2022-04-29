const burger = document.getElementById("burger");
const login_reg_section = document.getElementById("login-registration");

burger.addEventListener("click", () => {
    burger.classList.toggle("active")
    if (login_reg_section) {
        login_reg_section.classList.toggle("active");
        document.body.classList.toggle("stop-scrolling");
    }
});

const eye_icons = document.querySelectorAll("i.fas.fa-eye");

eye_icons.forEach(eye => {
    eye.addEventListener("click", () => {
        eye.classList.toggle("fa-eye-slash");

        let type = "password", change_to = "text";
        if (!eye.classList.contains("fa-eye-slash")) {
            type = "text";
            change_to = "password";
        }

        eye.parentElement.parentElement.querySelector(`input[type=${type}]`).setAttribute("type", change_to);
    })
})

const go_to_login = document.querySelector(".login-registration-form__btn[data-form=login]");
const go_to_reg = document.querySelector(".login-registration-form__btn[data-form=registration]");

if (go_to_login && go_to_reg) {

    const reg_form = go_to_login.parentElement;
    const login_form = go_to_reg.parentElement;

    go_to_login.addEventListener("click", () => {
        reg_form.classList.remove("show");
        reg_form.classList.add("hide");

        login_form.classList.remove("hide");
        login_form.classList.add("show");
    });

    go_to_reg.addEventListener("click", () => {
        login_form.classList.remove("show");
        login_form.classList.add("hide");

        reg_form.classList.remove("hide");
        reg_form.classList.add("show");
    });

    const reg_password = reg_form.querySelector("input[name=password]");
    const confirm_password = reg_form.querySelector("input[name=cnf-pwd]");

    function matchPassword(pwd, cnf) {
        if (pwd.value !== cnf.value) cnf.setCustomValidity("Le due password non corrispondono");
        else cnf.setCustomValidity("");

        cnf.reportValidity();
    }

    confirm_password.addEventListener("input", () => matchPassword(reg_password, confirm_password));

    reg_form.addEventListener("submit", function (e) {
        e.preventDefault();
        matchPassword(reg_password, confirm_password);

        const fd = new FormData(reg_form);
        const data = {};

        for (const pair of fd.entries())
            data[pair[0]] = pair[1];

        $.ajax({
            type: "POST",
            data: data,
            url: this.getAttribute("action")
        }).done((response) => {
            console.log(response);
        })
    })
}