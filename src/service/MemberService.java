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
     * repo의 updateMember 호출하여 해당 아이디의 회원정보 update
     */
    MemberDTO updateMember(MemberDTO updateMember);


    /*
     * [회원 삭제 기능]
     * repo의 loadMember 호출하여 아이디를 전달해 정보 조회 후
     * repo의 deleteMember 호출하여 해당 아이디의 회원정보 delete
     */
    MemberDTO deleteMember(String memberNo);

    /*
     * [회원 가입 기능]
     * repo의 requestMember 호출하여 데이터를 저장
     */
    MemberDTO requestMember(MemberDTO member);

    /*
    [회원아이디 중복검사 기능]
    repo의 searchLoginfo를 호출 해당 아이디가 존재하는지 확인
     */
    MemberDTO checkId (String memberNo);

    /*
    [회원아이디 간편조회 기능]
    repo의 loadMember 호출해 해당 아이디의 아이디/이름/이메일 등 확인
     */
    MemberDTO searchSimple (String memberNo);

    /*
    [회원아이디 상세조회 기능]
    repo의 loadMember 호출해 해당 아이디의 모든 회원정보 확인
     */
    MemberDTO searchDitail (String memberNo);


    /*
    [전체 회원 조회기능]
    repo의 allLoadMember 호출해 해당 아이디의 모든 회원정보 확인
     */
    MemberDTO searchAll (String memberNo);


}
