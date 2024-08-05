package board;

import common.DBUtil;
import common.IsDeleted;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService{

    @Override
    public Long writeBoard(String title, String content) {
        return 0L;
    }

    @Override
    public List<Board> findAllBoard() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Board> list = new ArrayList<>();
        try {
            String SQL = "select board_id,title,content,created_at,modified_at,is_deleted from board";
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()){
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDateTime createAt = rs.getTimestamp("created_at").toLocalDateTime();
                Timestamp modifiedTimestamp = rs.getTimestamp("modified_at");
                LocalDateTime modifiedAt = (modifiedTimestamp != null) ? modifiedTimestamp.toLocalDateTime() : null;
                IsDeleted isDeleted = IsDeleted.valueOf(rs.getString("is_deleted"));
                Board board = new Board(title, content, createAt, modifiedAt, isDeleted);
                Long boarId = rs.getLong("board_id");
                board.setBoard_id(boarId);
                list.add(board);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DBUtil.close(rs, ps, conn);
        }
        return list;
    }

    @Override
    public Board findBoardById(Long boardId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Board board = null;
        try {
            String SQL = "select board_id,title,content,created_at,modified_at,is_deleted from board where board_id = " + boardId;
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(SQL);
            rs = ps.executeQuery();
            if(rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
                Timestamp modifiedTimestamp = rs.getTimestamp("modified_at");
                LocalDateTime modifiedAt = (modifiedTimestamp != null) ? modifiedTimestamp.toLocalDateTime() : null;
                IsDeleted isDeleted = IsDeleted.valueOf(rs.getString("is_deleted"));
                board = new Board(title, content, createdAt, modifiedAt, isDeleted);
                board.setBoard_id(boardId);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DBUtil.close(rs, ps, conn);
        }
        return board;

    }

    @Override
    public Long updateBoard(Long boardId, String title, String content) {
        return 0L;
    }

    @Override
    public Long deleteBoard(Long boardId) {
        return 0L;
    }

    @Override
    public List<Board> searchBoard(String searchText) {
        return List.of();
    }
}
