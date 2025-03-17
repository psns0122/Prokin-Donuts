package service;

import dto.memberDTO.MemberDTO;
import dto.memberDTO.MemberRequestDTO;
import repository.MemberRepo;

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

    @Override
    public MemberDTO searchSimple(String memberNo) {
        return null;
    }

    @Override
    public MemberDTO searchDitail(String memberNo) {
        return null;
    }

    @Override
    public MemberDTO searchAuthority(String authority) {
        return null;
    }

    @Override
    public List<MemberDTO> searchAll() {
        return null;
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
