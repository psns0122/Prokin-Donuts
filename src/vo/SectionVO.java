package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 섹션 테이블 */
public class SectionVO {
    /** 섹션 아이디 */
    private String sessionId;
    /** 창고 아이디 */
    private String warehouseId;
    /** 권한 아이디 */
    private String authorityId;
    /** 적재량 */
    private int storageCapacity;
    /** 보관타입 */
    private String storedType;
}
