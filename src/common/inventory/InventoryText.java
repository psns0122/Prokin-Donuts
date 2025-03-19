package common.inventory;

/*
    사용자에게 출력할 모든 text를 미리 정의하는 enum 클래스입니다.
 */
public enum InventoryText {
    MENU_HEADER("\n====================================\n[재고 관리 시스템]\n===================================="),

    SHOW_INVENTORY("=== 전체 재고 출력 ==="),

    ;

    private final String text;

    InventoryText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
