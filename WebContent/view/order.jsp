<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, charset=UTF-8">
<title>Document</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Baloo+2:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/order.css">
</head>
<body>
	<div class="header">
		<img class="header__logo" alt="Logo" src="http://tocotocotea.com/wp-content/themes/tocotocotea/assets/images/logo.png">
		<div class="header__search">
			<img class="search__icon" src="../image/search.png" alt="search-icon"> 
			<input class="search__text" type="text" placeholder="Tìm kiếm sản phẩm">
		</div>
		<a class="header__btn" href="#">Đăng Nhập</a>
	</div>

	<div class="body">
		<div class="body__menu">
			<h3 class="menu__heading">Danh Mục</h3>
			<ul class="menu__list-categories">
				<c:forEach var="category" items="${categories}">
					<li class="list-categories__category">
						<a class="category__name" href="#">${category.getName()}</a> 
						<a class="category__amount" href="#">1</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="body__products">
			<h3 class="products__heading">Sản Phẩm</h3>
			<div class="products__list-products">
				<c:forEach var="product" items="${products}">
					<div class="list-products__product">
						<img class="product__img" src="${product.getImage()}" alt="${product.getName()}">
						<div>
							<div class="product__info">
								<p class="info__name">${product.getName()}</p>
								<p class="info__price">
									<fmt:formatNumber 
										value="${product.getPrice()}"
										maxFractionDigits="0" 
										currencyCode="VND" type="currency" />
								</p>
							</div>
							<a class="product__btn" href="${pageContext.request.contextPath}/order/?id=${product.getId()}">+</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="body__cart">
			<div class="cart__header">
				<h3 class="header__heading">Giỏ Hàng</h3>
				<a class="header__clean" href="${pageContext.request.contextPath}/cart/clear">xóa tất cả</a>
			</div>
			<div class="cart__items">
				<c:set var="cartsList" value="${carts}"/>
				<c:choose>
					<c:when test="${cartsList eq null }">
						 <p class="items__status">Bạn chưa chọn sản phẩm nào!</p> 
					 </c:when>
					<c:when test="${cartsList.size() eq 0}">
						<p class="items__status">Bạn chưa chọn sản phẩm nào!</p>
					</c:when>
					<c:otherwise>
						<c:forEach var="cart" items="${carts}">
							<c:set var="total_money" value="${total_money + cart.getPrice() * cart.getQuantity()}"/>
							<c:set var="total_amount" value="${total_amount+cart.getQuantity()}"/>
							<div class="items__item">
								<div class="item__info">
									<p class="item-info__name">${cart.getProduct().getName()}</p>
									<p class="item-info__size">
										Size M, ${cart.getIce()}, ${cart.getSugar()},
										<c:forEach var="topping" items="${cart.getTopping()}">
											${topping.getName()},
										</c:forEach>
									</p>
									<p class="item-info__price">
										<fmt:formatNumber var="money" 
														  value="${cart.getPrice()}" 
														  type="currency" 
														  currencyCode="VND" 
														  maxFractionDigits="0" />
										
										<fmt:formatNumber var="total" 
														  value="${cart.getPrice() * cart.getQuantity()}" 
														  type="currency" 
														  currencyCode="VND" 
														  maxFractionDigits="0"/>
										${money} x ${cart.getQuantity()} = ${total}										
									</p>
								</div>
								<div class="item__action">
									<p id="${ cart.getId() }" class="action__handle action__handle--left" >+</p>
									<p class="action__quantity">${cart.getQuantity()}</p>
									<p id="${ cart.getId() }" class="action__handle action__handle--right">-</p>
								</div>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="cart__pay">
				<div class="pay__info">
					<img class="info__img" alt="cup" src="http://tocotocotea.com/wp-content/themes/tocotocotea/assets/images/icon-glass-tea.png">	
					<span class="info__cal"> 
						x ${total_amount eq null ? 0 : total_amount} = 
						<fmt:formatNumber var="_total" 
										  value="${total_money eq null ? 0 : total_money}" 
										  type="currency" 
										  currencyCode="VND" 
										  maxFractionDigits="0"/>
					  	${_total}
					</span>			
				</div>
				<a class="pay__btn" href="#">Thanh toán</a>
			</div>
		</div>
		<c:set var="product" value="${product}" />
		<c:if test="${product ne null}">
			<form action="/clone-tocotoco/cart/add" method="POST" class="order" accept-charset="utf-8">
				<div class="order__background"></div>
				<div class="order__product-form">
					<input hidden="hidden" value="${product.getId()}" name="id">
					<div class="product-form-info">
						<img class="product-form-info__product-image"
							 src="${product.getImage()}"
							 alt="${product.getName()}">
						<div class="product-form-info__info">
							<p class="form-info__name">${product.getName()}</p>
							<p class="form-info__price">
								<fmt:formatNumber value="${product.getPrice()}" currencyCode="VND"
									type="currency" maxFractionDigits="0" />
							</p>
							<p class="form-info__desc">Chưa có thông tin.</p>
							<img class="form-info__like" alt="heart" src="../image/heart.png">
							<div class="form-info__action">
								<button type="button" id="increase" class="form-action__handle form-action__handle--blue form-action__handle--circle">+</button>
								<input id="quantity" class="form-action__quantity" type="text" value="1" name="quantity" readonly>
								<button type="button" id="decrease" class="form-action__handle form-action__handle--blue form-action__handle--circle">-</button>
								<button class="form-action__handle form-action__handle--long">MUA NGAY</button>
							</div>
						</div>
					</div>
					
					<div class="product-form-option">
						<div class="option__size">
							<h3 class="size__heading">Chọn size</h3>
							<div class="size__select">
								<input id="size--M" class="select__radio" type="radio" value="Size M" checked/>
								<label class="select__text" for="size--M">Size M</label>
							</div>
						</div>
						<div class="option__sugar">
							<h3 class="sugar__heading">
								Chọn đường
							</h3>
							<div class="sugar__list-sugars">
								
								<div class="list__select"> 
									<input id="100%" class="select__radio" type="radio" value="100% đường"  name="sugar" checked>
									<label class="select__text" for="100%">100% đường</label>
								</div>
								
								<div class="list__select"> 
									<input id="70%" class="select__radio" type="radio" value="70% đường"  name="sugar">
									<label class="select__text" for="70%">70% đường</label>
								</div>
								
								<div class="list__select"> 
									<input id="50%" class="select__radio" type="radio" value="50% đường"  name="sugar">
									<label class="select__text" for="50%">50% đường</label>
								</div>
								
								<div class="list__select"> 
									<input id="30%" class="select__radio" type="radio" value="30% đường"  name="sugar">
									<label class="select__text" for="30%">30% đường</label>
								</div>
								
								<div class="list__select"> 
									<input id="0%" class="select__radio" type="radio" value="Không đường"  name="sugar">
									<label class="select__text" for="0%">Không đường</label>
								</div>
							</div>
						</div>
						<div class="option__ice">
							<h3 class="ice__headding">Chọn đá</h3>
							<div class="ice__list-ices">
								<div class="list__select"> 
									<input id="100% đá" class="select__radio" type="radio" value="100% đá"  name="ice" checked>
									<label class="select__text" for="100% đá">100% đá</label>
								</div>
								
								<div class="list__select"> 
									<input id="70% đá" class="select__radio" type="radio" value="70% đá"  name="ice">
									<label class="select__text" for="70% đá">70% đá</label>
								</div>
								
								<div class="list__select"> 
									<input id="50% đá" class="select__radio" type="radio" value="50% đá"  name="ice">
									<label class="select__text" for="50% đá">50% đá</label>
								</div>
								
								<div class="list__select"> 
									<input id="30% đá" class="select__radio" type="radio" value="30% đá"  name="ice">
									<label class="select__text" for="30% đá">30% đá</label>
								</div>
								
								<div class="list__select list__select--full-width"> 
									<input id="0% đá" class="select__radio" type="radio" value="Không đá"  name="ice">
									<label class="select__text" for="0% đá">Không đá</label>
								</div>
							</div>
						</div>
						<div class="option__topping">
							<h3 class="topping__heading">
								Chọn topping
							</h3>
							<div class="topping__list-toppings">
								<c:forEach var="topping" items="${toppings}">
									<div class="list__select list__select--full-width"> 
										<input id="${topping.getId()}" class="select__radio" type="checkbox" value="${topping.getId()}"  name="topping">
										<label class="select__text" for="${topping.getId()}">${topping.getName()}</label>
										<fmt:formatNumber var="price_topping" 
														  value="${topping.getPrice()}" 
														  type="currency" 
														  currencyCode="VND" 
														  maxFractionDigits="0"/>
										<label class="select__text2" for="${topping.getId()}">+${price_topping}</label>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</form>
		</c:if>
	</div>
	<script type="text/javascript" src="../js/order.js"></script>
</body>
</html>






