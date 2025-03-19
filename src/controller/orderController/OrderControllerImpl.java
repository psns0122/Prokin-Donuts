package controller.orderController;

import dto.orderDTO.OrderDTO;
import dto.orderDTO.OrderStatisticsDTO;
import dto.orderDTO.PendingInventoryComparisonDTO;
import service.orderService.OrderService;
import vo.orderVO.OrderDetailVO;
import vo.orderVO.OrderVO;
import java.util.List;
import java.util.Map;

public class OrderControllerImpl implements OrderController {
    private OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String submitOrder(OrderDTO dto) {
        return orderService.submitOrder(dto);
    }

    @Override
    public void approveOrder(String orderId) {
        orderService.approveOrder(orderId);
    }

    @Override
    public void shipOrder(String orderId) {
        orderService.shipOrder(orderId);
    }

    @Override
    public void holdOrder(String orderId, String reason) {
        orderService.holdOrder(orderId, reason);
    }

    @Override
    public OrderVO getOrder(String orderId) {
        return orderService.getOrder(orderId);
    }

    @Override
    public List<OrderDetailVO> getOrderDetails(String orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @Override
    public List<OrderVO> getOrdersByStatus(String status) {
        return orderService.getOrdersByStatus(status);
    }

    @Override
    public List<OrderVO> getOrdersByFranchiseId(String franchiseId) {
        return orderService.getOrdersByFranchiseId(franchiseId);
    }

    @Override
    public List<OrderVO> getOrdersByDate(String date) {
        return orderService.getOrdersByDate(date);
    }

    @Override
    public List<OrderVO> getOrdersByDateRange(String startDate, String endDate) {
        return orderService.getOrdersByDateRange(startDate, endDate);
    }

    @Override
    public OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month) {
        return orderService.getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
    }

    @Override
    public OrderStatisticsDTO getLastMonthOrderStatistics(String franchiseId) {
        return orderService.getLastMonthOrderStatistics(franchiseId);
    }

    @Override
    public Map<String, PendingInventoryComparisonDTO> getPendingInventoryComparisons() {
        return orderService.getPendingInventoryComparisons();
    }
}
