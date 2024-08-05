package board;

import java.util.List;

public class BoardServiceImpl implements BoardService{

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
