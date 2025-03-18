package dto.franchise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductCategoryDTO {
    /** 카테고리 아이디 */
    private int categoryId;
    /** 카테고리 중분류명 */
    private String categoryMid;
    /** 카테고리 소분류명 */
    private String categorySub;
}
