package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 가맹점 테이블 */
public class FranchiseVO {
    /** 가맹점 아이디 */
    private String franchiseID;
    /** 가맹점 위치 */
    private String franchiseAddress;
    /** 회원번호 */
    private String managerID;
    /** 권한 아이디 */
    private String authorityID;
}
