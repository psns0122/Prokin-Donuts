package vo.franchise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 가맹점 테이블 */
public class FranchiseVO {
    /** 가맹점 아이디 */
    private int franchiseId;
    /** 가맹점 위치 */
    private String franchiseIdLocation;
    /** 회원번호 */
    private int managerNo;
}
