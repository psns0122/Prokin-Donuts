package vo.orderVO;

public class OrderVO {
    private String orderId;
    private String orderDate;
    private String orderStatus;
    private String memberId;

    public OrderVO(String orderId, String orderDate, String orderStatus, String memberId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.memberId = memberId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getMemberId() {
        return memberId;
    }
}
