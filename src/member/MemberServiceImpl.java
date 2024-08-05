package member;

import common.DBUtil;
import member.controller.MemberController;
import member.repository.MemberRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class MemberServiceImpl implements MemberService{

    private Connection connection;
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public int join(String email, String password, String nickname) throws SQLException {
        connection = DBUtil.getConnection();
        int  result = memberRepository.join(connection, email, password, nickname);
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
