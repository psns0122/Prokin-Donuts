import java.text.SimpleDateFormat;
import java.util.*;

public class OrderServiceImpl implements OrderService {
    private OrderRepo orderRepo;
    private InventoryRepo inventoryRepo;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public OrderServiceImpl() {
        this.orderRepo = new DBOrderRepoImpl();
        this.inventoryRepo = new DBInventoryRepoImpl();
    }

    @Override
    public String submitOrder(OrderDTO dto) {
        String currentDate = sdf.format(new Date());
        String status = "발주 승인 대기중";
        // 권한ID는 여기서 "FRANCHISE"로 기본 설정
        OrderVO order = new OrderVO("", currentDate, status, dto.getFranchiseId(), "FRANCHISE");
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
        if (order == null) return;
        if (!order.getOrderStatus().equals("발주 승인 대기중")) return;
        OrderVO updated = new OrderVO(order.getOrderId(), order.getOrderDate(), "발주 승인", order.getMemberNo(), order.getAuthorityId());
        orderRepo.updateOrder(updated);
    }

    @Override
    public void shipOrder(String orderId) {
        OutboundRepo outboundRepo = new DBOutboundRepoImpl();
        OutboundService outboundService = new OutboundService(inventoryRepo, orderRepo, outboundRepo);
        outboundService.processOutbound(orderId);
    }

    @Override
    public void holdOrder(String orderId, String reason) {
        OrderVO order = orderRepo.findOrderById(orderId);
        if (order == null) return;
        if (!order.getOrderStatus().equals("발주 승인")) return;
        // 새로운 테이블에는 holdReason 컬럼이 없으므로 상태만 변경합니다.
        OrderVO updated = new OrderVO(order.getOrderId(), order.getOrderDate(), "출고 보류", order.getMemberNo(), order.getAuthorityId());
        orderRepo.updateOrder(updated);
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
        return getOrderStatisticsByFranchiseAndMonth(franchiseId, year, month);
    }
}