package service;

import dto.memberDTO.MemberDTO;
import dto.memberDTO.MemberRequestDTO;
import repository.MemberRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MemberServiceImpl implements MemberService {
    MemberRepo memberRepo;

    public MemberServiceImpl(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    //회원 등록 기능
    @Override
    public MemberDTO addMember(MemberDTO member) {
        //insertMember 반환된 객체를 result 에 저장
        Optional<MemberDTO> result = memberRepo.insertMember(member);
        //결과값을 반환, 하지만 결과값이 optional.empty면 null 반환
        return result.orElse(null);
    }

    //회원 수정 기능
    @Override
    public MemberDTO updateMember(MemberDTO updateMember) {
        Optional<MemberDTO> result = memberRepo.updateMember(updateMember);
        return result.orElse(null);
    }

    //회원 삭제 기능
    @Override
    public String deleteMember(String memberId) {
        Optional<String> result = memberRepo.deleteMember(memberId);
        return result.orElse(null);
    }

    //회원 가입 기능
    @Override
    public MemberRequestDTO requestMember(MemberRequestDTO member) {
        Optional<MemberRequestDTO> result = memberRepo.requestMember(member);
        return result.orElse(null);
    }

    //회원아이디 중복검사 기능
    @Override
    public String checkId(String memberId) {
        Optional<String> result = memberRepo.searchLoginfo("memberNo","id",memberId);
        return result.isPresent() ? "존재하는 아이디" : "존재하지 않는 아이디";
    }

    //회원아이디 간편조회 기능
    @Override
    public MemberDTO searchSimple(String memberId) {
        Optional<List<MemberDTO>> result = memberRepo.loadMember("id",memberId);
        return result.filter(list -> !list.isEmpty())
                .map(list -> list.get(0))  // 첫 번째 객체를 꺼냄
                .orElse(null);
    }

    //회원아이디 상세조회 기능
    @Override
    public MemberDTO searchDitail(String memberId) {
        Optional<List<MemberDTO>> result = memberRepo.loadMember("id",memberId);
        return result.filter(list -> !list.isEmpty())
                .map(list -> list.get(0))
                .orElse(null);
    }

    //권한별 조회 기능
    @Override
    public List<MemberDTO> searchAuthority(String authority) {
        int authorityId = 0;

        if (authority.equals("총관리자")) authorityId = 1;
        else if (authority.equals("창고관리자"))authorityId = 2;
        else authorityId = 3;

        Optional<List<MemberDTO>> result = memberRepo.loadMember("authorityId", authorityId);
        return result.orElse(Collections.emptyList());
    }

    //전체 회원 조회기능
    @Override
    public List<MemberDTO> searchAll() {
        Optional<List<MemberDTO>> result = memberRepo.allLoadMember();
        return result.orElse(Collections.emptyList());
    }

    @Override
    public String findId(String memberEmail) {
        return null;
    }

    @Override
    public String findPassword(String memberNo) {
        return null;
    }

    @Override
    public String randomNumber(String memberEmail) {
        return null;
    }

    @Override
    public boolean checkRandomNumber(String randomNumber) {
        return false;
    }

    @Override
    public MemberDTO approvalMember(String memberNo) {
        return null;
    }

    @Override
    public String logIn(String memberid) {
        return null;
    }

    @Override
    public String logOut(String memberid) {
        return null;
    }

    @Override
    public List<MemberDTO> searchRequestMember() {
        return null;
    }
}
