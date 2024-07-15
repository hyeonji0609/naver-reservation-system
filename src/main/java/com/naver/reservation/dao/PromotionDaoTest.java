package com.naver.reservation.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.naver.reservation.config.ApplicationConfig;
import com.naver.reservation.dto.DetailDto;
import com.naver.reservation.dto.ReservationDto;
import com.naver.reservation.service.DetailService;

public class PromotionDaoTest {
	@Autowired
	DetailService detailService;
	@Autowired
	ReservationDao reservationDao;
	
	public static void main(String[] args) {
		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		ReservationDao reservationDao = ac.getBean(ReservationDao.class);
		ReservationDto reservationDto = new ReservationDto();
		
//		List<DetailDto> list = detailDao.headerDetail(1, 9);
		
//	    for(DetailDto detail : list) {
//	        System.out.println("ID: " + detail.getId());
//	        System.out.println("Category ID: " + detail.getCategory_id());
//	        System.out.println("Description: " + detail.getDescription());
//	        System.out.println("Content: " + detail.getContent());
//	        System.out.println("Event: " + detail.getEvent());
//	        System.out.println("save_file_name: "+ detail.getSave_file_name());
//	        System.out.println();
//	    }
		/**List<DetailDto> list = detailDao.commentAll(1);
		
		for(DetailDto detail : list) {
	        System.out.println("comment: " + detail.getComment());
	        System.out.println("comment: " + detail.getScore());
	        System.out.println("comment: " + detail.getReservation_email());
	        System.out.println("comment: " + detail.getReservation_date());
//	        System.out.println("설명: " + detail.getContent());
//	        System.out.println();
	    }
		List<DetailDto> list = detailDao.commentAll(1);
		 * **/
		
		ReservationDto comment = reservationDao.productInfo(1);
		
		System.out.println("comment: " + comment.getDescription());

//		
//		List<ReservationDto> comment = reservationDao.productPrice(1);
//		
//		for(ReservationDto detail : comment) {
//			System.out.println("comment: " + detail.getPrice());
//			System.out.println("comment: " + detail.getPrice_type_name());
//			System.out.println("comment: " + detail.getDiscount_rate());
//	    }
		
	}
}
