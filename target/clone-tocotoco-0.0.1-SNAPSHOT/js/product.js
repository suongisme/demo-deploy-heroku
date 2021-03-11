document.getElementsByTagName('body')[0].onscroll = function() {
	handleMenu();
};

function handleMenu () {
    var status = document.querySelector(".header__status-bar");

    var x2 = document.querySelector('.header').getBoundingClientRect().bottom;
    if (x2 <= 80) {
        status.style.background = "#282828";
    } else {
        status.style.background = "";
    }
}

function handleBody() {
    var product__menu = document.querySelector(".products__menu");

	if (product__menu === 'null') return;

	var header = document.querySelector('.header').getBoundingClientRect();
	var header_bottom = header.bottom;

	if (header_bottom <= 80) {
        product__menu.style.position = "fixed";
        product__menu.style.top = "80px";
		product__menu.style.left = "0px";
    } else {
        product__menu.style.position = "absolute";
        product__menu.style.top = "0px";
    }
}