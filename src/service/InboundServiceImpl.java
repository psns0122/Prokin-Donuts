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
     * @return 입고 요청 리스트
     */
    @Override
    public List<InboundDTO> getInboundList() {
        return null;
    }

    @Override
    public void updateInboundInfo(int inboundId, List<ProductDTO> inboundList) {

    }

    @Override
    public boolean checkInboundDate(int inboundId) {
        return false;
    }

    @Override
    public void deleteInboundInfo(int inboundId) {

    }

    @Override
    public List<ProductDTO> getInboundDetail(int inboundId) {
        return null;
    }

    @Override
    public List<InboundDTO> getAllInboundInfo() {
        return null;
    }

    @Override
    public List<InboundDTO> getDateInboundInfo(Date start_date, Date end_date) {
        return null;
    }

    @Override
    public List<InboundDTO> getInboundRequest() {
        return null;
    }

    @Override
    public void updateInboundStatus(int inbound) {

    }

    @Override
    public List<InboundDTO> getAllInboundInfo(int warehouseId) {
        return null;
    }

    @Override
    public List<InboundDTO> getAllInbound() {
        return null;
    }

    @Override
    public List<InboundDTO> getInboundByDate(Date start_date, Date end_date) {
        return null;
    }


}
