package dto.orderDTO;

import java.util.List;

public class OrderDTO {
    private String franchiseId; // 가맹점 ID (문자열)
    private List<OrderItemDTO> items;

    public OrderDTO(String franchiseId, List<OrderItemDTO> items) {
        this.franchiseId = franchiseId;
        this.items = items;
    }
    public String getFranchiseId() { return franchiseId; }
    public List<OrderItemDTO> getItems() { return items; }
}
