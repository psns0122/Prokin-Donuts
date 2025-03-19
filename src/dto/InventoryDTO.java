package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    private int inventoryId;
    private String productId;
    private String productName;
    private int quantity;
    private int warehouseId;
}
