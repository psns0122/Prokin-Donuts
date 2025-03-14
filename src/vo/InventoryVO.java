package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 재고 테이블 */
public class InventoryVO {
    /** 재고 아이디 */
    private String inventoryId;
    /** 수량 */
    private int quantity;
    /** 제품 아이디 */
    private String productId;
    /** 안전재고량 */
    private int safetyInventory;
    /** 창고 아이디 */
    private String warehouseId;
}
