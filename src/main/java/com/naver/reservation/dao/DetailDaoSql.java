package com.naver.reservation.dao;

public class DetailDaoSql {
	// 상단 영역
	public static final String HEADER_DETAIL = "SELECT P.id, P.description, P.content, P.event"
	        + " FROM product P"
	        + " WHERE P.id = :id";
	
	// 메인 이미지 조회
	public static final String MAIN_IMG = "SELECT FI.save_file_name"
		    + " FROM product P"
		    + " JOIN product_image PI ON (P.id = PI.product_id)"
		    + " JOIN file_info FI ON (PI.file_id = FI.id)"
		    + " WHERE (PI.type = 'ma' OR PI.type = 'et')"
		    + " AND P.id = :id"
		    + " ORDER BY CASE WHEN PI.type = 'ma' THEN 1 ELSE 2 END, P.id DESC";
	
	// 한줄평
	public static final String COMMENT_ALL = "SELECT P.description, RUC.score, RUC.comment, RI.reservation_date,"
			+ " SUBSTRING_INDEX(RI.reservation_email, '@', 1) AS reservation_email"
			+ " FROM product P"
			+ " JOIN reservation_user_comment RUC ON (P.id = RUC.product_id)"
			+ " JOIN reservation_info RI ON (RUC.reservation_info_id= RI.id)"
			+ " WHERE P.id = :id"
	        + " ORDER BY RI.reservation_date DESC";
	
	// 오시는 길
	public static final String DIRECTIONS = "SELECT P.description, DI.place_name, DI.place_lot, DI.place_street, DI.tel"
			+ " FROM product P"
			+ " JOIN display_info DI ON (P.id = DI.product_id)"
			+ " WHERE P.id = :id";
}
