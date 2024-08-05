package member;

import common.IsDeleted;
import java.time.LocalDateTime;

public class Member {

    private Long memberId;
    private String email;
    private String password;
    private String nickname;

    private LocalDateTime createAt;
    private IsDeleted isDeleted;

    public Member(String email, String password, String nickname, LocalDateTime createAt,
                  IsDeleted isDeleted) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.createAt = createAt;
        this.isDeleted = isDeleted;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public IsDeleted getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(IsDeleted isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createAt=" + createAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
