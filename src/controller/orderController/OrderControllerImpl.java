package controller;

import dto.OrderDTO;
import service.OrderService;
import vo.OrderVO;
import vo.OrderDetailVO;
import vo.OrderStatisticsDTO;
import java.util.List;

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
    public OrderVO getOrder(String orderId) {
        return orderService.getOrder(orderId);
    }

    @Override
    public List<OrderDetailVO> getOrderDetails(String orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @Override
    public OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month) {
        return orderService.getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
    }

    @Override
    public void approveOrder(String orderId) {
        orderService.approveOrder(orderId);
    }

}
