import java.util.List;
import java.util.Map;

public interface OrderRepo {
    String saveOrder(OrderVO order);
    void saveOrderDetail(OrderDetailVO detail);
    OrderVO findOrderById(String orderId);
    void updateOrder(OrderVO order);
    List<OrderDetailVO> findOrderDetailsByOrderId(String orderId);
    List<OrderVO> findOrdersByStatus(String status);
    List<OrderVO> findOrdersByFranchiseId(String franchiseId);
    List<OrderVO> findOrdersByDate(String date);
    List<OrderVO> findOrdersByDateRange(String startDate, String endDate);
    OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month);
    Map<String, Integer> getPendingOrderQuantities();
}
