package vo.inbound;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
