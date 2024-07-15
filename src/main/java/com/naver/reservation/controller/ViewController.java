package com.naver.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.naver.reservation.dto.DetailDto;
import com.naver.reservation.dto.ReservationDto;
import com.naver.reservation.service.DetailService;
import com.naver.reservation.service.ReservationService;

@Controller
public class ViewController {
	@Autowired
	ReservationService reservationService;
	@Autowired
	DetailService detailService;
	
	@GetMapping(path="/reserve")
	public String reserve(
			@RequestParam(name="id", required=true) Integer id,
			Model model) {
	    
		ReservationDto productInfo = reservationService.getProductInfo(id);
		
		model.addAttribute("productInfo", productInfo);
		
	    return "reserve";
	}
	
	@GetMapping(path="/detail")
	public String detail(
			@RequestParam(name="id", required=true) int id,
			ModelMap model) {
		
		// id에 해당하는 Header 정보 구하기
		DetailDto headerInfo = detailService.getHeaderDetail(id);
		List<DetailDto> headerImg = detailService.getMainImg(id);
		List<DetailDto> comment = detailService.getCommentAll(id);
		
		// JSP에서 값을 사용할 수 있도록 넣어줌
		model.addAttribute("headerInfo", headerInfo); // JSP에서 사용 가능하도록 model에 값을 넣음
		model.addAttribute("headerImg", headerImg); // JSP에서 사용 가능하도록 model에 값을 넣음
		model.addAttribute("comment", comment);
		
		return "detail";
	}
	
	@GetMapping(path="/review")
	public String review(@RequestParam(name="id", required=true) int id,
			ModelMap model) {
		
		DetailDto headerInfo = detailService.getHeaderDetail(id);
		List<DetailDto> comment = detailService.getCommentAll(id);
		
		model.addAttribute("headerInfo", headerInfo);
		model.addAttribute("comment", comment);
		
		return "review";
	}
	
	@GetMapping(path="/login")
	public String bookinglogin() {
		
		return "bookinglogin";
	}
	
	@PostMapping(path="/myreservation")
	public String myreservation(
			) {
		
		return "myreservation";
	}
	
	@GetMapping(path="/reviewWrite")
	public String reviewWrite() {
		
		return "reviewWrite";
	}

}
