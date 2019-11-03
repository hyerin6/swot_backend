package kr.devdogs.swot.board.controller;

import kr.devdogs.swot.board.dto.Board;
import kr.devdogs.swot.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth/board")
public class BoardAuthController {

    @Autowired
    BoardService boardService;

    // 전체 읽어오기 (최신순)
    @RequestMapping(value = "{code}/list", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> readAll(@PathVariable("code") int code) {
        Map<String, Object> res = new HashMap<String, Object>();

        List<Board> boards = boardService.findAll(code);

        if(boards == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", boards);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 특정 게시글 정보 읽어오기
    @RequestMapping(value = "{code}/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByCode(@PathVariable("code") int code, @PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();

        Board board = boardService.findById(code, id);

        if(board == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", board);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
