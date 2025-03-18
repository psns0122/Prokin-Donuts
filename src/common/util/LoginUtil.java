package common.util;

import dto.memberDTO.MemberDTO;
import lombok.Getter;


public class LoginUtil {
    @Getter
    private static MemberDTO loginMember;

    public static void setLoginMember(MemberDTO loginMember) {
        LoginUtil.loginMember = loginMember;
    }
}
