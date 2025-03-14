package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 제품 카테고리 */
public class ProductCategoryVO {
    /** 카테고리 아이디 */
    private String categoryId;
    /** 카테고리 명 */
    private String categoryName;
}
