package service;

import dto.WarehouseDTO;
import repository.WarehouseRepo;

import java.util.ArrayList;

public class WarehouseServiceImpl implements WarehouseService {
    public WarehouseServiceImpl(WarehouseRepo storageRepo) {
    }

    @Override
    public boolean insertWarehouse(WarehouseDTO warehouse) {

        return false;
    }

    @Override
    public boolean updateWarehouse(WarehouseDTO warehouse) {

        return false;
    }

    @Override
    public boolean deleteWarehouse(WarehouseDTO warehouse) {

        return false;
    }

    @Override
    public ArrayList<WarehouseDTO> viewWarehouses() {

        return null;
    }

    @Override
    public ArrayList<WarehouseDTO> viewWarehousesByLocation(String Location) {

        return null;
    }
}
