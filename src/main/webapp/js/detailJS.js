// 이미지 인덱스
let currentIndex = 0;

// 클릭시 이미지 변경
function makeImgTemplate(data, direction) {	
	const imgElement = document.querySelectorAll('.visual_img.detail_swipe .img_thumb');
	const numElements = document.querySelectorAll(".figure_pagination .num");
	
	if (direction == 'next' && currentIndex >= 0 && currentIndex < data['imgList'].length - 1) {
		currentIndex++;
		updateImages();
		
	} else if (direction == 'prev' && currentIndex > 0 && currentIndex <= data['imgList'].length - 1) {
		currentIndex--;
		updateImages();
	}
	
	function updateImages() {
	    imgElement.forEach(img => {
	      img.setAttribute('src', `${data['imgList'][currentIndex]}`);
	      numElements[0].innerHTML = currentIndex + 1;
	    });
  	}
}

// comment
function makeCommentTemplate(data){
	// Template 불러오기
	var commentContent = document.getElementById("comment_img").innerHTML;
	var	htmlComment = "";
	
	if (commentContent){
		var commentItem = data;
		
		// class="list_short_review"인 node 선택
		var commentContainer = document.getElementsByClassName("list_short_review")[0];
		
		for (var i = 0; i < 3; i++){
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
		makeImgTemplate(data, direction);
		makeCommentTemplate(data);
		
	});
	oReq.open("GET", url);
	oReq.send();
}

// 클릭시
document.addEventListener("click", function(event) {
	if (event.target.classList.contains('btn_nxt') || event.target.classList.contains('btn_prev')) {
		const urlParams = new URLSearchParams(window.location.search);
		const id = urlParams.get('id');
		const direction = event.target.classList.contains('btn_nxt') ? 'next' : 'prev';
		const url = `http://localhost:8080/reservation/api/detail?id=${id}`;

		sendAjax(url, direction);
	}
});

// load시
document.addEventListener("DOMContentLoaded", function(){	
	const urlParams = new URLSearchParams(window.location.search);
	const id = urlParams.get('id');
	
	const url = `http://localhost:8080/reservation/api/detail?id=${id}`;
	sendAjax(url, id);
})
