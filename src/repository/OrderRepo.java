package repository;

import vo.OrderVO;
import vo.OrderDetailVO;
import dto.OrderDTO;
import java.util.List;
import vo.OrderStatisticsDTO;
import java.util.Map;

public interface OrderRepo {
    String saveOrder(OrderVO order);
    void saveOrderDetail(OrderDetailVO detail);
    OrderVO findOrderById(String orderId);
    void updateOrder(OrderVO order);
    List<OrderDetailVO> findOrderDetailsByOrderId(String orderId);
    OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month);
    Map<String, Integer> getPendingOrderQuantities();
}