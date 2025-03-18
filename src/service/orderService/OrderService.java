package service;

import dto.OrderDTO;
import vo.OrderVO;
import vo.OrderDetailVO;
import vo.OrderStatisticsDTO;
import java.util.List;
import java.util.Map;

public interface OrderService {
    String submitOrder(OrderDTO dto);
    OrderVO getOrder(String orderId);
    List<OrderDetailVO> getOrderDetails(String orderId);
    OrderStatisticsDTO getOrderStatisticsByFranchiseAndMonth(String franchiseId, int year, int month);

    void approveOrder(String orderId);
    void shipOrder(String orderId);



}
