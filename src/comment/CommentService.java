package comment;

import java.util.List;

public interface CommentService {

    Long writeComment(String content, Long boardId, Long parentId);

    Long deleteComment(Long commentId);

    Long updateComment(Long commentId, String content);

    List<Comment> findAllByBoard(Long boardId);
}
