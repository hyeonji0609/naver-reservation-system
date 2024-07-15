let currentIndex = 0;
let intervalId;

function imgMove(visualImg, numItems) {
  const items = visualImg.children;

  function showNextImage() {
    for (let i = 0; i < items.length; i++) {
      items[i].style.display = "none";
    }
    items[currentIndex].style.display = "block";
    currentIndex = (currentIndex + 1) % numItems;
  }

  function startSlideshow() {
    intervalId = setInterval(showNextImage, 3000);
  }

  function stopSlideshow() {
    clearInterval(intervalId);
  }

  startSlideshow(); // 슬라이드쇼 시작

  // 마우스 오버 시 슬라이드쇼 일시 중지, 마우스 아웃 시 재개
  visualImg.addEventListener("mouseover", stopSlideshow);
  visualImg.addEventListener("mouseout", startSlideshow);
}

function makePromotionTemplate(data){
	// template를 불러옴
	var htmlPromotion = document.getElementById("promotionItem").innerHTML;
	var promotionHTML = "";
	var visualImg = document.querySelector(".visual_img")

  	for (var i = 0; i < data.list.length; i++) {    	
    	promotionHTML = htmlPromotion.replace("{save_file_name}", data.list[i].save_file_name);
       	visualImg.innerHTML += promotionHTML
  	}
  	
  	imgMove(visualImg, data.list.length);
  	
}

function sendAjaxForPromotion(url) {
	// 서버 통신을 위한 객체 생성
    var oReq = new XMLHttpRequest();
    
    // addEventListener : HTTP 요청이 성공적으로 완료되었을 때 발생
    oReq.addEventListener("load", function () { 
    	var data = JSON.parse(oReq.responseText);
        makePromotionTemplate(data);
    });
    oReq.open("GET", url);
    oReq.send();
}

//첫 화면 
document.addEventListener("DOMContentLoaded", function(){
	sendAjaxForPromotion("http://localhost:8080/reservation/api/promotions");
});
