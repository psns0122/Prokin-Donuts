package dto.memberDTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberRequestDTO {
    public MemberRequestDTO() {
    }

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
    /** 회원 승인요청 상태 **/
    private String request;

    @Override
    public String toString() {
        return "MemberRequestDTO{" +
                "authorityId=" + authorityId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", request='" + request + '\'' +
                '}';
    }
}
