package repository;

import dto.InventoryDTO;

import java.util.List;

public interface InventoryRepo {
    List<InventoryDTO> getAllInventory();
    int getInventoryQuantity(String productId);
    void updateInventoryQuantity(String productId, int newQuantity);
}
