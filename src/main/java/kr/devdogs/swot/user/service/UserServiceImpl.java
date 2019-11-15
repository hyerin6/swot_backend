package kr.devdogs.swot.user.service;

import kr.devdogs.swot.application.mapper.ApplicationMapper;
import kr.devdogs.swot.board.service.BoardService;
import kr.devdogs.swot.comment.mapper.CommentMapper;
import kr.devdogs.swot.reservation.mapper.ReservationMapper;
import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired UserMapper userMapper;
    @Autowired ApplicationMapper applicationMapper;
    @Autowired ReservationMapper reservationMapper;
    @Autowired BoardService boardService;
    @Autowired CommentMapper commentMapper;

    // my info
    @Override
    public User findByUserId(int id){
        return userMapper.findByUserId(id);
    }

    // withdraw
    @Override
    public boolean withdraw(int id){
        int result = userMapper.withdraw(id);
        result += applicationMapper.deleteByUserId(id);
        result += reservationMapper.deleteByUserId(id);
        result += boardService.deleteByUserId(id); // 게시글 삭제 및 예약 전부 거절 처리
        result += commentMapper.deleteByUserId(id); // 댓글 삭제
        if(result >= 1) return true;
        else return false;
    }

    // 이름, 상태 메시지, 학번만 변경
    @Override
    public User modifyUserInfo(User user){
        int result = userMapper.modifyUserInfo(user);
        if(result == 1) {
            User modifyUser = userMapper.findByUserId(user.getId());
            return modifyUser;
        }
        else return null;
    }

}
