package kr.devdogs.swot.manager.service;

import kr.devdogs.swot.manager.dto.Manager;
import kr.devdogs.swot.manager.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("managerService")
public class ManagerServiceImpl implements ManagerService{

    @Autowired ManagerMapper managerMapper;

    @Override
    public Manager getMyInfo(String id){
        Manager manager = managerMapper.getMyInfo(id);
        return manager;
    }
}
