package service;

import dto.memberDTO.MemberDTO;
import dto.memberDTO.MemberRequestDTO;
import repository.MemberRepo;

import java.util.*;

public class MemberServiceImpl implements MemberService {
    MemberRepo memberRepo;
    private Map<String, String> randomNumber = new HashMap<>();

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
    public MemberDTO updateMember(String memberID ,MemberDTO updateMember) {
        Optional<List<MemberDTO>> Memberlist = memberRepo.loadMember("id",memberID);
        Optional<MemberDTO> loadMember = Memberlist.map(list -> list.get(0));
        updateMember.setMemberNo(loadMember.get().getMemberNo());
        Optional<MemberDTO> result = memberRepo.updateMember(updateMember);
        return result.orElse(null);
    }

    //회원 삭제 기능
    @Override
    public String deleteMember(String memberId) {
        Optional<String> result = memberRepo.deleteMember(memberId);
        return result.orElse(null);
    }

    //회원 가입  요청기능
    @Override
    public MemberRequestDTO requestMember(MemberRequestDTO member) {
            Optional<MemberRequestDTO> result = memberRepo.insertRequestMember(member);
            return result.orElse(null);
    }

    //회원아이디 중복검사 기능
    @Override
    public boolean  checkId(String memberId) {
        return memberRepo.searchLoginfo("memberNo","id",memberId).isPresent();
    }

    //회원아이디 간편조회 기능
    @Override
    public MemberDTO searchMember(String memberId) {
        Optional<List<MemberDTO>> result = memberRepo.loadMember("id",memberId);
        return result.filter(list -> !list.isEmpty())
                .map(list -> list.get(0))  // 첫 번째 객체를 꺼냄
                .orElse(null);
    }


    //권한별 조회 기능
    @Override
    public List<MemberDTO> searchAuthority(String authority) {
        int authorityId = 0;

        if (authority.equals("본사관리자")) authorityId = 1;
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

    //아이디 찾기 기능
    @Override
    public String findId(String memberEmail) {
        Optional<String> result = memberRepo.searchLoginfo("id","email",memberEmail);
        return result.orElse(null);
    }

    // 이메일 찾기 기능
    public String findemail(String id) {
        Optional<String> result = memberRepo.searchLoginfo("email","id",id);
        return result.orElse(null);
    }

    //비밀번호 찾기 기능
    @Override
    public String findPassword(String memberId) {
        Optional<String> result = memberRepo.searchLoginfo("password","id",memberId);
        return result.orElse(null); }

    //인증번호 생성 기능
    @Override
    public String randomNumber(String memberEmail) {
        // 6자리 랜덤 숫자 생성
        // 100000~999999 사이의 랜덤 숫자 생성
        String random = String.valueOf((int) (Math.random() * 900000) + 100000);
        randomNumber.put(memberEmail,random);
        return random;
    }

    //인증번호 유효왁인 기능
    @Override
    public boolean checkRandomNumber(String memberEmail, String userRandomNumber) {
        //email(키)가 존재하고, 키의 값(인증번호)이 유저가 입력한 인증번호와 같다면, 인증번호 폐기 후 true 리턴
        if (randomNumber.containsKey(memberEmail) && randomNumber.get(memberEmail).equals(userRandomNumber)) {
           randomNumber.remove(memberEmail); // 인증번호 폐기
            return true;
        }
        return false;
    }

    //회원 승인 기능
    @Override
    public String approvalMember(String memberId) {
        boolean approval = memberRepo.approvalMember(memberId);
        if (approval) return "success";
        else return "fail";
    }

    //로그인 기능
    @Override
    public String logIn(String memberId,String password) {
            Optional<String> result = memberRepo.logInnOut(memberId);
            return result.orElse(null);
    }


    //로그아웃 기능
    @Override
    public String logOut(String memberId) {
        Optional<String> result = memberRepo.logInnOut(memberId);
        return result.orElse(null);
    }

    //회원가입 요청 조회 기능
    @Override
    public List<MemberRequestDTO> searchRequestMemberAll() {
        Optional<List<MemberRequestDTO>> result = memberRepo.loadRequestMemberall();
        return result.orElse(Collections.emptyList());
    }

    // 회원가입 요청 승인대기 상태 조회 기능
    @Override
    public String searchRequestMember(String id) {
        Optional<String> result = memberRepo.RequestMember(id);
        return result.orElse(null);
    }

    //로그인 상태 확인 기능
    @Override
    public String logstatus(String memberId){
        Optional<String> result = memberRepo.searchLoginfo("logstatus","id",memberId);
        return result.orElse(null);
    }

}
