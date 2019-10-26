package kr.devdogs.swot.board.controller;

// 게시글 생성, 수정, 삭제, 목록 출력, 특정 게시글 읽기 (게시글은 댓글을 항상 같이 읽어옴)

import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.mapper.BoardMapper;
import kr.devdogs.swot.board.service.BoardService;
import kr.devdogs.swot.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BoardController {

    @Autowired
    UserService userService;
    @Autowired
    BoardService boardService;

    // 게시판 글쓰기 : 1) 공지사항, 2) 스터디, 3) Q&A
    @RequestMapping(value="create", method= RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest req, Board board){
        Map<String, Object> res = new HashMap<String, Object>();

        // 공지사항은 관리자만 글을 쓸 수 있다.
        int userId = (int) req.getAttribute("session");
        board.setUserId(userId);

        switch(board.getCode()) {
            case 1: // 공지사항
                char state = userService.findByUserId(userId).getState();
                if(state == 'M'){
                    int id = boardService.create(board);
                    Board currentBoard = boardService.findById(id);
                    if(currentBoard != null){
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
            case 2: case 3 : // 스터디, Q&A
                int id = boardService.create(board);
                Board currentBoard = boardService.findById(id);
                if(currentBoard != null){
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

}
