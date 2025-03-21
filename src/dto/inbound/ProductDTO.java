package dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
public class ProductDTO {
    /** 제품 아이디 */
    private int productId;
    /** 제품명 */
    private String productName;
    /** 제품 단가 */
    private int productPrice;
    /** 카테고리 아이디 */
    private String categoryId;
    /** 보관타입 */
    private String storedType;

    @Override
    public String toString() {
        return "[" +
                "제품Id: " + productId +
                ", 제품이름: '" + productName + '\'' +
                ", 가격: " + productPrice +
                ", 보관타입: '" + storedType + '\'' +
                ']';
    }
}
