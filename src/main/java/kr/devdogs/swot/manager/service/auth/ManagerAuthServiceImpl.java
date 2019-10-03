package kr.devdogs.swot.manager.service.auth;

import kr.devdogs.swot.manager.dto.Manager;
import kr.devdogs.swot.manager.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("managerAuthService")
public class ManagerAuthServiceImpl implements ManagerAuthService{

    @Autowired ManagerMapper managerMapper;

    @Override
    public Manager managerSignin(Manager manager){
        Manager currentManager = managerMapper.managerSignin(manager);
        return currentManager;
    }
}
