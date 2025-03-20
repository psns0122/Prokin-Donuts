package dto;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    /** 재고 아이디 */
    private int inventoryId;
    /** 수량 */
    private int quantity;
    /** 제품 아이디 */
    private String productId;
    /** 제품 이름 */
    private String productName;
    /** 안전재고량 */
    private int safetyInventory;
    /** 창고 아이디 */
    private int warehouseId;
}
