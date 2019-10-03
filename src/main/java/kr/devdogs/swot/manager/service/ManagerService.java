package kr.devdogs.swot.manager.service;

import kr.devdogs.swot.manager.dto.Manager;
import org.springframework.stereotype.Service;

@Service
public interface ManagerService {
    Manager getMyInfo(String id);
}
