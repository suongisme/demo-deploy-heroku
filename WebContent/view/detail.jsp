<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Baloo+2:wght@700&display=swap" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/detail.css">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="product">
		<img class="product__image" src="${product.getImage()}" alt="${product.getName()}">
		<div class="product__info">
			<div>
				<p class="info__name">${product.getName().toUpperCase()}</p>
				<p class="info__price">
					<fmt:formatNumber 
						value="${product.getPrice()}" 
						maxFractionDigits="0" 
						currencyCode="VND" 
						type="currency"/>
				</p>
				<a class="info__btn" href="${pageContext.request.contextPath}/order/?id=${product.getId()}" target="_blank">Mua Ngay</a>
				<div class="info__desc">
					<h4>DESCRIPTION</h4>
					<p class="desc__text">${product.getDescription()}</p>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="footer.jsp" %>
</body>
</html>
