import board.Board;
import board.BoardService;
import board.BoardServiceImpl;
import comment.CommentServiceImpl;
import common.IsDeleted;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import member.MemberServiceImpl;
import member.controller.MemberController;
import member.repository.MemberRepository;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A:
        while (true) {
            StringBuilder sb = new StringBuilder();
            sb.append("원하는 서비스를 선택해주세요.\n");
            sb.append("1 : 회원 서비스\n");
            sb.append("2 : 게시글 서비스\n");
            sb.append("3 : 댓글 서비스\n");
            sb.append("0 : 서비스 종료\n");
            System.out.println(sb);
            String command = br.readLine();
            switch (command) {
                case "1" -> {
                    memberSystem();
                }
                case "2" -> {
                    boardSystem(br);
                }
                case "3" -> {
                    commentSystem(br);
                }
                case "0" -> {
                    break A;
                }
            }
        }
    }

    private static void memberSystem() {
        MemberController controller = new MemberController(new MemberServiceImpl(new MemberRepository()));
        controller.loadMemberMenu();
    }

    private static void boardSystem(BufferedReader br) throws IOException {
        BoardService boardService = new BoardServiceImpl();
        A:
        while(true) {
            StringBuilder sb = new StringBuilder();
            sb.append("원하는 서비스를 선택해주세요.\n");
            sb.append("1 : 게시글 작성\n");
            sb.append("2 : 게시글 전체 조회\n");
            sb.append("3 : 게시글 개별 조회\n");
            sb.append("4 : 게시글 검색\n");
            sb.append("5 : 게시글 수정\n");
            sb.append("6 : 게시글 삭제\n");
            sb.append("0 : 서비스 종료\n");
            System.out.println(sb);
            String command = br.readLine();

            switch (command) {
                case "1" -> {
                    System.out.println("제목을 입력하세요 : ");
                    String title = br.readLine();
                    System.out.println("내용을 입력하세요 : ");
                    String content = br.readLine();
                    Long createdBoardId = boardService.writeBoard(title, content);
                    System.out.println("createdBoardId = " + createdBoardId);
                }
                case "2" -> {
                    List<Board> allBoard = boardService.findAllBoard();
                    for (Board board : allBoard) {
                        System.out.println("board = " + board);
                    }
                }
                case "3" -> {
                    System.out.println("게시글 아이디 : ");
                    Long boardId = Long.parseLong(br.readLine());
                    Board boardById = boardService.findBoardById(boardId);
                    System.out.println("boardById = " + boardById);
                }
                case "4" -> {
                    System.out.println("검색어를 입력하세요 : ");
                    String searchText = br.readLine();
                    List<Board> boards = boardService.searchBoard(searchText);
                    for (Board board : boards) {
                        System.out.println("board = " + board);
                    }
                }
                case "5" -> {
                    System.out.println("게시글 아이디 : ");
                    Long boardId = Long.parseLong(br.readLine());
                    System.out.println("제목을 입력하세요 : ");
                    String title = br.readLine();
                    System.out.println("내용을 입력하세요 : ");
                    String content = br.readLine();
                    Long updatedBoard = boardService.updateBoard(boardId, title, content);
                    System.out.println("updatedBoard = " + updatedBoard);

                }
                case "6" -> {
                    System.out.println("게시글 아이디 : ");
                    Long boardId = Long.parseLong(br.readLine());
                    Long deletedBoard = boardService.deleteBoard(boardId);
                    System.out.println("deletedBoard = " + deletedBoard);
                }
                case "0" -> {
                    break A;
                }
            }
            System.out.println();
        }
    }

    private static void commentSystem(BufferedReader br) throws IOException {
        CommentServiceImpl commentService = new CommentServiceImpl();
        A:
        while(true) {
            StringBuilder sb = new StringBuilder();
            sb.append("원하는 서비스를 선택해주세요.\n");
            sb.append("1 : 댓글 작성\n");
            sb.append("2 : 댓글 전체 조회\n");
            sb.append("3 : 댓글 수정\n");
            sb.append("4 : 댓글 삭제\n");
            sb.append("0 : 서비스 종료\n");
            System.out.println(sb);
            String command = br.readLine();

            switch (command) {
                case "1" -> {
                    System.out.println("내용을 입력하세요 : ");
                    String content = br.readLine();
                    System.out.println("게시글 아이디 : ");
                    Long boardId = Long.parseLong(br.readLine());
                    System.out.println("본 댓글 아이디 : ");
                    String parent = br.readLine();
                    Long parentId;
                    if (parent.equals("")) {
                        parentId = null;
                    } else {
                        parentId = Long.parseLong(parent);
                    }
                    commentService.writeComment(content, boardId, parentId);
                }
                case "2" -> {
                    System.out.println("게시글 아이디 : ");
                    Long boardId = Long.parseLong(br.readLine());
                    commentService.findAllByBoard(boardId);
                }
                case "3" -> {
                    System.out.println("댓글 아이디 : ");
                    Long commentId = Long.parseLong(br.readLine());
                    System.out.println("내용을 입력하세요 : ");
                    String content = br.readLine();
                    commentService.updateComment(commentId, content);
                }
                case "4" -> {
                    System.out.println("댓글 아이디 : ");
                    Long commentId = Long.parseLong(br.readLine());
                    commentService.deleteComment(commentId);
                }
                case "0" -> {
                    break A;
                }
            }
            System.out.println();
        }
    }
}