package dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
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
