package kr.devdogs.swot.user.service;

import kr.devdogs.swot.user.dto.User;

public interface UserService {

    // myinfo
    public User getUser(String uid);

    // withdraw
    public boolean withdraw(String email);

    // modifyMyinfo
    public User modifyMyinfo(User user);

}
