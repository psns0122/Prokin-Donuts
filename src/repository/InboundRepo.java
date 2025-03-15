package repository;

import dto.InboundDTO;

import java.util.List;

public interface InboundRepo {

    /**
     * [입고 검수 기능]
     * 입고테이블에서 입고승인 상태인 행을 가져온다.
     * null 인 경우도 생각
     */
    List<InboundDTO> findByApprovedStatus();

    /**
     * [입고 검수 기능]
     * 입고테이블에서 해당 ID의 입고 상태를 입고완료 상태로 변경한다.
     * + 트리거를 통해 재고에 반영(update)
     */
    void updateCompletedStatus(int inboundId);

    /**
     *
     */



}
