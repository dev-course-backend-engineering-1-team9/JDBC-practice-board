package board;

import common.DBUtil;
import common.IsDeleted;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService{

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    @Override
    public Long writeBoard(String title, String content) {
        return 0L;
    }

    @Override
    public List<Board> findAllBoard() {
        return List.of();
    }

    @Override
    public Board findBoardById(Long boardId) {
        return null;
    }

    @Override
    public Long updateBoard(Long boardId, String title, String content) {
        int result = 0;

        try {
            StringBuilder sql = new StringBuilder("UPDATE board SET modified_at = NOW()");
            if (title != null) {
                sql.append(", title = ?");
            }
            if (content != null) {
                sql.append(", content = ?");
            }
            sql.append(" WHERE board_id = ?");

            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql.toString());

            int paramIndex = 1;
            if (title != null) {
                ps.setString(paramIndex++, title);
            }
            if (content != null) {
                ps.setString(paramIndex++, content);
            }
            ps.setLong(paramIndex, boardId);

            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL 오류입니다 ! ");
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(ps, connection);
        }

        return (long) result;
    }

    @Override
    public Long deleteBoard(Long boardId) {
        int result = 0;

        try {
            String sql = "UPDATE board SET is_deleted = ?, modified_at = NOW() WHERE board_id = ?";
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, IsDeleted.Y.name());
            ps.setLong(2, boardId);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL 오류입니다 ! ");
            throw new RuntimeException(e);
        } finally {
            DBUtil.close(ps, connection);
        }
        return (long) result;
    }

    @Override
    public List<Board> searchBoard(String searchText) {
        List<Board> searchResultBoard = new ArrayList<>();
        try {
            String sql = "SELECT title,content,created_at,modified_at,is_deleted FROM board WHERE title LIKE ? AND is_deleted = 'N'";
            connection = DBUtil.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + searchText + "%");
            resultSet = ps.executeQuery();
            while(resultSet.next()){
                searchResultBoard.add(new Board(
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("created_at").toLocalDateTime(),
                        resultSet.getTimestamp("modified_at").toLocalDateTime(),
                        IsDeleted.N
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL 오류입니다 ! ");
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            System.out.println("생성 날짜 또는 수정 날짜가 없습니다.");
            throw new RuntimeException(e);
        }
        finally {
            DBUtil.close(ps, connection);
        }
        return searchResultBoard;
    }
}
