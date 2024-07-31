import model.Hotel;
import model.Reservation;
import service.ReservationManager;
import service.ReservationManagerImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

public class Main{
    public static void main(String[] args) throws IOException {

        ReservationManager mgr = new ReservationManagerImpl();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder serviceMenu = new StringBuilder();
        serviceMenu.append("원하는 서비스를 선택해주세요.\n");
        serviceMenu.append("1 : 숙소 예약\n");
        serviceMenu.append("2 : 고객의 예약 조회\n");
        serviceMenu.append("3 : 숙소의 예약 조회\n");
        serviceMenu.append("4 : 예약 날짜 변경\n");
        serviceMenu.append("5. 예약 취소\n");
        System.out.println(serviceMenu);
        String command = br.readLine();

        //1. 예약하기 - 준호
        if(command.equals("1")) {
            System.out.print("회원번호 입력: ");
            Long customerId = Long.parseLong(br.readLine());

            //호텔 목록 가져와서 출력하기
            List<Hotel> hotelList = mgr.findAllHotel();
            System.out.print("호텔 목록(가격은 1박 기준입니다.) ");
            for(Hotel hotel : hotelList) {
                System.out.println(hotel);
            }

            System.out.print("숙박할 호텔번호 입력: ");
            int hotelId = Integer.parseInt(br.readLine());
            Hotel selectHotel = hotelList.get(hotelId-1);

            System.out.print("지불할 요금을 입력하세요: ");
            int price = Integer.parseInt(br.readLine());
            if(price < selectHotel.getPriceDay()) {
                throw new RuntimeException("지불한 금액이 숙박 금액보다 적습니다.");
            }

            System.out.print("체크인 날짜를 입력하세요(형식: 년도-월-일 순): ");
            String checkInDate = br.readLine();

            System.out.print("체크아웃 날짜를 입력하세요(형식: 년도-월-일 순): ");
            String checkOutDate = br.readLine();
            //호텔 예약 메서드 호출(예먁 일자 목록 로직 메서드 내 작성 필요)
            mgr.reserve(customerId, (long) hotelId, price, checkInDate, checkOutDate);
        }

        //2. 고객ID로 검색 - 조믿음
        if (command.equals("2")) {
            System.out.print("예약한 고객 ID를 입력하세요>> ");
            Long customerId = Long.parseLong(br.readLine());
            List<Reservation> reservationList = mgr.findByCustomer(customerId);
            for(Reservation reservation:reservationList){
                // Reservation 클래스에 toString() 오버라이드
                System.out.println(reservation.toString());
            }
        }


        //3. 숙소ID로 검색 - 나민혁
        if(command.equals("3")) {
            System.out.println("예약한 숙소 ID를 입력하세요.");
            Long hotelId = Long.parseLong(br.readLine());
            List<Reservation> searchHotelIdResult = mgr.findByHotel(hotelId);
            for(Reservation reservation : searchHotelIdResult) {
                System.out.println(reservation.toString());
            }
        }

        //4. 예약날짜 변경(checkIn날짜, checkout날짜) 이형석
        if(command.equals("4")) {
            System.out.print("예약한 고객의 ID를 입력해주세요 >>");
            Long customerId = Long.parseLong(br.readLine());
            System.out.print("예약한 숙소의 ID를 입력해주세요 >>");
            Long hotelId = Long.parseLong(br.readLine());
            Long reservationId = mgr.findReservation(customerId, hotelId).getReservationId();
            System.out.print("체크인 날짜를 입력해주세요 >>");
            String checkInDate = br.readLine();
            System.out.print("체크아웃 날짜를 입력해주세요 >>");
            String checkOutDate = br.readLine();
            int updated = mgr.updateReservation(reservationId, checkInDate, checkOutDate);
            if(updated == 1){
                System.out.println("예약을 변경하였습니다.");
            }else{
                System.out.println("예약 변경에 실패하였습니다.");
            }
        }
        //5. 예약 취소 - 안
        if (command.equals("5")) {
            Long deleteId = Long.parseLong(br.readLine());
            int deletedReservationId = mgr.deleteReservation(deleteId);
            System.out.println("삭제 완료 : " + deletedReservationId);
        }
    }
}