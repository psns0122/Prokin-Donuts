package service;

import dto.memberDTO.MemberDTO;
import repository.MemberRepo;
import vo.memberVO.MemberVO;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    MemberRepo memberRepo;

    public MemberServiceImpl(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

    //회원 등록 기능
    @Override
    public MemberVO addMember(MemberVO member) {
        return null;
    }

    @Override
    public MemberVO updateMember(MemberVO updateMember) {
        return null;
    }

    @Override
    public MemberVO deleteMember(String memberNo) {
        return null;
    }

    @Override
    public MemberVO requestMember(MemberVO member) {
        return null;
    }

    @Override
    public MemberVO checkId(String memberNo) {
        return null;
    }

    @Override
    public MemberVO searchSimple(String memberNo) {
        return null;
    }

    @Override
    public MemberVO searchDitail(String memberNo) {
        return null;
    }

    @Override
    public MemberVO searchAuthority(String authority) {
        return null;
    }

    @Override
    public List<MemberVO> searchAll() {
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
    public MemberVO approvalMember(String memberNo) {
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
    public List<MemberVO> searchRequestMember() {
        return null;
    }
}
