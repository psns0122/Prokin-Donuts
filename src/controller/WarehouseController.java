package controller;

public interface WarehouseController {
    /** 창고 관리 메뉴 출력 */
    void showWarehouseMenu();

    /** 창고 등록 */
    void insertWarehouse();

    /** 창고 수정 */
    void updateWarehouse();

    /** 창고 삭제 */
    void deleteWarehouse();

    /** 창고 조회 메뉴 출력 */
    void viewWarehouseMenu();

    /** 전체 창고 조회 */
    void viewWarehouses();

    /** 소재지별 창고 조회 */
    void viewWarehousesByLocation();
}
