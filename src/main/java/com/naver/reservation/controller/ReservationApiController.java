package com.naver.reservation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naver.reservation.dto.DetailDto;
import com.naver.reservation.dto.ProductDto;
import com.naver.reservation.dto.ReservationDto;
import com.naver.reservation.service.DetailService;
import com.naver.reservation.service.MainService;
import com.naver.reservation.service.ReservationService;

@RestController
@RequestMapping(path="/api")
public class ReservationApiController {
	@Autowired
	MainService mainService;
	@Autowired
	DetailService detailService;
	@Autowired
	ReservationService reservationService;
	
	@GetMapping(path = "/promotions")
	public Map<String, Object> listInPromotion() {
		List<ProductDto> listInPromotion = mainService.getPromotion();
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", listInPromotion);
		
		return map;
	}
	
	@GetMapping(path="/products")
	public Map<String, Object> listInProduct(@RequestParam(name="start", required=false, defaultValue="0") int start){
		List<ProductDto> list = mainService.getProducts(start);
		
		// 전체 페이지수 구하기
		int count = mainService.getCountAll(); // count : 현재 카테고리의 컨텐츠 수
		int pageCount = count / mainService.LIMIT; // 넘길 수 있는 페이지 수
		if(count % mainService.LIMIT > 0)
			pageCount++;
		
		// 페이지 수만큼 start의 값을 리스트로 저장
		// 예를 들면 페이지수가 3이면
		// 0, 4, 8 이렇게 저장된다.
		// list?start=0 , list?start=4, list?start=8 으로 링크가 걸린다.
		List<Integer> pageStartList = new ArrayList<>();
		for(int i = 0; i < pageCount; i++) {
			pageStartList.add(i * mainService.LIMIT);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("count", count);
		map.put("pageStartList", pageStartList);
		
		return map;
		
	}
	
	@GetMapping("/products/{category}")
	public Map<String, Object> listInCategory(@PathVariable(name="category") int category, 
											  @RequestParam(name="start", required=false, defaultValue="0") int start) {
		List<ProductDto> listInCategory = mainService.getProductsInCategory(start, category);
		
		// 카테고리에 속한 페이지수 구하기
		int countInCategory = mainService.getCountWithinCategory(category);
		int pageCountInCategory = countInCategory / mainService.LIMIT;
		if(countInCategory % mainService.LIMIT > 0)
			pageCountInCategory++;
		
		// 페이지 수만큼 start의 값을 리스트로 저장
		List<Integer> pageStartListInCategory = new ArrayList<>();
		for(int i = 0; i < pageCountInCategory; i++) {
			pageStartListInCategory.add(i * mainService.LIMIT);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", listInCategory);
		map.put("count", countInCategory);
		map.put("pageStartList", pageStartListInCategory);
		
		return map;
	}
	
	@GetMapping(path="/detail")
	public Map<String, Object> listInImg(
			@RequestParam(name="id", required=true) int id){
		
		// img 불러오기
		List<DetailDto> headerImg = detailService.getMainImg(id);
		// comment 불러오기
		List<DetailDto> comment = detailService.getCommentAll(id);
		
		List<String> headerImgList = new ArrayList<>();
	    
	    /**
	     * comment가 아래와 같은 객체라고 하자.
	     * [DetailDto(comment="Hello"), DetailDto(comment="World"), DetailDto(comment="Java")]
	     * 그러면 이 객체를 순회하면서 comment에 해당하는 것만 가지고 온다.
	     * **/
		for (DetailDto dto : headerImg) {
	        headerImgList.add(dto.getSave_file_name());
	    }
		
		Map<String, Object> map = new HashMap<>();
		map.put("imgList", headerImgList);
		map.put("commentList", comment);
		
		return map;
		
	}
	
	@GetMapping(path="reserve")
	public Map<String, Object> listProductInfo(
			@RequestParam(name="id", required=true) int id){
		
		ReservationDto productInfo = reservationService.getProductInfo(id);
		List<ReservationDto> productPrice = reservationService.getProductPrice(id);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("Info", productInfo);
		map.put("Price", productPrice);
		
		return map;
		
	}
	
}
