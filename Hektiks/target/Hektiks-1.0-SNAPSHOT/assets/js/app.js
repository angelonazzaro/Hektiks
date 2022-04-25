const burger = document.querySelector(".burger");
const searchBar = document.querySelector(".search-bar");
const clearIcon = document.querySelector(".clear-icon");

burger.addEventListener("click", function () {
	burger.classList.toggle("menu-active");
});

clearIcon.addEventListener("click", function () {
    searchBar.value = "";
    searchBar.dispatchEvent(new Event("input"));
})

searchBar.addEventListener("input", function () {
	if (searchBar.value.length === 0) {
		clearIcon.style.opacity = 0;
		clearIcon.style.pointerEvents = "none";
	} else {
		clearIcon.style.opacity = 1;
		clearIcon.style.pointerEvents = "all";
	}
});