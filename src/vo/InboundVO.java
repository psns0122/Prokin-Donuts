package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 입고 테이블 */
public class InboundVO {
    /** 입고 아이디 */
    private int inboundID;
    /** 입고일 */
    private String inboundDate;
    /** 창고아이디 */
    private int storageID;
    /** 제품아이디 */
    private String productID;
    /** 세션아이디 */
    private String sessionID;
    /** 권한아이디 */
    private String authorityID;
}
