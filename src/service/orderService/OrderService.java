import java.util.List;
import java.util.Map;

public interface OrderService {
    String submitOrder(OrderDTO dto);
    void approveOrder(String orderId);
    void shipOrder(String orderId);
    void holdOrder(String orderId, String reason);
    OrderVO getOrder(String orderId);
    List<OrderDetailVO> getOrderDetails(String orderId);
    List<OrderVO> getOrdersByStatus(String status);
    List<OrderVO> getOrdersByFranchiseId(String franchiseId);
    List<OrderVO> getOrdersByDate(String date);
    List<OrderVO> getOrdersByDateRange(String startDate, String endDate);
    OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month);
    Map<String, PendingInventoryComparisonDTO> getPendingInventoryComparisons();
    OrderStatisticsDTO getLastMonthOrderStatistics(String franchiseId);
}
