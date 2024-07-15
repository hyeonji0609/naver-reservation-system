<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="description" content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
    <title>네이버 예약</title>
    <link href="css/style.css?ver=1" rel="stylesheet" type="text/css">
</head>

<body>
    <div id="container">
		<!-- [D] 예약하기로 들어오면 header에 fade 클래스 추가로 숨김 -->
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="#" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
					<a href="#" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
				</h1>
				  <a href="#" class="btn_my"> <span title="예약확인">예약확인</span> </a>
			</header>
		</div>
        <div class="ct">
            <div class="wrap_review_list">
                <div class="review_header">
                    <div class="top_title gr">
                        <a href="/reservation/detail?id=${headerInfo.id}" class="btn_back" title="이전 화면으로 이동"> <i class="fn fn-backward1"></i> </a>
                        <h2><a class="title" href="#"></a></h2>
                    </div>
                </div>
                <div class="section_review_list">
                    <div class="review_box">
                        <h3 class="title_h3">예매자 한줄평</h3>
                        <div class="short_review_area">
                            <div class="grade_area">
                            	<!-- [D] 별점 graph_value는 퍼센트 환산하여 width 값을 넣어줌 -->
								<!-- average 구하기 -->
								<c:set var="sum" value="0" />
								<c:forEach var="item" items="${comment}">
									<c:set var="sum" value="${sum + item.score}" />
									<!-- 'item' 값을 'sum'에 더함 -->
								</c:forEach>
								<c:choose>
									<c:when test="${fn:length(comment) == 0}">
										<c:set var="average" value="0" />
									</c:when>
									<c:otherwise>
										<c:set var="average" value="${sum div fn:length(comment)}" />
									</c:otherwise>
								</c:choose>
								<c:set var="ratio" value="${average / 5.0 * 100}" />
								
								
								<span class="graph_mask"> <em class="graph_value"
									style="width: ${ratio}%;"></em>
								</span> <strong class="text_value"> <span><fmt:formatNumber
											value="${average}" pattern="${average == 0 ? '0' : '.0'}" /></span>
									<em class="total">5.0</em>
								</strong> <span class="join_count"> <em class="green">${fn:length(comment)}건</em>
									등록
								</span></div>
							
                            <ul class="list_short_review">
                            </ul>
                        </div>
                        <p class="guide"> <i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한 이용자가 남긴 평가입니다.</span> </p>
                    </div>
                </div>
            </div>
        </div>
        <hr> </div>
		<footer>
	        <div class="gototop">
	            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
	        </div>
	        <div id="footer" class="footer">
	            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
	            <span class="copyright">© NAVER Corp.</span>
	        </div>
	    </footer>
	    
	<script type="text/html" id="comment_img">
	<li class="list_item">
		<div>
        	<div class="review_area no_img">
        		<h4 class="resoc_name">{description}</h4>
        		<p class="review">{comment}</p>
        	</div>
        	<div class="info_area">
       			<div class="review_info">
					<span class="grade">{score}</span>
					<span class="name">{reservation_email}</span>
					<span class="date">{reservation_date} 방문</span>
				</div>
       		</div>
        </div>
    </li>
	</script>
	
	<script type="text/javascript">
		function makeCommentTemplate(data){
			// Template 불러오기
			var commentContent = document.getElementById("comment_img").innerHTML;
			var	htmlComment = "";
			
			if (commentContent){
				var commentItem = data;
				
				// class="list_short_review"인 node 선택
				var commentContainer = document.getElementsByClassName("list_short_review")[0];
				var commentListLength = commentItem['commentList'].length;
				
				for (var i = 0; i < commentListLength; i++){
					const item = commentItem['commentList'][i];
		
					// 템플릿 채워넣기
					htmlComment = commentContent.replace(/{(\w+)}/g, (match, key) => item[key]);
					
					// HTML을 DOM 노드로 변환
					var parser = new DOMParser();
					var htmlNode = parser.parseFromString(htmlComment, 'text/html').body.childNodes[0];
					
					// class = "list_short_review"의 자식으로 넣기
					commentContainer.appendChild(htmlNode);
				}
			} 
		}
		
		// Ajax로 연결
		function sendAjax(url, direction) {
			var oReq = new XMLHttpRequest();
			oReq.addEventListener("load", function() {
				
				var data = JSON.parse(oReq.responseText);
				makeCommentTemplate(data);
				console.log(data);
				
			});
			oReq.open("GET", url);
			oReq.send();
		}
		
		document.addEventListener("DOMContentLoaded", function(){	
			const urlParams = new URLSearchParams(window.location.search);
			const id = urlParams.get('id');
			
			console.log(id);
			
			const url = 'http://localhost:8080/reservation/api/detail?id='+id;
			sendAjax(url, id);
		})	
	</script>
	
</body>

</html>
