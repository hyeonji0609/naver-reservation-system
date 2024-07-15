package com.naver.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetailDto {
	// product
	private int id;
	private int category_id;
	private String description;
	private String content;
	private String event;
	
	// reservation_user_comment
	private int score;
	private String comment;
	
	// reservation_info
	private String reservation_email;
	// java의 Date 타입을 json에 맞게 변경해줄 필요가 있다.
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reservation_date;
	// private Date reservation_date;
	
	// display_info
	private String place_name;
	private String place_lot;
	private String place_street;
	private String tel;
	
	// comment image
	private String save_file_name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getReservation_email() {
		return reservation_email;
	}

	public void setReservation_email(String reservation_email) {
		this.reservation_email = reservation_email;
	}

	public Date getReservation_date() {
		return reservation_date;
	}

	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	
	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getPlace_lot() {
		return place_lot;
	}

	public void setPlace_lot(String place_lot) {
		this.place_lot = place_lot;
	}

	public String getPlace_street() {
		return place_street;
	}

	public void setPlace_street(String place_street) {
		this.place_street = place_street;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getSave_file_name() {
		return save_file_name;
	}

	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
}