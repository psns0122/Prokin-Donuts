package common.warehouse;

/*
    사용자에게 출력할 모든 text를 미리 정의하는 enum 클래스입니다.
 */
public enum WarehouseText {
    /** 창고관리 헤더 */
    MENU_HEADER("\n" +
            "====================================\n" +
            "[창고 관리 시스템]\n" +
            "===================================="),

    /** 창고 등록 관련 */
    HQ_INSERT_WAREHOUSE_HEADER("=== 창고 등록 ==="),
    HQ_INSERT_WAREHOUSE_NAME("창고 이름: "),
    HQ_INSERT_WAREHOUSE_LOCATION("창고 소재지: "),
    HQ_INSERT_WAREHOUSE_SIZE("창고 수용한도: "),
    HQ_INSERT_WAREHOUSE_MANAGER_ID("창고 담당관리자 ID: "),
    HQ_INSERT_WAREHOUSE("창고가 등록되었습니다."),

    /** 창고 수정 관련 */
    HQ_UPDATE_WAREHOUSE_HEADER("=== 창고 수정 ==="),
    HQ_UPDATE_WAREHOUSE_ID("수정할 창고 ID: "),
    HQ_UPDATE_WAREHOUSE_NAME("수정할 창고 이름: "),
    HQ_UPDATE_WAREHOUSE_MANAGER_NAME("창고 담당관리자 이름: "),
    HQ_UPDATE_WAREHOUSE_MANAGER_ID("창고 담당관리자 ID: "),
    HQ_UPDATE_WAREHOUSE("창고 정보가 수정되었습니다."),

    /** 창고 삭제 관련 */
    HQ_DELETE_WAREHOUSE_HEADER("=== 창고 삭제 ==="),
    HQ_DELETE_WAREHOUSE_ID("삭제할 창고 ID: "),
    HQ_DELETE_WAREHOUSE("창고가 삭제되었습니다."),

    /** 뒤로가기 */
    BACK_ACTION("이전 메뉴로 돌아갑니다."),

    /** 본사-창고관리 메인메뉴 */
    HQ_WAREHOUSE_MENU("\n[본사 관리자 > 창고 관리]\n" +
            "1. 창고 등록\n" +
            "2. 창고 수정\n" +
            "3. 창고 삭제\n" +
            "4. 창고 조회\n" +
            "5. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 본사-창고관리 > 창고조회 서브메뉴 */
    HQ_WAREHOUSE_VIEW_MENU("\n[본사 관리자 > 창고 관리 > 창고 조회]\n" +
            "1. 전체 창고 조회\n" +
            "2. 소재지별 창고 조회\n" +
            "3. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 창고 조회 관련 */
    HQ_SHOW_WAREHOUSE_BY_ALL_HEADER("=== 전체 창고 조회 ==="),
    HQ_SHOW_WAREHOUSE_BY_LOCATION_HEADER("=== 소재지별 창고 조회 ==="),
    HQ_SHOW_WAREHOUSE_BY_LOGIN_HEADER("=== 내 창고 조회 ==="),
    HQ_SHOW_INVENTORY_BY_LOGIN_HEADER("=== 내 창고 재고 조회 ==="),

    /** 본사-창고관리 > 창고조회 > 소재지별 창고 조회 서브메뉴 */
    HQ_WAREHOUSE_VIEW_LOCATION_MENU("\n[본사 관리자 > 창고 관리 > 창고 조회 > 소재지별 창고 조회]\n" +
            "1. 수도권\n" +
            "2. 비수도권\n" +
            "3. 뒤로가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 창고-창고관리 메인메뉴 */
    WAREHOUSE_MANAGE_MENU("\n[창고-창고관리]\n" +
              "1. 정보 조회\n" +
              "2. 재고 조회\n" +
              "3. 뒤로 가기\n" +
              "\n메뉴를 선택하세요: "),

    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////

    // 테이블 조회시 헤더 등


    ;

    private final String text;

    WarehouseText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
