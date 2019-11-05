package kr.devdogs.swot.application.mapper;

import kr.devdogs.swot.application.dto.Application;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApplicationMapper {
    public int create(Application application); // 예약 생성
    public int accept(int id); // 수락
    public int decline(int id); // 거절
    public List<Application> findByBoardId(int boardId); // 특정 스터디 예약 현황
    public Application findById(int id); // 특정 예약 정보 조회
    public List<Application> myAcceptStudy(int userId); // 나의 스터디 목록
    public List<Application> findByUserId(int userId); // 특정 사용자 예약 현황
    public int delete(int id);
}
