package comment;

import common.IsDeleted;
import java.time.LocalDateTime;

public class Comment {

    private Long commentId;
    private String content;
    private Long boardId;
    private Long memberId;
    private Long parentId;

    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    private IsDeleted isDeleted;

    public Comment(String content, Long boardId, Long memberId, Long parentId, LocalDateTime createAt, LocalDateTime modifiedAt,
                   IsDeleted isDeleted) {
        this.content = content;
        this.boardId = boardId;
        this.memberId = memberId;
        this.parentId = parentId;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
        this.isDeleted = isDeleted;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
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

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", createAt=" + createAt +
                ", modifiedAt=" + modifiedAt +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
