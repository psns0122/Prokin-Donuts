package service;

import dto.OrderDTO;
import dto.OrderItemDTO;
import repository.OrderRepo;
import repository.InventoryRepo;
import vo.OrderVO;
import vo.OrderDetailVO;
import vo.OrderStatisticsDTO;
import common.OrderIdGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final InventoryRepo inventoryRepo;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public OrderServiceImpl(OrderRepo orderRepo, InventoryRepo inventoryRepo) {
        this.orderRepo = orderRepo;
        this.inventoryRepo = inventoryRepo;
    }

    @Override
    public String submitOrder(OrderDTO dto) {
        String currentDate = sdf.format(new Date());
        String status = "발주 승인 대기중";
        // 주문 헤더 생성 (orderId는 빈 문자열로 생성 후 DB에서 새 주문번호를 부여)
        OrderVO order = new OrderVO("", currentDate, status, dto.getFranchiseId(), "FRANCHISE");
        // DBOrderRepoImpl 내부에서 OrderIdGenerator를 이용해 주문번호 생성
        String orderId = orderRepo.saveOrder(order);
        for (OrderItemDTO item : dto.getItems()) {
            OrderDetailVO detail = new OrderDetailVO(0, item.getOrderQuantity(), item.getProductId(), orderId);
            orderRepo.saveOrderDetail(detail);
        }
        return orderId;
    }
}