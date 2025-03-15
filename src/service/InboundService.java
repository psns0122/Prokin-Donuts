package service;

import dto.InboundDTO;

import java.util.List;

public interface InboundService {

    //[입고 검수 Service]

    /**
     * 입고(승인) 고지서를 반환하는 service
     * findByApprovedStatus 사용
     *
     * @return 입고승인 리스트
     */
    List<InboundDTO> getInboundList();

    /**
     * 입고(승인) ok 되면 입고 완료! service
     * @param inboundId 입고 아이디
     */
    void completedInbound(int inboundId);


}
