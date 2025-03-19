package vo.outboundVO;

public class OutboundDetailVO {
    private int outboundDetailId;
    private int quantity;
    private String productId;
    private String outboundId;

    public OutboundDetailVO(int outboundDetailId, int quantity, String productId, String outboundId) {
        this.outboundDetailId = outboundDetailId;
        this.quantity = quantity;
        this.productId = productId;
        this.outboundId = outboundId;
    }
    public int getOutboundDetailId() { return outboundDetailId; }
    public int getQuantity() { return quantity; }
    public String getProductId() { return productId; }
    public String getOutboundId() { return outboundId; }
}
