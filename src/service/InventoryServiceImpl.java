package service;

import dto.InventoryDTO;
import repository.InventoryRepo;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {
    private InventoryRepo inventoryRepo;
    public InventoryServiceImpl(InventoryRepo inventoryRepo) {
        this.inventoryRepo = inventoryRepo;
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepo.getAllInventory();
    }
}
