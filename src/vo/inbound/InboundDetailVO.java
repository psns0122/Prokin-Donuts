package vo.inbound;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
public class InboundDetailVO {
    /** 입고 상세 아이디 */
    private int inboundDetailId;
    /** 수량 */
    private int quantity;
    /** 입고 아이디 */
    private int inboundId;
    /** 제품 아이디 */
    private int productId;
    /** 섹션 아이디 */
    private int sectionId;

    @Override
    public String toString() {
        return "[" +
                "입고상세Id: " + inboundDetailId +
                ", 수량: " + quantity +
                ", 입고Id: " + inboundId +
                ", 상품Id: " + productId +
                ", 섹션Id: " + sectionId +
                ']';
    }
}
