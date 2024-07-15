<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <title>네이버 예약</title>
    
    <!-- css files -->
    <link href="css/style.css?ver=1" rel="stylesheet" type="text/css">
</head>

<body>
    <div id="container">
    
    <!-- 상단 header 부분 -->
    	<div class="header">
            <header class="header_tit">
                <h1 class="logo">
                    <a href="https://m.naver.com/" class="lnk_logo" title="네이버"> <span class="spr_bi ico_n_logo">네이버</span> </a>
                    <a href="/reservation/myreservation" class="lnk_logo" title="예약"> <span class="spr_bi ico_bk_logo">예약</span> </a>
                </h1>
                <a href="/reservation/login" class="btn_my"> <span class="viewReservation" title="예약확인">예약확인</span> </a>
            </header>
        </div>     

        <hr>
        
        <!-- 이벤트 영역 -->
        <div class="event">
            <div class="section_visual">
                <div class="group_visual">
                    <div class="container_visual">
                        <div> <!--카테고리 영역-->
                        	<div class="container_visual">
                                <!-- 슬라이딩기능: 이미지 (type = 'th')를 순차적으로 노출 -->
                                <ul class="visual_img">
                                </ul>
                            </div>
                            <span class="nxt_fix" style="display:none;"></span>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 카테고리 탭 -->
            <div class="section_event_tab">
                <ul class="event_tab_lst tab_lst_min">
                    <li class="item" data-category="0">
                    	<!-- active 클래스 : 초록색으로 바꿔줌-->
                        <a class="anchor active"> <span>전체리스트</span> </a>
                    </li>
                    <li class="item" data-category="1">
                        <a class="anchor"> <span>전시</span> </a>
                    </li>
                    <li class="item" data-category="2">
                        <a class="anchor"> <span>뮤지컬</span> </a>
                    </li>
                    <li class="item" data-category="3">
                        <a class="anchor"> <span>콘서트</span> </a>
                    </li>
                    <li class="item" data-category="4">
                        <a class="anchor"> <span>클래식</span> </a>
                    </li>
                    <li class="item" data-category="5">
                        <a class="anchor"> <span>연극</span> </a>
                    </li>
                </ul>
            </div>
            
            <!-- 카테고리 영역 -->
            <div class="section_event_lst">
            	<!-- 카테고리 갯수 -->
                <p class="event_lst_txt">바로 예매 가능한 행사가 <span class="pink">{count}개</span> 있습니다</p>
                <!-- 카테고리 결과 -->
                <div class="wrap_event_box">
                    <!-- [D] lst_event_box 가 2컬럼으로 좌우로 나뉨, 더보기를 클릭할때마다 좌우 ul에 li가 추가됨 -->
                    <!-- 더보기 -->
                    <div class="more">
                        <button class="btn"><span>더보기</span></button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <footer>
        <div class="gototop">
            <a href="#" class="lnk_top"> <span class="lnk_top_text">TOP</span> </a>
        </div>
        <div class="footer">
            <p class="dsc_footer">네이버(주)는 통신판매의 당사자가 아니며, 상품의정보, 거래조건, 이용 및 환불 등과 관련한 의무와 책임은 각 회원에게 있습니다.</p>
            <span class="copyright">© NAVER Corp.</span>
        </div>
    </footer>
    
    <!-- template -->
    <script type="text/html" id="promotionItem">
    <li class="item" style="background-image: url({save_file_name});">
        <a href="#">
			<span class="img_btm_border"></span>
			<span class="img_right_border"></span>
			<span class="img_bg_gra"></span>
            <div class="event_txt">
                <h4 class="event_txt_tit"></h4>
                <p class="event_txt_adr"></p>
                <p class="event_txt_dsc"></p>
            </div>
        </a>
    </li>
    </script>

    <script type="text/html" id="itemList">
	<li class="item">
        <a href="/reservation/detail?id={id}" class="item_book">
            <div class="item_preview"><img class="img_thumb" src="{save_file_name}">
            	<span class="img_border"></span>
            </div>
            <div class="event_txt">
            	<h4 class="event_txt_tit">
					<span>{description}</span>
					<small class="sm">{place_name}</small>
		    	</h4>
                <p class="event_txt_dsc">{content}</p>
            </div>
         </a>
    </li>
    </script>
    
    <!--JS 연결-->
	<script src="js/promotionJS.js"></script>
	<script src="js/productJS.js"></script>
</body>
</html>
