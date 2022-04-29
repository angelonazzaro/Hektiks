const burger = document.getElementById("burger");
const login_reg_section = document.getElementById("login-registration");

burger.addEventListener("click", () => {
    burger.classList.toggle("active")
    if (login_reg_section) {
        login_reg_section.classList.toggle("active");
        document.body.classList.toggle("stop-scrolling");
    }
});

const eyeIcons = document.querySelectorAll("i.fas.fa-eye");

eyeIcons.forEach(eye => {
    eye.addEventListener("click", () => {
        eye.classList.toggle("fa-eye-slash");

        let type = "password", changeTo = "text";
        if (eye.classList.contains("fa-eye-slash")) {
            type = "text";
            changeTo = "password";
        }

        eye.parentElement.querySelector(`input[type=${type}]`).setAttribute("type", changeTo);
    })
})

const go_to_login = document.querySelector(".login-registration-form__btn[data-form=login]");
const go_to_reg = document.querySelector(".login-registration-form__btn[data-form=registration]");

if (go_to_login && go_to_reg) {

    const regForm = go_to_login.parentElement;
    const loginForm = go_to_reg.parentElement;

    go_to_login.addEventListener("click", () => {
        regForm.classList.remove("show");
        regForm.classList.add("hide");

        loginForm.classList.remove("hide");
        loginForm.classList.add("show");
    });

    go_to_reg.addEventListener("click", () => {
        loginForm.classList.remove("show");
        loginForm.classList.add("hide");

        regForm.classList.remove("hide");
        regForm.classList.add("show");
    });
}