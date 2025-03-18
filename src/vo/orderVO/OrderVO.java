package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 발주 테이블 */
public class OrderVO {
    /** 발주 아이디 */
    private String orderId;
    /** 발주 수량 */
    private int orderQuantity;
    /** 제품 아이디 */
    private String productId;
    /** 발주일 */
    private String orderDate;
    /** 회원번호 */
    private String memberNo;
    /** 권한 아이디 */
    private String authorityId;

    public OrderVO(String orderId, String orderDate, String orderStatus, String memberNo, String authorityId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.memberNo = memberNo;
        this.authorityId = authorityId;
    }

    public String getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }
    public String getOrderStatus() { return orderStatus; }
    public String getMemberNo() { return memberNo; }
    public String getAuthorityId() { return authorityId; }
}
