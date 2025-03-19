package vo.orderVO;

public class OrderVO {
    private String orderId;
    private String orderDate;
    private String orderStatus;
    private String memberNo;      // 가맹점(회원번호)
    private String authorityId;   // 권한ID (예: "FRANCHISE")

    public OrderVO(String orderId, String orderDate, String orderStatus, String memberNo, String authorityId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.memberNo = memberNo;
        this.authorityId = authorityId;
    }
    // Getter 메서드들
    public String getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }
    public String getOrderStatus() { return orderStatus; }
    public String getMemberNo() { return memberNo; }
    public String getAuthorityId() { return authorityId; }
}
