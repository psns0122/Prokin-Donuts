package dto.orderDTO;

public class OrderItemDTO {
    private String productId;
    private int orderQuantity;

    public OrderItemDTO(String productId, int orderQuantity) {
        this.productId = productId;
        this.orderQuantity = orderQuantity;
    }
    public String getProductId() { return productId; }
    public int getOrderQuantity() { return orderQuantity; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof OrderItemDTO)) return false;
        OrderItemDTO other = (OrderItemDTO) obj;
        return this.productId.equals(other.productId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode();
    }
}
