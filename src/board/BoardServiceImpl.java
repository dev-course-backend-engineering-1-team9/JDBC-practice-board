package board;

import static member.controller.MemberController.loginMember;

import common.DBUtil;
import common.IsDeleted;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import common.DBUtil;
import common.IsDeleted;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService{

    private Connection connection;
    private PreparedStatement ps;
    private ResultSet resultSet;

    @Override
    public Long writeBoard(String title, String content) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        conn = DBUtil.getConnection();
        Long writerId = loginMember.getMemberId();  //작성자 member_id 찾아오는 메서드로 변경 필요
        String SQL = "INSERT INTO board(member_id, title, content) VALUES (?, ?, ?)";
        try {
            ps = conn.prepareStatement(SQL);
            ps.setLong(1, writerId);
            ps.setString(2, title);
            ps.setString(3, content);
            return (long) ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return (long) -1;
        }
    }

    @Override
    public List<Board> findAllBoard() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Board> list = new ArrayList<>();
        try {
            String SQL = "select board_id,title,content,created_at,modified_at,is_deleted from board b where b.is_deleted = 'N'";
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
