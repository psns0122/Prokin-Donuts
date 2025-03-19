package dto.memberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberDTO {
    public MemberDTO() {
    }

    /** 회원번호 */
    private int memberNo;
    /** 권한 아이디 */
    private int authorityId;
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
    /** 로그인 상태 */
    private String logstatus;

    @Override
    public String toString() {
        return
                "회원번호 : " + memberNo +
                ", 권한ID : " + authorityId +
                ", 이름 : " + name  +
                ", 전화번호 : " + phoneNumber  +
                ", email : " + email  +
                ", 주소 : " + address +
                ", ID : " + id +
                ", 비밀번호 : " + password;
    }

}
