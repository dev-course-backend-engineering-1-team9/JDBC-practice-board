import board.Board;
import board.BoardService;
import board.BoardServiceImplByAnyeon00;
import common.IsDeleted;

public class Main {
    public static void main(String[] args) {
        BoardService boardService = new BoardServiceImplByAnyeon00();
        Board board = new Board("title", "content", null, null, IsDeleted.N);
        Long l = boardService.writeBoard(board.getTitle(), board.getContent());
        System.out.println(l);
    }
}