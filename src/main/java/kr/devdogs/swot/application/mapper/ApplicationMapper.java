package kr.devdogs.swot.application.mapper;

import kr.devdogs.swot.application.dto.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationMapper {
    public int create(Application application); // 예약 생성
    public int accept(Application application); // 수락
    public int decline(Application application); // 거절
    public Application findById(int id); // 특정 예약 정보 조회
    public List<Application> readAll(); // 모든 예약 현황
    public List<Application> findByUserId(int userId); // 특정 사용자 예약 현황
}
