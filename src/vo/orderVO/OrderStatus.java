package vo.orderVO;

public enum OrderStatus {
    WAITING_FOR_APPROVAL("발주 승인 대기중"),
    APPROVED("발주 승인"),
    HOLD("출고 보류");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
