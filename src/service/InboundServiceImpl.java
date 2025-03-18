package service;


import dto.inbound.InboundDTO;

import dto.inbound.ProductDTO;
import repository.InboundRepo;
import vo.inbound.InboundDetailVO;
import vo.inbound.InboundVO;

import java.util.Date;
import java.util.List;

public class InboundServiceImpl implements InboundService {
    private final InboundRepo inboundRepo;

    public InboundServiceImpl(InboundRepo inboundRepo) {
        this.inboundRepo = inboundRepo;
    }

    //창고 관리자

    /**
     * [입고 검수 기능]
     * 입고(승인) 상태의 입고테이블 정보를 반환
     * @return 입고(승인) 리스트
     */
    @Override
    public List<InboundVO> getApprovalInboundList(int warehouseId) {
        return inboundRepo.findByApprovedStatus(warehouseId).orElseThrow(
                () -> new IllegalArgumentException("~~")
        );

    }

    /**
     * 해당 입고 ID 상태 변경 (승인 -> 완료)
     * @param inboundId 입고 아이디
     */
    @Override
    public void completedInbound(int inboundId) {
        inboundRepo.updateCompletedStatus(inboundId);
    }

    /**
     * 입고 요청시 필요한 상품 메뉴 반환
     * @return 상품 리스트
     */
    @Override
    public List<ProductDTO> getProductMenu() {
        return inboundRepo.getProductInfo().orElseThrow(
                () -> new IllegalArgumentException("~~")
        );
    }

    /**
     * 입고 요청 등록
     * @param inboundVO
     */
    @Override
    public void registerInbound(InboundVO inboundVO) {
        inboundRepo.registerInboundInfo(inboundVO);
    }

    /**
     * 입고 요청 상세 등록
     * @param inboundList
     */
    @Override
    public void registerDetailInfo(List<InboundDetailVO> inboundList) {
        inboundRepo.registerInboundDetailInfo(inboundList);
    }

    /**
     * [입고 요청 수정]
     * 입고 요청 리스트 출력 (요청, 승인) 상태
     *
     * @return 입고 요청 리스트
     */
    @Override
    public List<InboundVO> getInboundList(int warehouseId) {
        return inboundRepo.getInboundStatus(warehouseId).orElseThrow(
                () -> new IllegalArgumentException("ee")
        );
    }

    /**
     * [입고 요청 수정]
     * 입고 요청 수정 ID 입력, 변경 데이터 update
     * -->>>>>>>> 수정 사항 존재
     * 매개변수 값 추후 수정
     * @param inboundId
     * @param inboundList
     */
    @Override
    public void updateInboundInfo(int inboundId, List<InboundDetailVO> inboundList) {
        inboundRepo.updateInboundInfo(inboundList);
    }

    /**
     * 입고 예정 날짜 확인 후 수정 및 취소 가능 여부 판단
     * 입고ID -> 입고 상세 테이블의 예정입고일 확인
     * --> 추후 개발 예정
     * @param inboundId
     * @return
     */
    @Override
    public boolean checkInboundDate(int inboundId) {
        return inboundRepo.checkInboundDate(inboundId);
    }

    /**
     * 입고ID 입력시 입고 요청 취소
     * @param inboundId
     */
    @Override
    public void deleteInboundInfo(int inboundId) {
        inboundRepo.deleteInboundInfo(inboundId);
    }

    /**
     * 입고 ID 입력시 입고 상세 정보 출력
     * 창고 ID 필요
     * @param inboundId
     * @return 입고상세정보
     */
    @Override
    public List<InboundDetailVO> getInboundDetail(int inboundId) {
        return null;
    }



    @Override
    public List<InboundDTO> getDateInboundInfo(Date start_date, Date end_date) {
        return null;
    }

    // 총관리자(본사)

    /**
     * (입고요청) 상태인 입고요청서를 가져온다.
     *
     * @return
     */
    @Override
    public List<InboundVO> getInboundRequest() {
        return inboundRepo.getInboundRequest().orElseThrow(
                () -> new IllegalArgumentException("d")
        );
    }

    /**
     * 입고 요청 승인
     * 입고상태 (요청 -> 승인) 변경
     * @param inbound
     */
    @Override
    public void updateInboundStatus(int inbound) {
        inboundRepo.updateInboundStatus(inbound);
    }

    /**
     * 총관리자 고지서 출력
     * @return 모든 창고의 입고 고지서
     */
    @Override
    public List<InboundVO> getAllInboundInfo() {
        return inboundRepo.getAllInboundInfo().orElseThrow(
                () -> new IllegalArgumentException("d")
        );
     }

    @Override
    public List<InboundDTO> getAllInbound() {
        return null;
    }

    @Override
    public List<InboundDTO> getInboundByDate(Date start_date, Date end_date) {
        return null;
    }

    @Override
    public int getNextInboundId() {
        return inboundRepo.getNextInboundId().orElseThrow(
                () -> new IllegalArgumentException("~")
        );
    }


}
