package common.util;

import dto.memberDTO.MemberDTO;
import lombok.Getter;


public class LoginUtil {
    @Getter
    private static MemberDTO loginMember = new MemberDTO(1, 1, "", "", "", "" , "", "", "");
    //private static MemberDTO loginMember = new MemberDTO();

    public static void setLoginMember(MemberDTO loginMember) {
        LoginUtil.loginMember = loginMember;
    }
}
