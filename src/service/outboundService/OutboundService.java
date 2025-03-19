package service;

import repository.InventoryRepo;
import repository.OrderRepo;
import repository.OutboundRepo;
import vo.OrderVO;
import vo.OrderDetailVO;
import vo.OutboundVO;
import vo.OutboundDetailVO;

import java.util.Date;
import java.util.List;

public class OutboundService {
    private final InventoryRepo inventoryRepo;
    private final OrderRepo orderRepo;
    private final OutboundRepo outboundRepo;

    public OutboundService(InventoryRepo inventoryRepo, OrderRepo orderRepo, OutboundRepo outboundRepo) {
        this.inventoryRepo = inventoryRepo;
        this.orderRepo = orderRepo;
        this.outboundRepo = outboundRepo;
    }

    public boolean processOutbound(String orderId) {
        OrderVO order = orderRepo.findOrderById(orderId);
        if (order == null) {
            System.out.println("발주를 찾을 수 없습니다: " + orderId);
            return false;
        }
        if (!order.getOrderStatus().equals("발주 승인")) {
            System.out.println("출고 처리 불가. 현재 상태: " + order.getOrderStatus());
            return false;
        }
        List<OrderDetailVO> details = orderRepo.findOrderDetailsByOrderId(orderId);
        boolean insufficient = false;
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
        for (OrderDetailVO detail : details) {
            int available = inventoryRepo.getInventoryQuantity(detail.getProductId());
            inventoryRepo.updateInventoryQuantity(detail.getProductId(), available - detail.getOrderQuantity());
        }
        OrderVO updated = new OrderVO(order.getOrderId(), order.getOrderDate(), "출고 완료", order.getMemberNo(), order.getAuthorityId());
        orderRepo.updateOrder(updated);
        OutboundVO outbound = new OutboundVO("", new Date(), "출고 완료");
        String outboundId = outboundRepo.saveOutbound(outbound);
        for (OrderDetailVO detail : details) {
            OutboundDetailVO outDetail = new OutboundDetailVO(0, detail.getOrderQuantity(), detail.getProductId(), outboundId);
            outboundRepo.saveOutboundDetail(outDetail);
        }
        System.out.println("출고 완료: 주문 " + orderId);
        return true;
    }
}
