package repository;

import dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public class MemberRepoImpl implements MemberRepo {

    @Override
    public MemberDTO insertMember(MemberDTO member) {
        return null;
    }

    @Override
    public Optional<MemberDTO> updateMember(String memberId, MemberDTO updateMember) {
        return Optional.empty();
    }

    @Override
    public Optional<MemberDTO> deleteMember(String memberId) {
        return Optional.empty();
    }

    @Override
    public boolean requestMember(MemberDTO member) {
        return false;
    }

    @Override
    public Optional<MemberDTO> approvalMember(String memberId) {
        return Optional.empty();
    }

    @Override
    public <T> Optional<List<MemberDTO>> loadMember(T serchValue) {
        return Optional.empty();
    }

    @Override
    public Optional<List<MemberDTO>> allLoadMember() {
        return Optional.empty();
    }

    @Override
    public Optional<String> searchLoginfo(String searchValue) {
        return Optional.empty();
    }

    @Override
    public Optional<String> logInnOut(String memberId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<MemberDTO>> loadRequestMember() {
        return Optional.empty();
    }
}
