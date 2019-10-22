package kr.devdogs.swot.user.service;

import kr.devdogs.swot.user.dto.User;

public interface UserService {

    // myinfo
    public User findByUserId(int id);

    // withdraw
    public boolean withdraw(int id);

    // modifyUserInfo
    public User modifyUserInfo(User user);

}
