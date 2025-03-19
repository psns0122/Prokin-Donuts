package common.inbound;

/*
    사용자에게 출력할 모든 text를 미리 정의하는 enum 클래스입니다.
 */
public enum InboundText {
    MENU_HEADER("==========[입고 관리 시스템]==========="),
    MENU_BOTTOM("====================================="),
    MENU("1. 입고 검수\n2. 입고요청\n3. 입고요청 수정\n4.입고요청 취소\n5.입고고지서 출력\n6.입고 현황"),

    COMPLETE_INBOUND("입고를 완료할 ID를 입력하세요."),
    COMPLETE_TEXT("입고가 완료되었습니다.");

    private final String text;

    InboundText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
