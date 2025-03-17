package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
/** 입고 테이블 */
public class InboundVO {
    /** 입고 아이디 */
    private int inboundId;
    /** 입고일 */
    private Date inboundDate;
    /** 입고상태 */
    private String status;
    /** 창고 아이디 */
    private int warehouseId;
}
