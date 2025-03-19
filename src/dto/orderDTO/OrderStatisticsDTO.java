import java.util.Map;

public class OrderStatisticsDTO {
    private int totalOrderRequests;
    private Map<String, Integer> productOrderQuantities;

    public OrderStatisticsDTO(int totalOrderRequests, Map<String, Integer> productOrderQuantities) {
        this.totalOrderRequests = totalOrderRequests;
        this.productOrderQuantities = productOrderQuantities;
    }
    public int getTotalOrderRequests() { return totalOrderRequests; }
    public Map<String, Integer> getProductOrderQuantities() { return productOrderQuantities; }
}
