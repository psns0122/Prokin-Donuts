package service;

import dto.InventoryDTO;
import dto.warehouse.WarehouseDTO;

import java.util.ArrayList;
import java.util.List;

public interface WarehouseService {
    /** 창고 등록 */
    String insertWarehouse(WarehouseDTO warehouse);

    /** 창고 수정 */
    String updateWarehouse(WarehouseDTO warehouse);

    /** 창고 삭제 */
    String deleteWarehouse(int warehouseId);

    /**
     * 전체 창고 조회
     */
    List<WarehouseDTO> viewWarehouses();

    /** 소재지별 창고 조회 */
    List<WarehouseDTO> viewWarehousesByLocation(String location);

    /** 내 창고 조회 */
    List<WarehouseDTO> viewMyWarehouses();


    /** 내 창고 재고 조회 */
    List<InventoryDTO> viewMyWarehousesInventory();


}
