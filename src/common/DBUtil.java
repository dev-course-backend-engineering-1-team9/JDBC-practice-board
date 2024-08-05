package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

//    private static final String URL = "jdbc:mysql://127.0.0.1:3306/workshop";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/team9_jdbcpj";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Thafi0@5ai8&6";

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
