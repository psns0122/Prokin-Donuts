package vo.outboundVO;

public class OutboundDetailVO {
    private int outboundDetailId;
    private int quantity;
    private int productId;
    private int outboundId;

    // 기존 레코드 조회용 생성자
    public OutboundDetailVO(int outboundDetailId, int quantity, int productId, int outboundId) {
        this.outboundDetailId = outboundDetailId;
        this.quantity = quantity;
        this.productId = productId;
        this.outboundId = outboundId;
    }

    // 신규 출고 상세 등록 시, outboundDetailId는 AUTO_INCREMENT로 처리되므로 기본값(0) 사용
    public OutboundDetailVO(int quantity, int productId, int outboundId) {
        this.outboundDetailId = 0;
        this.quantity = quantity;
        this.productId = productId;
        this.outboundId = outboundId;
    }

    public int getOutboundDetailId() {
        return outboundDetailId;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getProductId() {
        return productId;
    }
    public int getOutboundId() {
        return outboundId;
    }
}
