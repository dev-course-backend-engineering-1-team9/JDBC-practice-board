package member;

import java.sql.SQLException;

public interface MemberService {

    int join(Member member) throws SQLException;

    Member login(String email, String password) throws SQLException;

}
