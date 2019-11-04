package kr.devdogs.swot.comment.controller;

import kr.devdogs.swot.comment.dto.Comment;
import kr.devdogs.swot.comment.service.CommentService;
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

@RestController
@RequestMapping(value="/api/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    // 댓글 생성
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> create(HttpServletRequest req, Comment comment) {
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");
        comment.setUserId(userId);

        if(comment.getBodyText() == null){
            res.put("result", "fail");
            res.put("result", "bodyText is Required");
            return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        }

        if(commentService.create(comment) == 1){
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 댓글 삭제
    @RequestMapping(value = "deleteOne/{id}", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> deleteOne(HttpServletRequest req, @PathVariable("id") int id) {
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");

        if(commentService.deleteOne(id, userId) == 1){
            res.put("result", "success");
        } else {
            res.put("result", "fail");
            res.put("error", "Unknown Error");
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 특정 사용자가 작성한 댓글 전부 조회
    @RequestMapping(value = "MyComments", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> findByUserId(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<String, Object>();
        int userId = (int) req.getAttribute("session");

        List<Comment> comments = commentService.findByUserId(userId);

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
