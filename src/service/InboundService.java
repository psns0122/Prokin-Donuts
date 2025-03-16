package service;

import dto.InboundDTO;
import dto.ProductDTO;

import java.util.List;

public interface InboundService {

    // [입고 검수 Service]

    /**
     * 입고(승인) 고지서를 반환하는 service
     * findByApprovedStatus 사용
     *
     * @return 입고승인 리스트
     */
    List<InboundDTO> getApprovalInboundList();

    /**
     * 입고(승인) ok 되면 입고 완료! service
     * @param inboundId 입고 아이디
     */
    void completedInbound(int inboundId);

    // [입고 요청 Service]
    /**
     * 입고 요청시 상품 Menu 정보
     */
    List<ProductDTO> getProductMenu();

    /**
     * 입고 요청 등록
     */
    void registerInbound(List<ProductDTO> inboundList);


    // [입고 요청 수정 Service]
    /**
     * 입고 요청 리스트 출력(요청, 승인 상태인 경우에만 가능)
     */
    List<InboundDTO> getInboundList();

    /**
     * 입고 요청 수정 ID 입력, 변경 데이터 update
     */
    void updateInboundInfo(int inboundId, List<ProductDTO> inboundList);

    /**
     * 입고 예정 날짜 확인 후 수정 및 취소 가능 여부 판단
     * 입고ID -> 입고 상세 테이블의 예정입고일 확인
     */
    boolean checkInboundDate(int inboundId);

    // [입고 요청 취소 Service]
    /**
     * 입고ID 입력시 입고 요청 취소
     */
    void deleteInboundInfo(int inboundId);

}
