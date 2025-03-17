package dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Getter
@Builder
@AllArgsConstructor
public class InboundDTO {
    /** 입고 아이디 */
    private int inboundId;
    /** 입고일 */
    private Date inboundDate;
    /** 입고상태 */
    private String status;
    /** 창고 아이디 */
    private int warehouseId;
}
