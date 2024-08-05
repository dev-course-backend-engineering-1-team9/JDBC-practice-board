package comment;

import java.util.List;

public class CommentServiceImpl implements CommentService{

    @Override
    public Long writeComment(String content, Long boardId, Long parentId) {
        return 0L;
    }

    @Override
    public Long deleteComment(Long commentId) {
        return 0L;
    }

    @Override
    public Long updateComment(Long commentId, String content) {
        return 0L;
    }

    @Override
    public List<Comment> findAllByBoard(Long boardId) {
        return List.of();
    }
}
