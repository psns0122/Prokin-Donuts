package dto.inbound;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
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
}
