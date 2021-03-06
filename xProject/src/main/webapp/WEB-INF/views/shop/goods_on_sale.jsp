<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할인품목 | 슈퍼마켙!</title>
<%@ include file="../include/header.jsp" %>
</head>
<body>
<%@ include file="../include/menu.jsp" %>
<section class="section-margin calc-30px">
      <div class="container">
        <div class="section-intro pb-30px">
          <!-- <p style="letter-spacing:3px; margin:0 auto;">SALE!</p> -->
          <div class="info__img">
			<img src="${path}/img/할인품목.png" 
				style="display:block; margin:0px auto; margin-top:30px; width:200px; height:55px;">
		  </div>
        </div>
        <div class="row">
        <c:forEach var="row" items="${map.list}">
          <input type = "hidden" value="${row.code}">
          <div class="col-md-6 col-lg-4 col-xl-3">
            <div class="card text-center card-product">
              <div class="card-product__img">
                <img class="card-img" src="${path}/img/goods/on_sale/${row.img_path}">
                <c:if test="${sessionScope.userid != 'admin'}">
                <ul class="card-product__imgOverlay">
                  <form name="form1" method="post" action="${path}/shop/goodscart/insert.do">
                  <li><button type="submit"><i class="ti-shopping-cart">
                  	<input type="hidden" name="good_id" value="${row.good_id}">
                  	<input type="hidden" name="amount" value="1">
                  </i></button></li>
                  </form>
                </ul>
                </c:if>
              </div>              	
              <div class="card-body">
			<!-- 관리자에게만 편집 버튼 표시 -->
			<h4 class="card-product__title">
              <a href="${path}/shop/goods/detail/${row.good_id}"> ${row.gname}</a></h4>
               <c:choose>
               	<c:when test="${row.on_discount==0}">               	
                <p class="card-product__price">
                	<fmt:formatNumber value="${row.price}" pattern="#,###" />&nbsp;원
                </p>
               	</c:when>
               	<c:otherwise>
               	<p class="card-product__price">
                	<span style="color:red; font-weight:600;">(${row.on_discount}%↓)</span><br>
                	<fmt:formatNumber value="${row.price}" pattern="#,###" />&nbsp;원
                </p>
               	</c:otherwise> 
               </c:choose>
			<c:if test="${sessionScope.userid=='admin'}"> <br>
			  <a class="btn btn-sm" style="background-color:#384aec; color:white;" 
			 	href="${path}/shop/goods/edit/${row.good_id}">편집</a></c:if>                
              </div>
            </div>
          </div>
          </c:forEach>
         </div>
        </div>         
</section>

<br>
<%@ include file="../include/footer.jsp" %>
</body>
</html>