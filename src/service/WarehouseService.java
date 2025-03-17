package service;

import dto.WarehouseDTO;

import java.util.ArrayList;

public interface WarehouseService {
    /** 창고 등록 */
    boolean insertWarehouse(WarehouseDTO warehouse);

    /** 창고 수정 */
    boolean updateWarehouse(WarehouseDTO warehouse);

    /** 창고 삭제 */
    boolean deleteWarehouse(WarehouseDTO warehouse);

    /** 전체 창고 조회 */
    ArrayList<WarehouseDTO> viewWarehouses();

    /** 소재지별 창고 조회 */
    ArrayList<WarehouseDTO> viewWarehousesByLocation(String location);
}
