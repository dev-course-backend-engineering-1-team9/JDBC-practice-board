package board;

import java.util.List;

public interface BoardService {

    Long writeBoard(String title, String content);

    List<Board> findAllBoard();

    Board findBoardById(Long boardId);

    Long updateBoard(Long boardId, String title, String content);

    Long deleteBoard(Long boardId);

    // 제목 한정 검색
    List<Board> searchBoard(String searchText);

}
