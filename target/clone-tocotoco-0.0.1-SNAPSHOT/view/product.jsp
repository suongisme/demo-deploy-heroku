<%@page import="suongnguyen.tocotoco.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Baloo+2:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
<title>TocoToco</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="products">
		<div class="products__menu">
			<h2 class="menu__heading">MENU</h2>
			<ul class="menu__list">
				<c:forEach var="category" items="${categories}">
	                <li class="list__category">
	                    <a class="category__text" href="#${category.getId()}">${category.getName()}</a>
	                </li>
                </c:forEach> 
			</ul>
		</div>
		<div class="products__list">
			<c:forEach var="category" items="${categories}">
				<div>
					<h2 id="${category.getId()}" class="list__heading">${category.getName()}</h2>	
					<div class="list__body">
						<c:set var="productList" value="${productDao.selectByCategoryID(category.getId())}"/>
						<c:choose>
							<c:when test="${productList.size() > 0 }">
								<c:forEach var="product" items="${productList}">
										<div class="list__product">
							                    <p class="product__sale">${product.getSale()}%</p>
							                    <a class="product__img" href="${pageContext.request.contextPath}/product/${product.getId()}"> 
							                        <img src="${product.getImage()}" alt="${product.getName()}">
							                    </a>
							                    <div class="products__info">
							                        <p class="product-info__name">${product.getName()}</p>
							                        <p class="product-info__price">
							                        	<fmt:formatNumber 
							                        		var="money" 
							                        		value="${product.getPrice()}" 
							                        		type="currency"
							                        		currencyCode="VND"
							                        		maxFractionDigits="0"
						                        		/>
						                        		${money}
							                        </p>
							                        <div class="container-btn">
							                            <a class="product-info__btn product-info__btn--primary" href="${pageContext.request.contextPath}/order/?id=${product.getId()}" target="_blank">Mua Ngay</a>
							                            <a class="product-info__btn " href="${pageContext.request.contextPath}/product/${product.getId()}">Xem chi tiết</a>
							                        </div>
							                    </div>
							                </div>
				                </c:forEach>
							</c:when>
							<c:otherwise>
			            		<h3 style="color: var(--brown-color-primary); margin-left: 30px; font-size: 1.5rem">Khong co san pham</h3>
			            	</c:otherwise>
						</c:choose>
           			 </div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>