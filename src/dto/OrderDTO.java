package dto;

public class OrderDTO {
    private String franchiseId;
    private List<OrderItemDTO> items;

    public OrderDTO(String franchiseId, List<OrderItemDTO> items) {
        this.franchiseId = franchiseId;
        this.items = items;
    }

    public String getFranchiseId() { return franchiseId; }
    public List<OrderItemDTO> getItems() { return items; }
}