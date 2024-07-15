<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- JSTL 사용 -->
<!-- c는 조건문, 반복문, 변수 설정 / fn은 문자열 처리 등 유용한 함수 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="utf-8">
<meta name="description"
	content="네이버 예약, 네이버 예약이 연동된 곳 어디서나 바로 예약하고, 네이버 예약 홈(나의예약)에서 모두 관리할 수 있습니다.">
<meta name="viewport"
	content="width=device-width,initial-scale=1,maximum-scale=1,minimum-scale=1,user-scalable=no">
<title>네이버 예약</title>
<!-- css files -->
<link href="css/style.css?ver=1" rel="stylesheet" type="text/css">
<style>
.container_visual {
	height: 414px;
}
</style>

</head>
<body>
	<div id="container">
		<div class="header fade">
			<header class="header_tit">
				<h1 class="logo">
					<a href="/reservation" class="lnk_logo" title="네이버"> <span
						class="spr_bi ico_n_logo">네이버</span>
					</a> <a href="/reservation" class="lnk_logo" title="예약"> <span
						class="spr_bi ico_bk_logo">예약</span>
					</a>
				</h1>
				<a href="/reservation/bookinglogin/" class="btn_my"> <span title="예약확인">예약확인</span>
				</a>
			</header>
		</div>
		<div class="ct main">
			<div>
				<div class="section_visual">
					<header>
						<h1 class="logo">
							<a href="/reservation" class="lnk_logo" title="네이버"> <span
								class="spr_bi ico_n_logo">네이버</span>
							</a> <a href="/reservation" class="lnk_logo" title="예약"> <span
								class="spr_bi ico_bk_logo">예약</span>
							</a>
						</h1>
						<a href="/reservation/login/" class="btn_my"> <span
							class="viewReservation" title="예약확인">예약확인</span>
						</a>
					</header>

					<!-- 이미지 갯수 부분 -->
					<div class="pagination">
						<div class="bg_pagination"></div>
						<div class="figure_pagination">
							<!-- 클릭할 때마다 num이 바뀌어야 함 -->
							<span class="num">1</span> <span class="num off">/ <span><c:out
										value="${fn:length(headerImg)}" /></span>
							</span>
						</div>
					</div>

					<!-- 슬라이딩 이미지 부분 -->
					<div class="group_visual">
						<div>
							<div class="container_visual" style="width: 414px;">
								<ul class="visual_img detail_swipe">
									<!-- main 이미지 -->
									<li class="item" style="width: 414px;"><img alt=""
										class="img_thumb" src="${headerImg.get(0).save_file_name}">
										<span class="img_bg"></span>
										<div class="visual_txt">
											<div class="visual_txt_inn">
												<h2 class="visual_txt_tit">
													<span>${headerInfo.description}</span>
												</h2>
												<p class="visual_txt_dsc"></p>
											</div>
										</div></li>
								</ul>
							</div>

							<!-- 화살표 -->
							<div class="prev">
								<div class="prev_inn">
									<a href="#" class="btn_prev" title="이전"> <!-- [D] 첫 이미지 이면 off 클래스 추가 -->
										<i class="spr_book2 ico_arr6_lt off"></i>
									</a>
								</div>
							</div>
							<div class="nxt">
								<div class="nxt_inn">
									<a href="#" class="btn_nxt" title="다음"> <i
										class="spr_book2 ico_arr6_rt"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- 펼쳐보기 -->
				<div class="section_store_details">
					<!-- [D] 펼쳐보기 클릭 시 store_details에 close3 제거 -->
					<div class="store_details close3">
						<p class="dsc">${headerInfo.content}</p>
					</div>
					<!-- [D] 토글 상황에 따라 bk_more에 display:none 추가 -->
					<a href="#" class="bk_more _open"> <span class="bk_more_txt">펼쳐보기</span>
						<i class="fn fn-down2"></i>
					</a> <a href="#" class="bk_more _close" style="display: none;"> <span
						class="bk_more_txt">접기</span> <i class="fn fn-up2"></i>
					</a>
				</div>

				<!-- 이번트 정보 -->
				<div class="section_event">
					<div class="event_info_box">
						<div class="event_info_tit">
							<h4 class="in_tit">
								<i class="spr_book ico_evt"></i> <span>이벤트 정보</span>
							</h4>
						</div>
						<div class="event_info">
							<div class="in_dsc">
								[네이버예약 특별할인]<br>R석 50%, S석 60% 할인
							</div>
						</div>
					</div>
				</div>

				<!-- 예매하기 버튼 -->
				<div class="section_btn">
					<button type="button" class="bk_btn"
						onclick="location.href ='/reservation/reserve?id=${headerInfo.id}'">
						<i class="fn fn-nbooking-calender2"></i> <span>예매하기</span>
					</button>
				</div>

				<!-- 한줄평 -->
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
								</span>
							</div>

							<!-- Review -->
							<ul class="list_short_review">
								<!-- comment Template -->
							</ul>
						</div>
						<p class="guide">
							<i class="spr_book2 ico_bell"></i> <span>네이버 예약을 통해 실제 방문한
								이용자가 남긴 평가입니다.</span>
						</p>
					</div>

					<!-- 예매자 한줄평 더보기 -->
					<a class="btn_review_more" href="/reservation/review?id=${headerInfo.id}"> <span>예매자
							한줄평 더보기</span> <i class="fn fn-forward1"></i>
					</a>
				</div>

				<!-- 상세정보 탭 -->
				<div class="section_info_tab">
					<!-- [D] tab 선택 시 anchor에 active 추가 -->
					<ul class="info_tab_lst">
						<li class="item active _detail"><a href="#"
							class="anchor active"> <span>상세정보</span>
						</a></li>
						<li class="item _path"><a href="#" class="anchor"> <span>오시는길</span>
						</a></li>
					</ul>

					<!-- 상세정보 -->
					<!-- [D] 상세정보 외 다른 탭 선택 시 detail_area_wrap에 hide 추가 -->
					<div class="detail_area_wrap">
						<div class="detail_area">
							<div class="detail_info">
								<h3 class="blind">상세정보</h3>
								<ul class="detail_info_group">
									<li class="detail_info_lst"><strong class="in_tit">[소개]</strong>
										<p class="in_dsc"></p></li>
									<li class="detail_info_lst"><strong class="in_tit">[공지사항]</strong>
										<ul class="in_img_group">
											<li class="in_img_lst"><img alt="" class="img_thumb"
												src="https://ssl.phinf.net/naverbooking/20170131_238/14858250829398Pnx6_JPEG/%B0%F8%C1%F6%BB%E7%C7%D7.jpg?type=a1000">
											</li>
										</ul></li>
									<!-- <li class="detail_info_lst"> <strong class="in_tit">[공연정보]</strong>
                                        <ul class="in_img_group">
                                            <li class="in_img_lst"> <img alt="" class="img_thumb" src="https://ssl.phinf.net/naverbooking/20170131_255/1485825099482NmYMe_JPEG/%B0%F8%BF%AC%C1%A4%BA%B8.jpg?type=a1000"> </li>
                                        </ul>
                                    </li> -->
								</ul>
							</div>
						</div>
					</div>

					<!-- 오시는길 -->
					<!-- [D] 오시는길 외 다른 탭 선택 시 detail_location에 hide 추가 -->
					<div class="detail_location hide">
						<div class="box_store_info no_topline">
							<a href="#" class="store_location" title="지도웹으로 연결"> <img
								class="store_map img_thumb" alt="map"
								src="https://simg.pstatic.net/static.map/image?version=1.1&amp;crs=EPSG:4326&amp;baselayer=bl_vc_bg&amp;exception=xml&amp;scale=2&amp;caller=mw_smart_booking&amp;overlayers=ol_vc_an&amp;center=127.0011948,37.5717079&amp;markers=type,default2,127.0011948,37.5717079&amp;level=11&amp;w=340&amp;h=150">
								<span class="img_border"></span> <span class="btn_map"><i
									class="spr_book2 ico_mapview"></i></span>
							</a>
							<h3 class="store_name">엔에이치엔티켓링크(주)</h3>
							<div class="store_info">
								<div class="store_addr_wrap">
									<span class="fn fn-pin2"></span>
									<p class="store_addr store_addr_bold">서울특별시 종로구 종로33길 15</p>
									<p class="store_addr">
										<span class="addr_old">지번</span> <span class="addr_old_detail">서울특별시
											종로구 연지동 270 </span>
									</p>
									<p class="store_addr addr_detail">두산아트센터 연강홀</p>
								</div>
								<div class="lst_store_info_wrap">
									<ul class="lst_store_info">
										<li class="item"><span class="item_lt"> <i
												class="fn fn-call2"></i> <span class="sr_only">전화번호</span>
										</span> <span class="item_rt"> <a href="tel:02-548-0597"
												class="store_tel">02-548-0597</a></span></li>
									</ul>
								</div>
							</div>
							<!-- [D] 모바일 브라우저에서 접근 시 column2 추가와 btn_navigation 요소 추가 -->
							<div class="bottom_common_path column2">
								<a href="#" class="btn_path"> <i class="fn fn-path-find2"></i>
									<span>길찾기</span>
								</a> <a href="#" class="btn_navigation before"> <i
									class="fn fn-navigation2"></i> <span>내비게이션</span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- footer -->
	<footer>
		<div class="gototop">
			<a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span>
			</a>
		</div>
		<div class="footer">
			<p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및
				환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
			<span class="copyright">© NAVER Corp.</span>
		</div>
	</footer>
	<div id="photoviwer"></div>

	<!-- template -->

	<!-- 댓글 -->
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

	<!--JS 연결-->
	<script src="js/detailJS.js"></script>

</body>
</html>