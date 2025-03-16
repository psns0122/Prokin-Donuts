package service;

import dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberService {   /*

    /* [회원 등록 기능]
    * repo의 add메서드 호출하여 데이터를 저장
    */
    MemberDTO addMember(MemberDTO member);

    /*
     * [회원 수정 기능]
     * repo의 loadMember 호출하여 아이디를 전달해 정보 조회 후
     * repo의 updateMember 호출하여 해당 아이디의 회원정보 update
     */
    MemberDTO updateMember(String memberNo, MemberDTO updateMember);


    /*
     * [회원 삭제 기능]
     * repo의 loadMember 호출하여 아이디를 전달해 정보 조회 후
     * repo의 deleteMember 호출하여 해당 아이디의 회원정보 update
     */
    MemberDTO deleteMember(String memberNo);


}
