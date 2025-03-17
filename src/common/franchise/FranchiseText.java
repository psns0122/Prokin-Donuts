package common.franchise;

/*
    사용자에게 출력할 모든 text를 미리 정의하는 enum 클래스입니다.
 */
public enum FranchiseText {
    /** 가맹점관리 헤더 */
    MENU_HEADER("\n" +
            "====================================\n" +
            "[가맹점 관리 시스템]\n" +
            "===================================="),

    /** 뒤로가기 */
    BackAction("이전 메뉴로 돌아갑니다."),

    /** 본사-가맹점관리 메인메뉴 */
    HQ_MENU("\n[본사 관리자 메뉴]\n" +
            "1. 가맹점 관리\n" +
            "2. 제품 관리\n" +
            "3. 발주 관리\n" +
            "4. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 본사-가맹점관리 > 가맹점관리 서브메뉴 */
    HQ_FRANCHISE_MENU("\n[본사 관리자 > 가맹점 관리 메뉴]\n" +
            "1. 가맹점 등록\n" +
            "2. 가맹점 수정\n" +
            "3. 가맹점 삭제\n" +
            "4. 가맹점 전체 조회\n" +
            "5. 가맹점 상세 조회\n" +
            "6. 가맹점이 할당되지 않은 점주회원 조회\n" +
            "7. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 가맹점 등록 관련 */
    HQ_INSERT_FRANCHISE_HEADER("=== 가맹점 등록 ==="),
    HQ_INSERT_FRANCHISE_LOCATION("가맹점 위치: "),
    HQ_INSERT_FRANCHISE_MANAGER_ID("가맹점 점주 ID: "),
    HQ_INSERT_FRANCHISE("가맹점이 등록되었습니다."),

    /** 가맹점 수정 관련 */
    HQ_UPDATE_FRANCHISE_HEADER("=== 가맹점 수정 ==="),
    HQ_UPDATE_FRANCHISE_MANAGER_ID("수정할 가맹점 점주 ID: "),
    HQ_UPDATE_FRANCHISE("가맹점 정보가 수정되었습니다."),

    /** 가맹점 삭제 관련 */
    HQ_DELETE_FRANCHISE_HEADER("=== 가맹점 삭제 ==="),
    HQ_DELETE_FRANCHISE_ID("삭제할 가맹점 ID: "),
    HQ_DELETE_FRANCHISE("가맹점이 삭제되었습니다."),

    /** 창고 조회 관련 */
    HQ_SHOW_FRANCHISE_BY_ALL_HEADER("=== 전체 가맹점 조회 ==="),
    HQ_SHOW_FRANCHISE_BY_ID_HEADER("=== 가맹점 상세 조회 ==="),
    HQ_SHOW_FRANCHISE_ID("조회할 가맹점 ID: "),
    HQ_SHOW_MANAGER_BY_HAVE_NO_FRANCHISE_HEADER("=== 가맹점이 할당되지 않은 점주회원 조회 ==="),

    /** 본사-가맹점관리 > 제품관리 서브메뉴 */
    HQ_PRODUCT_MENU("\n[본사 관리자 > 제품 관리]\n" +
            "1. 제품 등록\n" +
            "2. 제품 수정\n" +
            "3. 제품 삭제\n" +
            "4. 제품 조회\n" +
            "5. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 제품 수정 관련 */
    HQ_UPDATE_PRODUCT_HEADER("=== 제품 수정 ==="),
    HQ_UPDATE_PRODUCT_MANAGER_ID("수정할 제품 ID: "),
    HQ_UPDATE_PRODUCT("제품 정보가 수정되었습니다."),

    /** 제품 삭제 관련 */
    HQ_DELETE_PRODUCT_HEADER("=== 제품 삭제 ==="),
    HQ_DELETE_PRODUCT_ID("삭제할 제품 ID: "),
    HQ_DELETE_PRODUCT("제품이 삭제되었습니다."),

    /** 본사-가맹점관리 > 제품관리 > 제품등록 서브메뉴 */
    HQ_PRODUCT_INSERT_MENU("\n[본사 관리자 > 제품 관리 > 제품 등록]\n" +
            "1. 신제품 등록\n" +
            "2. 카테고리 등록\n" +
            "3. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 제품 등록 관련 */
    HQ_INSERT_PRODUCT_HEADER("=== 신제품 등록 ==="),
    HQ_INSERT_PRODUCT_CATEGORY_NAME("신제품 소분류 카테고리: "),
    HQ_INSERT_PRODUCT_NAME("신제품 이름: "),
    HQ_INSERT_PRODUCT_PRICE("신제품 금액: "),
    HQ_INSERT_PRODUCT_TYPE("신제품 타입: "),
    HQ_INSERT_PRODUCT("신제품이 등록되었습니다."),

    HQ_INSERT_PRODUCT_CATEGORY_HEADER("=== 카테고리 등록 ==="),
    HQ_INSERT_PRODUCT_CATEGORY_MID("중분류명: "),
    HQ_INSERT_PRODUCT_CATEGORY_LAST("소분류명: "),
    HQ_INSERT_PRODUCT_CATEGORY("카테고리가 등록되었습니다."),

    /** 본사-가맹점관리 > 제품관리 > 제품조회 서브메뉴 */
    HQ_PRODUCT_VIEW_MENU("\n[본사 관리자 > 제품 관리 > 제품 조회]\n" +
            "1. 전체 제품 조회\n" +
            "2. 카테고리별 조회\n" +
            "3. 제품 아이디로 조회\n" +
            "4. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 제품 조회 관련 */
    HQ_SHOW_PRODUCT_BY_ALL_HEADER("=== 전체 제품 조회 ==="),
    HQ_SHOW_PRODUCT_BY_CATEGORY_HEADER("=== 카테고리별 조회 ==="),
    HQ_SHOW_PRODUCT_BY_ID("=== 제품 상세 조회 ==="),
    HQ_SHOW_PRODUCT_ID("조회할 제품 ID: "),

    /** 본사-가맹점관리 > 발주관리 서브메뉴 */
    HQ_ORDER_MENU("\n[본사 관리자 > 발주 관리]\n" +
            "1. 전체 발주 기록 조회\n" +
            "2. 가맹점별 발주 기록 조회\n" +
            "3. 발주 요청 승인\n" +
            "4. 발주 취소 승인\n" +
            "5. 뒤로 가기\n" +
            "\n메뉴를 선택하세요: "),

    /** 발주 조회 관련 */
    HQ_SHOW_ORDER_BY_ALL_HEADER("=== 전체 발주 기록 조회 ==="),
    HQ_SHOW_ORDER_BY_ID("=== 가맹점별 발주 기록 조회 ==="),
    HQ_SHOW_ORDER_ID("조회할 가맹점 ID: "),

    /** 발주 승인 관련 */
    HQ_SHOW_ORDER_APPROVAL_HEADER("=== 승인대기 발주 조회 ==="),
    HQ_SHOW_ORDER_CANCEL_APPROVAL_HEADER("=== 취소 승인대기 발주 조회 ==="),

    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////
    /// ////////////////////////////////////////////////////

    // 테이블 조회시 헤더 등








    ;

    private final String text;

    FranchiseText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
