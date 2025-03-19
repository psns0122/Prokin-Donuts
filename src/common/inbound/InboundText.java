package common.inbound;

/*
    사용자에게 출력할 모든 text를 미리 정의하는 enum 클래스입니다.
 */
public enum InboundText {
    WM_MENU_HEADER("=====[창고 관리자의 입고 관리 시스템]====="),
    HQ_MENU_HEADER("=====[총관리자의 입고 관리 시스템]====="),
    MENU_BOTTOM("====================================="),
    MENU("1. 입고 검수\n2. 입고요청\n3. 입고요청 수정\n4. 입고요청 취소\n5. 입고고지서 출력\n6. 입고 현황"),
    MENU1("1. 입고요청 승인\n2. 입고 고지서 출력\n3. (전체창고) 입고현황"),
    MENU_CHOICE("메뉴 선택 (숫자 입력, 종료: exit): "),
    COMPLETE_INBOUND("입고를 완료할 ID를 입력하세요."),
    COMPLETE_TEXT("입고가 완료되었습니다."),

    ERROR_COMPLETE_TEXT("입고를 완료할 수 없습니다."),
    PRODUCT_ID("상품 ID를 입력하세요."),

    QUANTITY("수량을 입력하세요."),
    SELECT_STOP("상품 선택: 1, 종료: 0을 입력하세요."),

    INBOUND_DATE("입고예약 날짜를 입력해주세요."),

    DELETE_ID("입고를 취소할 ID를 입력하세요."),

    DELETE("입고 요청이 취소되었습니다."),

    NOT_DELETE("입고 예정일 이틀 전부터는 취소할 수 없습니다.");

    private final String text;

    InboundText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
