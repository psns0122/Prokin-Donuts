package dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SectionDTO {
    /** 섹션 아이디 */
    private int sectionId;
    /** 창고 아이디 */
    private int warehouseId;
    /** 적재량 */
    private int storageCapacity;
    /** 보관타입 */
    private String storedType;
}
