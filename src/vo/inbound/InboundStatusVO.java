package vo.inbound;

import lombok.Builder;

import java.time.LocalDate;
@Builder
public class InboundStatusVO {
    /** 입고 아이디 */
    private int inboundId;
    /** 제품 아이디 */
    private int productId;
    /** 창고 아이디 */
    private int warehouseId;
    /** 섹션 아이디 */
    private int sectionId;
    /** 입고일 */
    private LocalDate inboundDate;
    /** 입고상태 */
    private String status;
    /** 수량 */
    private int quantity;

    @Override
    public String toString() {
        return "[" +
                "입고Id: " + inboundId +
                ", 상품Id: " + productId +
                ", 창고Id: " + warehouseId +
                ", 섹션Id: " + sectionId +
                ", 입고날짜: " + inboundDate +
                ", 입고상태: " + status + '\'' +
                ", 수량: " + quantity +
                ']';
    }
}
