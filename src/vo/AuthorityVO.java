package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 권한 테이블 */
public class AuthorityVO {
    /** 권한아이디 */
    private String authorityID;
    /** 권한명 */
    private String authorityName;
}