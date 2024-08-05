package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String URL = DBConfig.getUrl();
    private static final String USERNAME = DBConfig.getUsername();
    private static final String PASSWORD = DBConfig.getPassword();

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }catch (SQLException e) {
            System.out.println("커넥션 생성 오류");
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(AutoCloseable... closeables){
        for(AutoCloseable closeable : closeables){
            if(closeable != null){
                try {
                    closeable.close();
                } catch (Exception e) {
                    System.out.println("close 하다가 에러나는거 아직까지 못보긴 했음.");
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
