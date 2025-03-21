package vo.franchise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 제품 카테고리 */
public class ProductCategoryVO {
    /** 카테고리 아이디 */
    private int categoryId;
    /** 카테고리 중분류명 */
    private String categoryMid;
    /** 카테고리 소분류명 */
    private String categorySub;
}
