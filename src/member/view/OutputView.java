package member.view;

public class OutputView {

    public static void printRetry() {
        System.out.println("다시 입력해주세요");
    }
    public static void printJoinSucceed() {
        System.out.println("회원가입 성공");
    }

    public static void printLoginSucceed(String nickname) {
        System.out.println("로그인 성공");
    }

    public static void printLogoutSucceed() {
        System.out.println("로그아웃 되었습니다.");
    }
}
