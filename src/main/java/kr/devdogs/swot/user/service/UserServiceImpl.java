package kr.devdogs.swot.user.service;

import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired UserMapper userMapper;

    // my info
    @Override
    public User findByUserId(int id){
        return userMapper.findByUserId(id);
    }

    // withdraw
    @Override
    public boolean withdraw(int id){
        int result = userMapper.withdraw(id);
        if(result == 1) return true;
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
