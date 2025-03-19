package service;

import dto.orderDTO.OrderDTO;
import dto.orderDTO.OrderItemDTO;
import dto.orderDTO.OrderStatisticsDTO;
import dto.orderDTO.PendingInventoryComparisonDTO;
import repository.InventoryRepo;
import repository.InventoryRepoImpl;
import repository.OrderRepo;
import repository.OutboundRepoImpl;
import vo.orderVO.OrderDetailVO;
import vo.orderVO.OrderVO;
import vo.orderVO.OrderStatus;

import java.text.SimpleDateFormat;
import java.util.*;

public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final InventoryRepo inventoryRepo;
    private final OutboundService outboundService;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public OrderServiceImpl(OrderRepo orderRepo, InventoryRepo inventoryRepo, OutboundService outboundService) {
        this.orderRepo = orderRepo;
        this.inventoryRepo = inventoryRepo;
        this.outboundService = outboundService;
    }

    public OrderServiceImpl(OrderRepo orderRepo) {
        this(
                orderRepo,
                new InventoryRepoImpl(),
                new OutboundServiceImpl(new InventoryRepoImpl(), orderRepo, new OutboundRepoImpl())
        );
    }

    @Override
    public String submitOrder(OrderDTO dto) {
        String currentDate = sdf.format(new Date());
        String status = OrderStatus.WAITING_FOR_APPROVAL.getStatus();
        OrderVO order = new OrderVO("", currentDate, status, dto.getFranchiseId());
        String orderId = orderRepo.saveOrder(order);
        for (OrderItemDTO item : dto.getItems()) {
            OrderDetailVO detail = new OrderDetailVO(0, item.getOrderQuantity(), item.getProductId(), orderId);
            orderRepo.saveOrderDetail(detail);
        }
        return orderId;
    }

    @Override
    public void approveOrder(String orderId) {
        OrderVO order = orderRepo.findOrderById(orderId);
        if (order == null) {
            System.out.println("발주가 존재하지 않습니다: " + orderId);
            return;
        }
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_APPROVAL.getStatus())) {
            System.out.println("현재 상태에서는 승인할 수 없습니다: " + order.getOrderStatus());
            return;
        }
        OrderVO updated = new OrderVO(order.getOrderId(), order.getOrderDate(), OrderStatus.APPROVED.getStatus(), order.getMemberId());
        orderRepo.updateOrder(updated);
        System.out.println("발주 승인 완료: " + orderId);
    }

    @Override
    public void shipOrder(String orderId) {
        outboundService.processOutbound(orderId);
    }

    @Override
    public void holdOrder(String orderId, String reason) {
        OrderVO order = orderRepo.findOrderById(orderId);
        if (order == null) {
            System.out.println("발주가 존재하지 않습니다: " + orderId);
            return;
        }
        if (!order.getOrderStatus().equals(OrderStatus.APPROVED.getStatus())) {
            System.out.println("현재 상태에서는 보류할 수 없습니다: " + order.getOrderStatus());
            return;
        }
        OrderVO updated = new OrderVO(order.getOrderId(), order.getOrderDate(), OrderStatus.HOLD.getStatus(), order.getMemberId());
        orderRepo.updateOrder(updated);
        System.out.println("출고 보류 처리 완료: " + orderId);
    }

    @Override
    public OrderVO getOrder(String orderId) {
        return orderRepo.findOrderById(orderId);
    }

    @Override
    public List<OrderDetailVO> getOrderDetails(String orderId) {
        return orderRepo.findOrderDetailsByOrderId(orderId);
    }

    @Override
    public List<OrderVO> getOrdersByStatus(String status) {
        return orderRepo.findOrdersByStatus(status);
    }

    @Override
    public List<OrderVO> getOrdersByFranchiseId(String franchiseId) {
        return orderRepo.findOrdersByFranchiseId(franchiseId);
    }

    @Override
    public List<OrderVO> getOrdersByDate(String date) {
        return orderRepo.findOrdersByDate(date);
    }

    @Override
    public List<OrderVO> getOrdersByDateRange(String startDate, String endDate) {
        return orderRepo.findOrdersByDateRange(startDate, endDate);
    }

    @Override
    public OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month) {
        return orderRepo.getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
    }

    @Override
    public Map<String, PendingInventoryComparisonDTO> getPendingInventoryComparisons() {
        Map<String, Integer> pendingMap = orderRepo.getPendingOrderQuantities();
        Map<String, PendingInventoryComparisonDTO> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : pendingMap.entrySet()) {
            String productId = entry.getKey();
            int pendingQuantity = entry.getValue();
            int inventoryQty = inventoryRepo.getInventoryQuantity(productId);
            result.put(productId, new PendingInventoryComparisonDTO(productId, inventoryQty, pendingQuantity));
        }
        return result;
    }

    @Override
    public OrderStatisticsDTO getLastMonthOrderStatistics(String franchiseId) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, -1);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return
                getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
    }
    @Override
    public void cancelOrder(String orderId) {
        OrderVO order = orderRepo.findOrderById(orderId);
        if (order == null) {
            System.out.println("발주가 존재하지 않습니다: " + orderId);
            return;
        }
        if (!order.getOrderStatus().equals(OrderStatus.WAITING_FOR_APPROVAL.getStatus())) {
            System.out.println("현재 상태에서는 취소할 수 없습니다. (현재 상태: " + order.getOrderStatus() + ")");
            return;
        }
        OrderVO cancelled = new OrderVO(order.getOrderId(), order.getOrderDate(), OrderStatus.CANCELLED.getStatus(), order.getMemberId());
        orderRepo.updateOrder(cancelled);
        System.out.println("발주 취소가 완료되었습니다. 발주 ID: " + orderId);
    }
}
