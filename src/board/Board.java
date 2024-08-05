package board;

import common.IsDeleted;
import java.time.LocalDateTime;

public class Board {

    private Long board_id;
    private String title;
    private String content;
    private Long member_id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    private IsDeleted isDeleted;

    public Board(String title, String content, LocalDateTime createAt, LocalDateTime modifiedAt,
                 IsDeleted isDeleted) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
    }
    public Board(Long board_id, String title, String content, LocalDateTime createAt, LocalDateTime modifiedAt,
                 IsDeleted isDeleted) {
        this.board_id = board_id;
        this.title = title;
        this.content = content;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
    }

    public Long getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Long board_id) {
        this.board_id = board_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public IsDeleted getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(IsDeleted isDeleted) {
        this.isDeleted = isDeleted;
    }
    public Long getMember_id() {return member_id;}

    public void setMember_id(Long member_id) { this.member_id = member_id; }

    @Override
    public String toString() {
        return "Board{" +
                "board_id=" + board_id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", modifiedAt=" + modifiedAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
