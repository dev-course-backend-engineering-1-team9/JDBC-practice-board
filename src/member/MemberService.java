package member;

import java.sql.SQLException;

public interface MemberService {

    int join(String email, String password, String nickname) throws SQLException;

    Member login(String email, String password) throws SQLException;

}
