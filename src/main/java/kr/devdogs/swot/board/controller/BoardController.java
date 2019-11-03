package kr.devdogs.swot.board.controller;

import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.service.BoardService;
import kr.devdogs.swot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// 게시글 생성, 수정, 삭제, 목록 출력, 특정 게시글 읽기 (게시글은 댓글을 항상 같이 읽어옴)

@RestController
@RequestMapping(value = "/api/board")
public class BoardController {

    @Autowired
    UserService userService;
    @Autowired
    BoardService boardService;

    // 게시판 글쓰기 : 1) 공지사항, 2) 스터디, 3) Q&A
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest req, Board board) {
        Map<String, Object> res = new HashMap<String, Object>();

        if (board.getTitle() == null || board.getBody() == null) {
            res.put("result", "fail");
            res.put("error", "Title, Body is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        // 공지사항은 관리자만 글을 쓸 수 있다.
        int userId = (int) req.getAttribute("session");
        board.setUserId(userId);

        switch (board.getCode()) {
            case 1: // 공지사항
                char state = userService.findByUserId(userId).getState();
                if (state == 'M') {
                    int id = boardService.create(board);
                    Board currentBoard = boardService.findById(board.getCode(), id);
                    if (currentBoard != null) {
                        res.put("result", "success");
                        res.put("info", currentBoard);
                    } else {
                        res.put("result", "fail");
                        res.put("error", "Unknown Error");
                    }
                } else {
                    res.put("result", "fail");
                    res.put("error", "관리자가 아닙니다.");
                    return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
                }
                break;
            case 2:
            case 3: // 스터디, Q&A
                int id = boardService.create(board);
                Board currentBoard = boardService.findById(board.getCode(), id);
                if (currentBoard != null) {
                    res.put("result", "success");
                    res.put("info", currentBoard);
                } else {
                    res.put("result", "fail");
                    res.put("error", "Unknown Error");
                }
                break;
            default:
                res.put("result", "fail");
                res.put("error", "code error - 유효한 게시판 코드가 아닙니다.");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // title, body 변경
    @RequestMapping(value = "modify/{id}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> modify(HttpServletRequest req, @PathVariable("id") int id, Board board) {
        Map<String, Object> res = new HashMap<String, Object>();

        if (board.getTitle() == null || board.getBody() == null) {
            res.put("result", "fail");
            res.put("error", "Title, Body is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        int userId = (int) req.getAttribute("session");
        board.setUserId(userId);
        Board findBoard = boardService.find(id);

        if(findBoard.getUserId() != userId){
            res.put("result", "fail");
            res.put("error", "사용자에게 수정 권한이 없습니다.");
        } else {
            Board currentBoard = boardService.modify(board);
            if (currentBoard == null) {
                res.put("result", "fail");
                res.put("error", "Unknown Error");
            } else {
                res.put("info", currentBoard);
            }
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 게시글 작성자가 게시글을 삭제하면 댓글도 자동으로 삭제처리 된다.
    // 댓글 삭제 아직 구현안됨
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> delete(HttpServletRequest req, @PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();

        int userId = (int) req.getAttribute("session");
        Board board = boardService.find(id);

        switch (board.getCode()) {
            case 1: // 공지사항
                char state = userService.findByUserId(userId).getState();
                // 공지사항 게시글은 관리자만 삭제할 수 있다.
                if (state == 'M') {
                    int updatedLine = boardService.delete(id);
                    if (updatedLine == 1) {
                        res.put("result", "success");
                    } else {
                        res.put("result", "fail");
                        res.put("error", "Unknown Error");
                    }
                } else {
                    res.put("result", "fail");
                    res.put("error", "관리자가 아닙니다.");
                    return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
                }
                break;
            case 2:
            case 3: // 스터디, Q&A
                int updatedDate = boardService.delete(id);
                if (updatedDate == 1) {
                    res.put("result", "success");
                } else {
                    res.put("result", "fail");
                    res.put("error", "Unknown Error");
                }
                break;
            default:
                res.put("result", "fail");
                res.put("error", "code error - 유효한 게시판 코드가 아닙니다.");
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 특정 유저가 작성한 게시글 정보 가져오기
    @RequestMapping(value = "myBoards", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByUserId(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();

        int userId = (int) req.getAttribute("session");

        List<Board> boards = boardService.findByUserId(userId);

        if(boards == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", boards);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
