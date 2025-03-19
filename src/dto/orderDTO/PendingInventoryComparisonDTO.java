package dto.orderDTO;

public class PendingInventoryComparisonDTO {
    private String productId;
    private int inventoryQuantity;
    private int pendingOrderQuantity;

    public PendingInventoryComparisonDTO(String productId, int inventoryQuantity, int pendingOrderQuantity) {
        this.productId = productId;
        this.inventoryQuantity = inventoryQuantity;
        this.pendingOrderQuantity = pendingOrderQuantity;
    }
    public String getProductId() { return productId; }
    public int getInventoryQuantity() { return inventoryQuantity; }
    public int getPendingOrderQuantity() { return pendingOrderQuantity; }
}
