package repository;

import dto.inbound.InboundDTO;
import dto.inbound.ProductDTO;
import vo.ProductVO;
import vo.inbound.InboundVO;
import vo.inbound.InboundDetailVO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InboundRepo {

    /**
     * [입고 검수 기능]
     * 입고테이블에서 입고승인 상태인 행을 가져온다.
     * null 인 경우도 생각
     */
    Optional<List<InboundVO>> findByApprovedStatus(int warehouseId);

    /**
     * [입고 검수 기능]
     * 입고테이블에서 해당 ID의 입고 상태를 입고완료 상태로 변경한다.
     * + 트리거를 통해 재고에 반영(update)
     */
    void updateCompletedStatus(int inboundId);

    /**
     * [입고 요청 기능]
     * 제품 테이블에서 모든 제품 정보를 가져온다.
     */
    Optional<List<ProductDTO>> getProductInfo();

    /**
     * [입고 요청 기능]
     * 입고 요청 정보를 입고, 입고상세 테이블에 저장한다.
     */
    void registerInboundDetailInfo(List<InboundDetailVO> inboundList);

    /**
     * [입고 요청 기능]
     * 입고 정보를 테이블에 저장한다.
     */
    void registerInboundInfo(InboundVO inboundVO);

    /**
     * [입고 수정, 삭제 기능]
     * 수정, 삭제할 입고 ID의 입고상태 정보를 가져온다.
     */
    Optional<String> getInboundStatus(int inboundId);

    /**
     * [입고 수정 기능]
     * 수정 가능 -> 입고ID, 입고상세 정보를 변경한다(update)
     */
    void updateInboundInfo(int inboundId, List<ProductVO> inboundList);

    /**
     * [입고 취소 기능]
     * 입고 ID 삭제
     */
    void deleteInboundInfo(int inboundId);

    // 총관리자(본사)

    /**
     * [입고 요청 승인]
     * (입고요청) 상태인 입고 요청서를 가져온다.
     */
    Optional<List<InboundDTO>> getInboundRequest();

    /**
     * [입고 요청 승인]
     * 승인할 입고ID를 입력하면 상태를 (요청 -> 승인) 변경
     */
    void updateInboundStatus(int inboundId);

    /**
     * [입고 고지서 출력]
     * 창고 ID를 통해 모든 입고요청서를 가져온다.
     * (입고 테이블의 모든 정보)
     */
    Optional<List<InboundDTO>> getAllInboundInfo(int warehouseId);

    /**
     * [입고 현황 조회]
     * 전체 창고 입고 현황 리스트 출력
     * 입고와 입고상세 join한 정보를 가져온다.
     * DTO 추후 변경
     */
    Optional<List<InboundDTO>> getAllInbound();

    /**
     * [입고 현황 조회]
     * 전체 창고 기간별 현황 조회
     * 입고와 입고상세 join한 정보를 가져온다.
     * DTO 추후 변경
     */

    Optional<List<InboundDTO>> getInboundByDate(Date start_date, Date end_date);


}
