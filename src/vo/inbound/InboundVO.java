package vo.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
/** 입고 테이블 */
public class InboundVO {
    /** 입고 아이디 */
    private int inboundId;
    /** 입고일 */
    private LocalDate inboundDate;
    /** 입고상태 */
    private String status;
    /** 창고 아이디 */
    private int warehouseId;
}
