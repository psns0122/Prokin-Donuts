public interface InventoryRepo {
    int getInventoryQuantity(String productId);
    void updateInventoryQuantity(String productId, int newQuantity);
}
