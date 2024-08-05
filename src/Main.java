import member.MemberServiceImpl;
import member.controller.MemberController;
import member.repository.MemberRepository;

public class Main {
    public static void main(String[] args) {
        MemberController controller = new MemberController(new MemberServiceImpl(new MemberRepository()));
        controller.loadMemberMenu();
    }
}