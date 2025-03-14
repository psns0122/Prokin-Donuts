package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
/** 제품 테이블 */
public class ProductVO {
    /** 제품 아이디 */
    private String productId;
    /** 제품명 */
    private String productName;
    /** 제품 단가 */
    private int productPrice;
    /** 카테고리 아이디 */
    private String categoryId;
    /** 제조일 */
    private Date ManufacturingDate;
    /** 보관타입 */
    private String productType;
}
