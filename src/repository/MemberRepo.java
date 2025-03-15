package repository;

import dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepo {

    /*
     * [회원 등록 기능]
     * 본사관리자가 신규 창고관리자를 등록
     */
     MemberDTO addMember(MemberDTO member);

    /*
     * [회원 수정 기능]
     * 본사관리자는 회원의 정보를 수정할 수 있다
     * 가맹점주는 본인 정보 수정
     * 수정하려는 회원이 없을 경우  Optional 처리
     */
    Optional<MemberDTO> updateMember(String memberNo, MemberDTO updatemember);


    /*
     * [회원 삭제 기능]
     * 본사관리자는 아이디로 회원정보를 삭제
     * 가맹점주는 탈퇴
     * 존재하지 않는 아이디의 경우  Optional 처리
     * 삭제된 멤버의 간단한 정보(아이디,이름 등) 리턴
     */
    Optional<MemberDTO> deleteMember(String memberNo);


    /*
     * [회원 가입 기능]
     * 가맹점주의 회원가입 요청을 요청테이블에 저장
     * 요청상태 :승인대기
     * 승인 실패의 경우 : 이미 존재하는 아이디의 경우 예외처리
     */
    boolean requestMember(MemberDTO member);


}
