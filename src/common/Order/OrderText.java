package common.Order;

public enum OrderText {
    MENU_HEADER("==========[발주 관리 시스템]=========="),
    MENU_BOTTOM("====================================="),
    MAIN_MENU("1. 점장 메뉴\n2. 본사 메뉴\n3. 창고 관리자 메뉴\n4. 종료"),

    STORE_MENU("1. 발주 요청 제출\n2. 발주 상태 확인\n3. 발주 취소\n4. 지난달 발주 통계 조회\n5. 이번달 발주 통계 조회\n6. 로그아웃"),    HEADQUARTERS_MENU("1. 미승인 발주 조회 및 승인\n2. 로그아웃"),
    WAREHOUSE_MENU("1. 발주 상세조회\n2. 대시보드 (주문 및 재고 비교)\n3. 보류 건 조회\n4. 로그아웃"),

    INPUT_FRANCHISE_ID("가맹점 아이디를 입력하세요: "),
    INPUT_PRODUCT_ID("상품 아이디를 입력하세요 (종료하려면 'save' 입력): "),
    INPUT_PRODUCT_QUANTITY("상품 발주 수량을 입력하세요: "),
    INPUT_ORDER_ID("상세 조회할 발주 ID를 입력하세요: "),
    CONFIRM_APPROVAL("이 발주를 승인하시겠습니까? (y/n): "),

    NO_ORDER("해당 가맹점의 발주 내역이 없습니다."),
    ORDER_SUBMIT_SUCCESS("발주 요청 제출 완료. 발주 ID: "),
    ORDER_APPROVE_SUCCESS("발주 승인 완료: "),
    ORDER_HOLD_SUCCESS("출고 보류 처리 완료: ");

    private final String text;

    OrderText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
