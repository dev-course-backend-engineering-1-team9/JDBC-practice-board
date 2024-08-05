package member;

public interface MemberService {

    Long join(String email, String password, String nickname);

    Long login(String email, String password);

}
