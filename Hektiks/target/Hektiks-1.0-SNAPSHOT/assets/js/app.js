const eyeIcons = document.querySelectorAll("i.far.fa-eye");

eyeIcons.forEach((eyeIcon) => {
	eyeIcon.addEventListener("click", () => {

		let addType = "text", removeType = "password";
		let addIconClass = "fa-eye-slash", removeIconClass = "fa-eye";
		let tmpClass, tmpType;

		if (!eyeIcon.classList.contains("fa-eye")) {
			tmpType = addType;
			addType = removeType;
			removeType = tmpType;

			tmpClass = addIconClass;
			addIconClass = removeIconClass;
			removeIconClass = tmpClass;
		}

		eyeIcon.classList.remove(removeIconClass);
		eyeIcon.classList.add(addIconClass);
		eyeIcon.parentElement.querySelector(`input[type=${removeType}]`).setAttribute("type", addType);
	})
});
