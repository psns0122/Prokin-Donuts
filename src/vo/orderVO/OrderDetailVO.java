package vo.orderVO;

public class OrderDetailVO {
    private int orderDetailId;
    private int orderQuantity;
    private String productId;
    private String orderId;

    public OrderDetailVO(int orderDetailId, int orderQuantity, String productId, String orderId) {
        this.orderDetailId = orderDetailId;
        this.orderQuantity = orderQuantity;
        this.productId = productId;
        this.orderId = orderId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getOrderId() {
        return orderId;
    }
}
