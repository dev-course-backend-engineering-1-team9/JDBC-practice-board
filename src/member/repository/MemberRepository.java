package member.repository;

import common.DBUtil;
import common.IsDeleted;
import member.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MemberRepository {

    private PreparedStatement pstmt;
    private ResultSet rs;
    public int join(Connection connection, String email, String password, String nickname) throws SQLException {
        int result = 0;

        try {
            String sql = "INSERT INTO Member(email, password, nickname) VALUES (?,?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, nickname);
            result = pstmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Member테이블에서 Insert작업 실패");
        } finally {
            DBUtil.close(pstmt);
        }

        return result;
    }

    public Member login(Connection connection, String email, String password) throws SQLException {
        Member loginMember = null;
        try {
            String sql = "SELECT member_id, email, password, nickname, created_at FROM Member WHERE email = ? AND password = ? AND is_deleted = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            pstmt.setString(3, IsDeleted.N.name());
            rs = pstmt.executeQuery();

            if(rs.next()) {
                long findMemberId = rs.getLong("member_id");
                String findEmail = rs.getString("email");
                String findPassword = rs.getString("password");
                String findNickname = rs.getString("nickname");
                LocalDateTime createdAt = parseTime(rs.getString("created_at"));

                loginMember = new Member(findEmail, findPassword, findNickname, createdAt, IsDeleted.N);
                loginMember.setMemberId(findMemberId);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Member테이블에 Select작업 실패");
        } finally {
            DBUtil.close(rs, pstmt);
        }

        return loginMember;
    }

    private LocalDateTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, formatter);
    }
}
