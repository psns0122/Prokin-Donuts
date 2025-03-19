package service;


import repository.InventoryRepo;
import repository.OrderRepo;
import repository.OutboundRepo;
import vo.orderVO.OrderDetailVO;
import vo.orderVO.OrderVO;
import vo.outboundVO.OutboundDetailVO;
import vo.outboundVO.OutboundVO;

import java.util.Date;
import java.util.List;

public class OutboundServiceImpl implements OutboundService {
    private final InventoryRepo inventoryRepo;
    private final OrderRepo orderRepo;
    private final OutboundRepo outboundRepo;

    // 생성자 주입: 의존성들을 외부에서 주입받아 초기화합니다.
    public OutboundServiceImpl(InventoryRepo inventoryRepo, OrderRepo orderRepo, OutboundRepo outboundRepo) {
        this.inventoryRepo = inventoryRepo;
        this.orderRepo = orderRepo;
        this.outboundRepo = outboundRepo;
    }

    @Override
    public boolean processOutbound(String orderId) {
        // 주문 조회
        OrderVO order = orderRepo.findOrderById(orderId);
        if (order == null) {
            System.out.println("발주를 찾을 수 없습니다: " + orderId);
            return false;
        }
        // 주문 상태가 '발주 승인'인지 확인
        if (!order.getOrderStatus().equals("발주 승인")) {
            System.out.println("출고 처리 불가. 현재 상태: " + order.getOrderStatus());
            return false;
        }
        // 주문 상세 내역 조회
        List<OrderDetailVO> details = orderRepo.findOrderDetailsByOrderId(orderId);
        boolean insufficient = false;
        // 각 상품별 재고 확인
        for (OrderDetailVO detail : details) {
            int available = inventoryRepo.getInventoryQuantity(detail.getProductId());
            if (available < detail.getOrderQuantity()) {
                System.out.println("재고 부족: 제품 " + detail.getProductId() + " 재고 " + available + ", 필요 " + detail.getOrderQuantity());
                insufficient = true;
            }
        }
        if (insufficient) {
            System.out.println("출고 처리 중단: 재고 부족");
            return false;
        }
        // 각 상품의 재고 업데이트
        for (OrderDetailVO detail : details) {
            int available = inventoryRepo.getInventoryQuantity(detail.getProductId());
            inventoryRepo.updateInventoryQuantity(detail.getProductId(), available - detail.getOrderQuantity());
        }
        // 주문 상태를 "출고 완료"로 업데이트
        OrderVO updated = new OrderVO(order.getOrderId(), order.getOrderDate(), "출고 완료", order.getMemberNo(), order.getAuthorityId());
        orderRepo.updateOrder(updated);
        // Outbound 정보 저장 (출고 헤더)
        OutboundVO outbound = new OutboundVO("", new Date(), "출고 완료");
        String outboundId = outboundRepo.saveOutbound(outbound);
        // 각 주문 상세에 대한 출고 상세 내역 저장
        for (OrderDetailVO detail : details) {
            OutboundDetailVO outDetail = new OutboundDetailVO(0, detail.getOrderQuantity(), detail.getProductId(), outboundId);
            outboundRepo.saveOutboundDetail(outDetail);
        }
        System.out.println("출고 완료: 주문 " + orderId);
        return true;
    }
}
