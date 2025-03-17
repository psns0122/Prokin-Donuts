package vo.memberVO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 회원 테이블 */
public class MemberVO {
    /** 회원번호 */
    private String memberNo;
    /** 권한 아이디 */
    private String authorityId;
    /** 회원 이름 */
    private String name;
    /** 회원 전화번호 */
    private String phoneNumber;
    /** 회원 이메일 */
    private String email;
    /** 회원 주소 */
    private String address;
    /** 회원 아이디 */
    private String id;
    /** 회원 비밀번호 */
    private String password;
}
