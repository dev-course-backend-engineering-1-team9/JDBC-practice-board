package model;

public class Hotel {
    private Long hotelId;
    private String hotelName;
    private String location;
    private Integer priceDay;   //숙박 비용
    private String tel;

    public Hotel(Long hotelId) {}

    public Hotel(Long hotelId, String hotelName, String location, Integer priceDay, String tel) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.location = location;
        this.priceDay = priceDay;
        this.tel = tel;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPriceDay() {
        return priceDay;
    }

    public void setPriceDay(Integer priceDay) {
        this.priceDay = priceDay;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", location='" + location + '\'' +
                ", priceDay=" + priceDay +
                ", tel='" + tel + '\'' +
                '}';
    }
}
