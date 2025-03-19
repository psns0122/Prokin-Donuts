package dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InventoryDTO {
    private int inventoryId;
    private String productId;
    private String productName;
    private int quantity;
    private int warehouseId;
}
