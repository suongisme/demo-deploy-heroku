const xml = new XMLHttpRequest();
const btnDecrease = document.querySelector("#decrease");
const btnIncrease = document.querySelector("#increase");
const quantity = document.querySelector("#quantity");

const allBtnAdd = document.querySelectorAll('.action__handle--left');
const allBtnSubtract = document.querySelectorAll('.action__handle--right');
const allAmount = document.querySelectorAll('.action__quantity');

const cart = document.querySelector('.cart__items');
const exit_order = document.querySelector(".order__background");
const cart_items = document.querySelector(".cart__items");

const host = window.location.host
const protocol = window.location.protocol

function addEvent() {
	for (let i = 0; i < allAmount.length; i++) {
		allBtnAdd[i].addEventListener('click', function(event) {
			
			const id = allBtnAdd[i].id;
			allAmount[i].textContent = parseInt(allAmount[i].textContent) + 1;

			xml.open('GET', `${protocol}//${host}/clone-tocotoco/cart/add?id=${id}`, true);
			xml.send();
		})

		allBtnSubtract[i].addEventListener('click', function() {

			const id = allBtnSubtract[i].id;
			allAmount[i].textContent = parseInt(allAmount[i].textContent) - 1;

			if (allAmount[i].textContent == 0) {
				cart.removeChild(allAmount[i].parentElement.parentElement)
			}

			if (cart.childElementCount === 0) {
				cart.innerHTML = `<p class='items__status'>Bạn chưa chọn sản phẩm nào!</p>`
			}

			xml.open('GET', `${protocol}//${host}/clone-tocotoco/cart/subtract?id=${id}`, true);
			xml.send();
		})
	}
}
addEvent();

function increaseQuantity() {
	if (btnIncrease == null) return;
	btnIncrease.addEventListener('click', function() {
		quantity.value = parseInt(quantity.value) + 1;
	});
}
increaseQuantity();

function decreaseQuantity() {
	if (btnDecrease == null) return;
	btnDecrease.addEventListener('click', function() {
		var qu = parseInt(quantity.value);
		if (qu < 2) return;

		quantity.value = qu - 1;
	});
}
decreaseQuantity();

function closeOrder() {
	if (exit_order == null) return;
	exit_order.addEventListener('click', function() {
		var order = document.querySelector(".order");
		order.style.display = "none";
	});
}
closeOrder();

function addScrollCart() {
	if (cart_items == null) return;
	var height = cart_items.clientHeight;
	if (height >= 300) {
		cart_items.style.overflowY = "scroll";
	}
}
addScrollCart();