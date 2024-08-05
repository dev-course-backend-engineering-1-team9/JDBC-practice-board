import comment.CommentServiceImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    private static void commentSystem() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        CommentServiceImpl commentService = new CommentServiceImpl();
        A:
        while(true) {
            StringBuilder sb = new StringBuilder();
            sb.append("원하는 서비스를 선택해주세요.\n");
            sb.append("1 : 댓글 작성\n");
            sb.append("2 : 댓글 전체 조회\n");
            sb.append("3 : 댓글 수정\n");
            sb.append("4 : 댓글 삭제\n");
            sb.append("5 : 서비스 종료\n");
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
                case "5" -> {
                    break A;
                }
            }
            System.out.println();
        }
    }
}