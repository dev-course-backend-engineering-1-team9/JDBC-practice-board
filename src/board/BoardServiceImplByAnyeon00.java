package board;

import common.DBUtil;
import common.IsDeleted;

import java.sql.*;
import java.util.List;

public class BoardServiceImplByAnyeon00 implements BoardService{
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    @Override
    public Long writeBoard(String title, String content) {
//        Board board = new Board(title, content, null, null, IsDeleted.N);
        conn = DBUtil.getConnection();
        Long writerId = null;
        writerId = 1L;
        String SQL = "INSERT INTO board(member_id, title, content) VALUES (?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setLong(1, writerId);
            pstmt.setString(2, title);
            pstmt.setString(3, content);
            pstmt.executeUpdate();
            System.out.println("BoardServiceImplByAnyeon00.writeBoard");
        } catch (SQLException e) {
            e.printStackTrace();
            return (long) -1;
        }
        return 1L;
    }

    @Override
    public List<Board> findAllBoard() {
        return null;
    }

    @Override
    public Board findBoardById(Long boardId) {
        return null;
    }

    @Override
    public Long updateBoard(Long boardId, String title, String content) {
        return null;
    }

    @Override
    public Long deleteBoard(Long boardId) {
        return null;
    }

    @Override
    public List<Board> searchBoard(String searchText) {
        return null;
    }
}
