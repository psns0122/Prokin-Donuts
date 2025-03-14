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
}
