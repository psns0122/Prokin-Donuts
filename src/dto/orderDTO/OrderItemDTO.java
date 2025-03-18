package dto;

public class OrderItemDTO {
    private String productId;
    private int orderQuantity;

    public OrderItemDTO(String productId, int orderQuantity) {
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }

    public String getProductId() { return productId; }
    public int getOrderQuantity() { return orderQuantity; }
}