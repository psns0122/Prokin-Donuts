package service;

import dto.MemberDTO;
import repository.MemberRepo;

public class MemberServiceImpl implements MemberService {
    public MemberServiceImpl(MemberRepo memberRepo) {
    }

    @Override
    public MemberDTO addMember(MemberDTO member) {
        return null;
    }

    @Override
    public MemberDTO updateMember(MemberDTO updateMember) {
        return null;
    }

    @Override
    public MemberDTO deleteMember(String memberNo) {
        return null;
    }

    @Override
    public MemberDTO requestMember(MemberDTO member) {
        return null;
    }

    @Override
    public MemberDTO checkId(String memberNo) {
        return null;
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
    public MemberDTO searchAll() {
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


}
