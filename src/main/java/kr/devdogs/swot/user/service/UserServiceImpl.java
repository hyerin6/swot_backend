package kr.devdogs.swot.user.service;

import kr.devdogs.swot.user.dto.User;
import kr.devdogs.swot.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired UserMapper userMapper;

    // myinfo
    @Override
    public User getUser(String uid){
        return userMapper.getUser(uid);
    }

    // withdraw
    @Override
    public boolean withdraw(String uid){
        int result = userMapper.withdraw(uid);
        if(result == 1) return true;
        else return false;
    }

    // modifyMyinfo - 이름, 상태 메시지, 학번만 변경할 수 있다.
    @Override
    public User modifyMyinfo(User user){
        int result = userMapper.userInfoUpdate(user);
        if(result == 1) {
            User modifyUser = userMapper.getUser(user.getUid());
            return modifyUser;
        }
        else return null;
    }

}
