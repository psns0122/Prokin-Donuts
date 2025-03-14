package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
/** 출고 테이블 */
public class OutboundVO {
    /** 출고 아이디 */
    private String outboundId;
    /** 출고일 */
    private Date outboundDate;
    /** 제품 아이디 */
    private String productId;
    /** 섹션 아이디 */
    private String sectionId;
    /** 창고 아이디 */
    private String warehouseId;
    /** 권한 아이디 */
    private String authorityId;
    /** 출고 상태 */
    private String outboundStatus;
}
