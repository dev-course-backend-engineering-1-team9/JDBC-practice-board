package model;

import java.time.LocalDateTime;

/*
reservation_id 		bigint 		auto_increment 		primary key,
	customer_id 		bigint,
	hotel_id 			bigint,
	reserved_date 		datetime,
	charge 				int,
	checkin_date 		datetime,
	checkout_date 		datetime,
	foreign key (customer_id) references customer(customer_id),
	foreign key (hotel_id) references hotel(hotel_id)
*/
public class Reservation {
    private Long reservationId;
    private Long customerId;
    private Long hotelId;
    private LocalDateTime reservedDate;
    private Integer charge;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    public Reservation() {
    }

    public Reservation(Long reservationId, Long customerId, Long hotelId,
                                LocalDateTime reservedDate, Integer charge,
                                LocalDateTime checkInDate, LocalDateTime checkOutDate) {
        this.reservationId = reservationId;
        this.customerId = customerId;
        this.hotelId = hotelId;
        this.reservedDate = reservedDate;
        this.charge = charge;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDateTime getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(LocalDateTime reservedDate) {
        this.reservedDate = reservedDate;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public LocalDateTime getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDateTime checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDateTime getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDateTime checkOutDate) {
        this.checkOutDate = checkOutDate;
    }
}
