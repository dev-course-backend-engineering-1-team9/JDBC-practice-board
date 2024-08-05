package member.controller;

import member.Member;
import member.MemberService;
import member.constant.MemberMenu;
import member.view.InputView;
import member.view.OutputView;

import java.io.IOException;
import java.sql.SQLException;

public class MemberController {

    private final MemberService memberService;
    public static Member loginMember; //로그인 멤버
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    public void loadMemberMenu() {
        while(true) {
            try {
            MemberMenu selected = InputView.printMenu();
                switch (selected) {
                    case JOIN ->  join();
                    case LOGIN -> login();
                    case LOGOUT -> logout();
                    case EXIT -> { return; }
                    default -> OutputView.printRetry();
                }
            } catch (IOException | SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void join() throws IOException, SQLException {
        Member newMember = InputView.printJoin();
        int result = memberService.join(newMember.getEmail(), newMember.getPassword(), newMember.getNickname());
        if(result != 1) {
            throw new SQLException("회원가입 실패");
        }

        OutputView.printJoinSucceed();
    }

    private void login() throws IOException, SQLException {
        if(MemberController.loginMember != null) {
            throw new SQLException("이미 로그인 하셨습니다.");
        }
        Member inputMember = InputView.printLogin();
        Member loginMember = memberService.login(inputMember.getEmail(), inputMember.getPassword());
        OutputView.printLoginSucceed(loginMember.getNickname());
    }

    private void logout() {
        if(loginMember == null) {
            OutputView.printLogoutFail();
            return;
        }
        MemberController.loginMember = null;
        OutputView.printLogoutSucceed();
    }
}
