package comment;

import static member.controller.MemberController.loginMember;

import common.DBUtil;
import common.IsDeleted;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService{

    @Override
    public Long writeComment(String content, Long boardId, Long parentId) {
        String sql = "INSERT INTO Comment (board_id, member_id, parent_id, content, created_at, modified_at, is_deleted) "
                + "VALUES (?, ?, ?, ?, NOW(), NOW(), 'N')";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, boardId);
            pstmt.setLong(2, loginMember.getMemberId());

            if (parentId == null) {
                pstmt.setNull(3, java.sql.Types.BIGINT);
            } else {
                pstmt.setLong(3, parentId);
            }

            pstmt.setString(4, content);

            return (long) pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public Long deleteComment(Long commentId) {
        String sql = "UPDATE Comment SET is_deleted = 'Y', modified_at = NOW() WHERE comment_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, commentId);
            long affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Comment with ID " + commentId + " has been marked as deleted.");
            } else {
                System.out.println("Comment with ID " + commentId + " not found.");
            }
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public Long updateComment(Long commentId, String content) {
        String sql = "UPDATE Comment SET content = ?, modified_at = NOW() WHERE comment_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, content);
            pstmt.setLong(2, commentId);
            long affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Comment with ID " + commentId + " has been updated.");
            } else {
                System.out.println("Comment with ID " + commentId + " not found.");
            }
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public List<Comment> findAllByBoard(Long boardId) {
        List<Comment> comments = retrieveComments(boardId);
        Map<Long, String> memberNicknames = getMemberNicknames(comments);

        printComments(comments, memberNicknames);

        return comments;
    }

    private List<Comment> retrieveComments(Long boardId) {
        String sql = "SELECT " +
                "c.comment_id, " +
                "c.board_id, " +
                "c.member_id, " +
                "c.parent_id, " +
                "c.content, " +
                "c.created_at, " +
                "c.is_deleted, " +
                "m.nickname " +
                "FROM Comment c " +
                "JOIN Member m ON c.member_id = m.member_id " +
                "WHERE c.board_id = ? and c.is_deleted = 'N' " +
                "ORDER BY " +
                "COALESCE(c.parent_id, c.comment_id), " +
                "c.parent_id IS NOT NULL, " +
                "c.created_at";

        List<Comment> comments = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, boardId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Comment comment = extractCommentFromResultSet(rs);
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    private Comment extractCommentFromResultSet(ResultSet rs) throws SQLException {
        long commentId = rs.getLong("comment_id");
        long boardId = rs.getLong("board_id");
        long memberId = rs.getLong("member_id");
        Long parentId = rs.getObject("parent_id") != null ? rs.getLong("parent_id") : null;
        String content = rs.getString("content");
        String createdAt = rs.getString("created_at");
        String isDeleted = rs.getString("is_deleted");

        return new Comment(content, boardId, memberId, parentId, toDateType(createdAt), null, IsDeleted.valueOf(isDeleted));
    }

    private Map<Long, String> getMemberNicknames(List<Comment> comments) {
        Map<Long, String> memberNicknames = new HashMap<>();
        for (Comment comment : comments) {
            long memberId = comment.getMemberId();
            if (!memberNicknames.containsKey(memberId)) {
                memberNicknames.put(memberId, findNicknameByMemberId(memberId));
            }
        }
        return memberNicknames;
    }

    private String findNicknameByMemberId(long memberId) {
        String sql = "SELECT nickname FROM Member WHERE member_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setLong(1, memberId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private LocalDateTime toDateType(String info) {
        return LocalDateTime.parse(info, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private void printComments(List<Comment> comments, Map<Long, String> memberNicknames) {
        Long lastCommentId = null;

        for (Comment comment : comments) {
            String nickname = memberNicknames.get(comment.getMemberId());
            if (comment.getParentId() == null) {
                if (lastCommentId != null) {
                    System.out.println();
                }
                System.out.println(nickname + " (ID: " + comment.getMemberId() + ") : " + comment.getContent());
                lastCommentId = comment.getCommentId();
            } else {
                System.out.println("    --> " + nickname + " (ID: " + comment.getMemberId() + ") : " + comment.getContent());
            }
        }
    }
}
