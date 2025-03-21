package repository;

import dto.InventoryDTO;
import dto.franchise.FranchiseDTO;
import dto.franchise.ProductDTO;
import dto.warehouse.WarehouseDTO;
import vo.InventoryVO;
import vo.warehouse.WarehouseVO;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepo {

    /**
     * [창고 등록 기능]
     * 가맹점 테이블에 신규 창고를 등록
     */
    Optional<String> insertWarehouse(WarehouseDTO warehouse);

    /**
     * [창고 수정 기능]
     * 본사관리자는 창고의 정보를 수정할 수 있다
     * 수정하려는 창고가 없을 경우 Optional 처리
     */
    Optional<String> updateWarehouse(WarehouseDTO warehouse);

    /**
     * [창고 삭제 기능]
     * 본사관리자는 가맹점 아이디로 가맹점을 삭제
     * 존재하지 않는 아이디일 경우 Optional 처리
     */
    Optional<String> deleteWarehouse(int warehouseId);

    /**c
     */
    Optional<List<WarehouseDTO>> showAllWarehouse();

    /**
     * [소재지별 조회 기능]
     * 본사관리자는 소재지별 창고를 조회할 수 있다
     */
    Optional<List<WarehouseDTO>> showAllWarehouseByLocation(String location);

    /**
     * [내 창고 정보 조회 기능]
     * 창고관리자는 내 창고의 정보를 조회할 수 있다
     */
    Optional<List<WarehouseDTO>> showMyWarehouse();

    /**
     * [내 창고 재고 조회 기능]
     * 창고관리자는 내 창고의 재고를 조회할 수 있다
     */
    Optional<List<InventoryDTO>> showMyWarehouseInventory();

}
