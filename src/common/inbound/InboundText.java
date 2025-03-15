package common.inbound;

/*
    사용자에게 출력할 모든 text를 미리 정의하는 enum 클래스입니다.
 */
public enum InboundText {
    MENU_HEADER("\n====================================\n[입고 관리 시스템]\n====================================");

    private final String text;

    InboundText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
