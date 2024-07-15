 function makePriceTemplate(data){
	// Template 불러오기
	var priceListHTML = document.getElementById("priceList").innerHTML;
	
	const priceList = data.Price;
	var priceHTML = "";
	
	// 2가 뮤지컬
	priceList.forEach(item => {
	  let price = '';
	
	  if (item.category_id === 2) { // 뮤지컬인 경우
	    switch (item.price_type_name) {
	      case 'V':
	        price = 'VIP 석 : ' + item.price + '원';
	        break;
	      case 'R':
	        price = 'R석 : ' + item.price + '원';
	        break;
	      case 'B':
	        price = 'B석 : ' + item.price + '원';
	        break;
	      case 'S':
	        price = 'S석 : ' + item.price + '원';
	        break;
	      case 'D':
	        price = '평일 : ' + item.price + '원';
	        break;
	      default:
	        price = '기타 : ' + item.price + '원';
	    }
	  } else { // 뮤지컬이 아닌 경우
	    switch (item.price_type_name) {
	      // 여기에 뮤지컬이 아닌 경우의 처리 로직을 작성하시면 됩니다.
	      case 'A':
	        price = '성인(만 19~64세) : ' + item.price + '원';
	        break;
	      case 'Y':
	        price = '청소년(만 13~18세) : ' + item.price + '원';
	        break;
	      case 'B':
	        price = '유아(만 4~12세) : ' + item.price + '원';
	        break;
	      case 'S':
	        price = '세트 : ' + item.price + '원';
	        break;
	      default:
	        price = '기타 : ' + item.price + '원';
	    }
	  }
	
	  if (price !== '') {
	    priceHTML += `<p>${price}</p>`;
	  }
	});
	
	// <p class="dsc"> 태그 내부의 HTML 변경
	priceListHTML = priceListHTML.replace(/<p class="dsc">[\s\S]*?<\/p>/, '<div class="dsc">' + priceHTML + '</div>');

	
	// HTML을 DOM 노드로 변환
	var parser = new DOMParser();
	var newNode = parser.parseFromString(priceListHTML, 'text/html').body.childNodes;
	
	// 원래 HTML에 추가할 대상 요소 선택
	var targetElement = document.getElementsByClassName("store_details")[0];
	
	for (var i = 0; i < newNode.length; i++) {
		targetElement.appendChild(newNode[i].cloneNode(true));
	}
}

function makePriceButtonTemplate(data) {
	// Template 불러오기
	var priceButtonHTML = document.getElementById("priceButton").innerHTML;
	const priceList = data.Price;
	const priceElements = [];

	for (let i = 0; i < priceList.length; i++) {
		const price = priceList[i];
		let priceTypeName = price.price_type_name;

		// priceTypeName 변경 로직 추가
		if (price.category_id === 2) { // 뮤지컬인 경우
			switch (priceTypeName) {
				case 'V':
					priceTypeName = 'VIP 석';
					break;
				case 'R':
					priceTypeName = 'R석';
					break;
				case 'B':
					priceTypeName = 'B석';
					break;
				case 'S':
					priceTypeName = 'S석';
					break;
				case 'D':
					priceTypeName = '평일';
					break;
				default:
					priceTypeName = '기타';
			}
		} else { // 뮤지컬이 아닌 경우
			switch (priceTypeName) {
				case 'A':
					priceTypeName = '성인(만 19~64세)';
					break;
				case 'Y':
					priceTypeName = '청소년(만 13~18세)';
					break;
				case 'B':
					priceTypeName = '유아(만 4~12세)';
					break;
				case 'S':
					priceTypeName = '세트';
					break;
				default:
					priceTypeName = '기타';
			}
		}

		// priceList 배열을 반복하며 각 가격 항목에 대한 HTML 생성
		const priceElement = priceButtonHTML.replace(/{price_type_name}/g, priceTypeName) // 가격 금액 대체
			.replace(/{price}/g, price.price) // 제품명 대체
			.replace(/{discount_rate}/g, price.discount_rate); // 할인율 대체

		// 각 priceElement를 DOM 노드로 변환
		var parser = new DOMParser();
		var htmlNode = parser.parseFromString(priceElement, 'text/html').body.firstChild;

		priceElements.push(htmlNode);

		// 생성된 가격 항목 HTML을 ticket_body div에 추가
		const ticketBodyContainer = document.getElementsByClassName('ticket_body')[0];
		priceElements.forEach(element => {
			ticketBodyContainer.appendChild(element);
			});
	}
}


// Ajax로 연결
function sendAjax(url) {
	var oReq = new XMLHttpRequest();
	
	oReq.addEventListener("load", function() {
		var data = JSON.parse(oReq.responseText);
		makePriceTemplate(data);
		makePriceButtonTemplate(data);
	});
	
	oReq.open("GET", url);
	oReq.send();
}

// load시
document.addEventListener("DOMContentLoaded", function(){	
	const urlParams = new URLSearchParams(window.location.search);
	const id = urlParams.get('id');
	
	const url = `http://localhost:8080/reservation/api/reserve?id=${id}`;
	sendAjax(url);
})
