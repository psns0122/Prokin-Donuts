package vo.warehouse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 섹션 테이블 */
public class SectionVO {
    /** 섹션 아이디 */
    private int sectionId;
    /** 창고 아이디 */
    private int warehouseId;
    /** 적재량 */
    private int storageCapacity;
    /** 보관타입 */
    private String storedType;
}
