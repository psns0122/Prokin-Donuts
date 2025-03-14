package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
/** 입고 테이블 */
public class InboundVO {
    /** 입고 아이디 */
    private String inboundId;
    /** 입고일 */
    private Date inboundDate;
    /** 창고아이디 */
    private String warehouseId;
    /** 제품아이디 */
    private String productId;
    /** 세션아이디 */
    private String sectionId;
    /** 권한아이디 */
    private String authorityId;
}
