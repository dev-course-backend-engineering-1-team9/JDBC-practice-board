package service;

import model.Hotel;
import model.Reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservationManagerImpl implements ReservationManager{

    private Connection connection;

    @Override
    public int reserve(Long customerId, Long hotel_id, int charge, String checkInDate, String checkOutDate) {
        int[] checkInArr = Arrays.stream(checkInDate.split("-"))
                .mapToInt(Integer::parseInt)
                .toArray();
        LocalDateTime checkIn = LocalDateTime.of(checkInArr[0], checkInArr[1], checkInArr[2], 15, 0);
        LocalDateTime checkOut = checkIn.plusDays(1).minusHours(4);
        return 1;
    }

    @Override
    public List<Reservation> findAllReservation() {
        return null;
    }


    public List<Hotel> findAllHotel() {
        ResultSet rs = null;
        List<Hotel> hotelList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/team9_reservation", "newuser", "1234");
            String sql = "SELECT hotel_id, hotel_name, location, price_day, tel from hotel";
            rs = connection.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                long hotelId = rs.getLong("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String location = rs.getString("location");
                int price = rs.getInt("price_day");
                String tel = rs.getString("tel");

                hotelList.add(new Hotel(hotelId, hotelName, location, price, tel));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotelList;
    }

    @Override
    public List<Reservation> findByCustomer(Long customerId) {
        return null;
    }

    @Override
    public List<Reservation> findByHotel(Long hotelId) {
        return null;
    }

    @Override
    public Reservation findReservation(Long customerId, Long hotelId) {
        return null;
    }

    @Override
    public int updateReservation(Long reservationId, String checkIntDate, String checkOutDate) {
        return 0;
    }

    @Override
    public int deleteReservation(Long reservationId) {
        return 0;
    }
}
