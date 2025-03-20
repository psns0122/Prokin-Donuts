package service;

import dto.InventoryDTO;
import dto.memberDTO.MemberDTO;
import dto.warehouse.WarehouseDTO;
import repository.WarehouseRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepo warehouseRepo;

    public WarehouseServiceImpl(WarehouseRepo warehouseRepo) {
        this.warehouseRepo = warehouseRepo;
    }

    @Override
    public String insertWarehouse(WarehouseDTO warehouse) {
        Optional<String> result = this.warehouseRepo.insertWarehouse(warehouse);
        return result.orElse("[Service] Insert Warehouse Failed");
    }

    @Override
    public String updateWarehouse(WarehouseDTO warehouse) {
        Optional<String> result = this.warehouseRepo.updateWarehouse(warehouse);
        return result.orElse("[Service] update Warehouse Failed");
    }

    @Override
    public String deleteWarehouse(int warehouseId) {
        Optional<String> result = this.warehouseRepo.deleteWarehouse(warehouseId);
        return result.orElse("[Service] delete Warehouse Failed");
    }

    @Override
    public List<WarehouseDTO> viewWarehouses() {
        Optional<List<WarehouseDTO>> result = warehouseRepo.showAllWarehouse();
        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<WarehouseDTO> viewWarehousesByLocation(String Location) {
        Optional<List<WarehouseDTO>> result = warehouseRepo.showAllWarehouseByLocation(Location);
        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<WarehouseDTO> viewMyWarehouses() {
        Optional<List<WarehouseDTO>> result = warehouseRepo.showMyWarehouse();
        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<InventoryDTO> viewMyWarehousesInventory() {
        Optional<List<InventoryDTO>> result = warehouseRepo.showMyWarehouseInventory();
        return result.orElse(Collections.emptyList());
    }
}
