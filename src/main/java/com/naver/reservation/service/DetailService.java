package com.naver.reservation.service;

import java.util.List;

import com.naver.reservation.dao.ReservationDao;
import com.naver.reservation.dto.DetailDto;

public interface DetailService {
	// 이런부분이 필요할 것이라고 인터페이스에서 지정
	public DetailDto getHeaderDetail(Integer id);
	public List<DetailDto> getMainImg(Integer id);
	public List<DetailDto> getCommentAll(Integer id);
	public List<DetailDto> getDirections(Integer id);

}