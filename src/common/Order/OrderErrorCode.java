package common.Order;

public enum OrderErrorCode {
    ORDER_NOT_FOUND("[Error]: 발주가 존재하지 않습니다."),
    INVALID_ORDER_STATUS("[Error]: 현재 상태에서는 해당 작업을 수행할 수 없습니다.");

    private final String text;

    OrderErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
