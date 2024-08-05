package member;

import common.DBUtil;
import member.controller.MemberController;
import member.repository.MemberRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MemberServiceImpl implements MemberService{

    private Connection connection;
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public int join(Member member) throws SQLException {
        if(!(!member.getEmail().isEmpty() && !member.getPassword().isEmpty() && !member.getNickname().isEmpty())) {
            throw new RuntimeException("아이디, 비밀번호, 닉네임은 필수 입력값입니다.");
        }

        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if(!Pattern.matches(emailRegex, member.getEmail())) {
            throw new RuntimeException("입력한 아이디가 이메일 형식이 아닙니다.");
        }

        if(!member.getPassword().equals(member.getConfirmPassword())) {
            throw new RuntimeException("입력한 비밀번호가 확인용 비밀번호와 다릅니다.");
        }

        connection = DBUtil.getConnection();
        int  result = memberRepository.join(connection, member);
        DBUtil.close(connection);
        return result;
    }

    @Override
    public Member login(String email, String password) throws SQLException {

        connection = DBUtil.getConnection();
        Member loginMember = memberRepository.login(connection, email, password);
        if(loginMember == null) {
            throw new SQLException("아이디 또는 비밀번호가 틀렸습니다.");
        }
        MemberController.loginMember = loginMember;
        DBUtil.close(connection);
        return loginMember;
    }
}
