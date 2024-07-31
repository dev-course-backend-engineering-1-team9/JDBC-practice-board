package service;

import model.Hotel;
import model.Reservation;

import java.util.List;

public interface ReservationManager {
    // 1) 데이터 추가
    int reserve(Long customerId, Long hotel_id, int charge, String checkInDate, String checkOutDate);

    // 2) 데이터전체목록
    List<Reservation> findAllReservation();

    // 3) 호텔 목록 전체 조회
    public List<Hotel> findAllHotel();

    // 4) 고객의 모든 예약 조회
    List<Reservation> findByCustomer(Long customerId);
    // 5) 숙소의 모든 예약 조회
    List<Reservation> findByHotel(Long hotelId);
    // 6) 특정 예약 조회
    Reservation findReservation(Long customerId,Long hotelId);

    // 7) 데이터 수정 -> 하위 메뉴로 수정항목 선택 여부
//예약날짜 변경
    int updateReservation(Long reservationId, String checkIntDate, String checkOutDate);

    // 8) 데이터 삭제
    int deleteReservation(Long reservationId);




}
