package kr.devdogs.swot.comment.controller;

import kr.devdogs.swot.comment.dto.Comment;
import kr.devdogs.swot.comment.service.CommentService;
import kr.devdogs.swot.user.service.UserService;
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
@RequestMapping(value="/api/auth/comment")
public class CommentAuthController {

    @Autowired
    CommentService commentService;

    // 특정 게시글의 댓글 전부 조회
    @RequestMapping(value = "{boardId}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByBoardId(@PathVariable("boardId") int boardId) {
        Map<String, Object> res = new HashMap<String, Object>();

        List<Comment> comments = commentService.findByBoardId(boardId);

        if(comments == null){
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        } else {
            res.put("result", "success");
            res.put("info", comments);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
