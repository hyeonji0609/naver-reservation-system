function makeTemplate(data, categoryNum) {
	// tab마다 갯수 불러오기
	var spanCount = document.querySelector(".pink");
  	
  	if (spanCount) {
    	const resultCount = data.count;
    	spanCount.innerText = `${resultCount}개`;
  	}
  	
  	// tab마다 내용을 바꿔주기
  	var container = document.querySelector('.wrap_event_box');
  	
  	// 컨테이너의 lst_event_box 비워주기
  	if (seeMoreCount == 0){
		  var eventBoxes = container.querySelectorAll('.lst_event_box');
		  eventBoxes.forEach(function(box) {
			  box.remove();
		});  
	  }
  	
  	// Template 불러오기
  	var tabContent = document.getElementById("itemList").innerHTML;
  	var htmlProduct = "";
  	
  	if (tabContent) {
		  const productItem = data.list;
		  
		  // 2개씩 class="lst_event_box"에 담기
		  for (var i = 0; i < productItem.length; i+= 2) {  	
			  var itemList = document.createElement('ul');
			  itemList.classList.add('lst_event_box');
			  
			  for (let j = i; j < i + 2 && j < productItem.length; j++) {
				  const item = productItem[j];
				  
				  // key에 매치가 되는 것들로 replace
				  htmlProduct = tabContent.replace(/{(\w+)}/g, (match, key) => item[key]);
				  
				  // HTML을 DOM 노드로 변환
				  var parser = new DOMParser();
				  var htmlNode = parser.parseFromString(htmlProduct, 'text/html').body.childNodes[0];
				  
				  // class='lst_event_box'의 자식으로 넣기
				  itemList.appendChild(htmlNode);
			  }
			  // itemList을 container의 더보기 앞에 계속 추가
			  var moreButton = container.querySelector('.more');
			  container.insertBefore(itemList, moreButton);
	  	}
	}
}



// Ajax로 연결
function sendAjax(url, categoryNum) {
    var oReq = new XMLHttpRequest();
    oReq.addEventListener("load", function () {

    	var data = JSON.parse(oReq.responseText);
    	
        makeTemplate(data, categoryNum);
    });
    
    if (categoryNum == 0) {
		oReq.open("GET", url);
    	oReq.send();	
	} else {
		oReq.open("GET", url + '/' + categoryNum);
    	oReq.send();
	}
}

var category_num;
var seeMoreCount;

//첫 화면일 때
document.addEventListener("DOMContentLoaded", function(){
  category_num = 0;
  seeMoreCount = 0;
  
  let url = "http://localhost:8080/reservation/api/products";
  
  // 초기 카테고리 데이터 로드
  sendAjax(url, category_num);
  
  // 클릭 할때 마다 데이터 로드
  const tabItems = document.querySelectorAll('.event_tab_lst .item');

  tabItems.forEach(function(item) {
    item.addEventListener('click', function(evt) {
      // 이전에 활성화된 active tab 제거
      const activeTab = document.querySelector('.event_tab_lst .item .active');
      if (activeTab) {
        activeTab.classList.remove('active');
      }
      
      // 현재 클릭된 tab을 active tab으로 활성화
      const categoryNum = evt.target.closest('.item').dataset.category;
      category_num = categoryNum; 
      const clickedTab = document.querySelector(`.event_tab_lst .item[data-category="${categoryNum}"] .anchor`);
      clickedTab.classList.add('active');
      
      // 아이템 초기화
      seeMoreCount = 0;
      
      sendAjax(url, categoryNum);
    });
  });
});

// 더보기 눌렸을 때 
var more = document.querySelector(".more");

more.addEventListener("click", function (evt) {
	
	if(evt.target.innerText == "더보기") {
		seeMoreCount++;
		
		if(category_num == 0) { //전체 리스트 보기 
			sendAjax("http://localhost:8080/reservation/api/products?start="+ 4*seeMoreCount, category_num);
		}
		else { //카테고리 별 보기 
			sendAjax("http://localhost:8080/reservation/api/products/" + category_num + "?start="+ 4*seeMoreCount, 0);
		}
	}
});