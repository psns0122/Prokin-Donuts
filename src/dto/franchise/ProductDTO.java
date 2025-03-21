package dto.franchise;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    /** 제품 아이디 */
    private int productId;
    /** 제품명 */
    private String productName;
    /** 제품 단가 */
    private int productPrice;
    /** 카테고리 아이디 */
    private int categoryId;
    /** 보관타입 */
    private String productType;
}
