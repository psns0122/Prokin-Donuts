package controller;

import common.member.MemberErrorCode;
import common.member.MemberText;
import common.util.InputUtil;
import common.util.LoginUtil;
import common.util.MenuUtil;
import config.DBUtil;
import dto.memberDTO.MemberDTO;
import repository.MemberRepo;
import repository.MemberRepoImpl;
import service.MemberService;
import service.MemberServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MemberControllerImpl implements MemberController {
    Scanner scanner = new Scanner(System.in);

    MemberService memberService;
    public MemberControllerImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    Map<Integer,Runnable> mainMenu = new HashMap<>();
    Map<Integer,Runnable> HQMenu = new HashMap<>();
    Map<Integer,Runnable> HQSearchMenu = new HashMap<>();
    Map<Integer,Runnable> HQAddMenu = new HashMap<>();
    Map<Integer,Runnable> WMMenu = new HashMap<>();
    Map<Integer,Runnable> WMSearchMenu = new HashMap<>();
    Map<Integer,Runnable> FMMenu = new HashMap<>();
    Map<Integer,Runnable> FMSearchMenu = new HashMap<>();


    public Map<Integer,Runnable> setHQMenu(){
        HQAddMenu = setHQAddMenu();
        HQSearchMenu = setHQSearchMenu();
        HQMenu.put(1,()-> MenuUtil.handleMenuSelection(MemberText.HQ_MEMBER_ADD_MENU.getText(),HQAddMenu) );
        HQMenu.put(2,()-> updateMenu());
        HQMenu.put(3,()-> deleteMenu());
        HQMenu.put(4,()-> MenuUtil.handleMenuSelection(MemberText.HQ_MEMBER_SEARCH_MENU.getText(),HQSearchMenu) );
        return HQMenu;
    }
    public Map<Integer,Runnable> setHQAddMenu(){
        HQAddMenu.put(1,()-> addMenu());
        HQAddMenu.put(2,()-> approve());
        return HQAddMenu;
    }

    public Map<Integer,Runnable>  setHQSearchMenu(){
        HQSearchMenu.put(1,()-> searchSimpleMenu());
        HQSearchMenu.put(2,()-> searchDitailMenu());
        HQSearchMenu.put(3,()-> searchAuthorityMenu());
        HQSearchMenu.put(4,()-> searchALLMenu());
        return HQSearchMenu;
    }

    public Map<Integer,Runnable> setWMMenu(){
        WMSearchMenu =setWMSearchMenu();
        WMSearchMenu = setWMSearchMenu();
        WMMenu.put(1,()-> MenuUtil.handleMenuSelection(MemberText.WM_MEMBER_SEARCH_MENU.getText(),WMSearchMenu));
        WMMenu.put(2,()-> updateMenu());
        return WMMenu;
    }

    public Map<Integer,Runnable> setWMSearchMenu(){
        WMSearchMenu.put(1,()->searchSimpleMenu());
        WMSearchMenu.put(2,()->searchDitailMenu());
        WMSearchMenu.put(3,()->searchAuthorityMenu());
        return WMSearchMenu;
    }

    public Map<Integer,Runnable> setFMMenu(){
        FMSearchMenu = setFMSearchMenu();
        FMMenu.put(1,()->MenuUtil.handleMenuSelection(MemberText.FM_MEMBER_SEARCH_MENU.getText(),FMSearchMenu));
        FMMenu.put(2,()->loginMemberUpdate());
        FMMenu.put(3,()->loginMemberDelete());
        return FMMenu;
    }

    public Map<Integer,Runnable> setFMSearchMenu(){
        FMSearchMenu.put(1,()->loginMemberSimpleSearch());
        FMSearchMenu.put(2,()->loginMemberDetailSearch());
        return FMSearchMenu;
    }



    public void HQMenu(){
        HQMenu =  setHQMenu();
        MenuUtil.handleMenuSelection(MemberText.HQ_MEMBER_MENU.getText(),HQMenu);
    }

    public void WMMenu(){
        System.out.println(MemberText.MENU_HEADER.getText());
        WMMenu = setWMMenu();
        MenuUtil.handleMenuSelection(MemberText.WM_MEMBER_MENU.getText(),WMMenu);
    }

    public void FMMenu(){
        System.out.println(MemberText.MENU_HEADER.getText());
        FMMenu = setFMMenu();
        MenuUtil.handleMenuSelection(MemberText.FM_MEMBER_MENU.getText(),FMMenu);
    }


    public void addMenu() {
        System.out.println(MemberText.INSERT_MEMBER_NEW_HEADER.getText());
        MemberDTO newMember = newMember();
        if(!memberService.checkId(newMember.getId())){ //아이디 중복검사
            MemberDTO result = memberService.addMember(newMember);
            if (result == null) System.out.println(MemberErrorCode.INSERT_FAIL.getText());
            else System.out.println(MemberText.INSERT_MEMBER_SUCCESS.getText());
        } else System.out.println(MemberErrorCode.ID_FOUND.getText());

    }

    public void approve(){
        System.out.println(MemberText.INSERT_MEMBER_APPROVE_HEADER.getText());
        memberService.searchRequestMemberAll().forEach(System.out::println);
        String result = memberService.approvalMember(InputUtil.getInput
                (MemberText.INSERT_MEMBER.getText()+
                        MemberText.MEMBER_ID.getText()).get());
        System.out.println(result);
        System.out.println(MemberText.INSERT_MEMBER_SUCCESS);
    }



    public void deleteMenu(){
        System.out.println(MemberText.DELETE_MEMBER_HEADER.getText());
        String deleteMemberId = InputUtil.getInput(MemberText.DELETE_MEMBER.getText()+MemberText.MEMBER_ID.getText()).get();
        String result = memberService.deleteMember(deleteMemberId);
        if(result!=null){
        System.out.print(result);
        System.out.println(MemberText.DELETE_MEMBER_SUCCESS.getText());
        }else System.out.println(MemberErrorCode.DELETE_FAIL.getText());
    }

    public void updateMenu(){
        System.out.println(MemberText.UPDATE_MEMBER_HEADER.getText());

        String updateMemberID = InputUtil.getInput(MemberText.UPDATE_MEMBER.getText()+
                                MemberText.MEMBER_ID.getText()).get();
        MemberDTO updateMember = updateMember();
        MemberDTO result = memberService.updateMember(updateMemberID,updateMember);
        System.out.print(updateMemberID);
        System.out.println(MemberText.UPDATE_MEMBER_SUCCESS.getText());
    }

    public void searchSimpleMenu(){
        System.out.println(MemberText.SEARCH_MEMBER_SIMPLE_HEADER.getText());
        MemberDTO result = memberService.searchMember(InputUtil.getInput(MemberText.SEARCH_MEMBER_ID.getText()).get());
        System.out.println("이름: "+result.getName()+
                            " 아이디 : "+result.getId()+
                            " 이메일: "+result.getEmail());
    }
    public void searchDitailMenu(){
        System.out.println(MemberText.SEARCH_MEMBER_DETAIL_HEADER.getText());
        MemberDTO result = memberService.searchMember(InputUtil.getInput(MemberText.SEARCH_MEMBER_ID.getText()).get());
        System.out.println(result);
    }
    public void searchAuthorityMenu(){
        System.out.println(MemberText.SEARCH_MEMBER_AUTHORITY_HEADER.getText());
        List<MemberDTO> result = memberService.searchAuthority(InputUtil.getInput(MemberText.SEARCH_MEMBER_AUTHORITY.getText()).get());
        result.forEach(System.out::println);
    }
    public void searchALLMenu(){
        System.out.println(MemberText.SEARCH_MEMBER_ALL_HEADER.getText());
        List<MemberDTO> result = memberService.searchAll();
        result.forEach(System.out::println);
    }


    public MemberDTO newMember(){

        String name = InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_NAME.getText()).get();
        int authority = InputUtil.getMenuSelection(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_AUTHORITYID.getText()).get();
        String phoneNumber = InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_PHONE.getText()).get();
        String email =InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_EMAIL.getText()).get();
        String address =InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_ADDRESS.getText()).get();
        String id = InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_ID.getText()).get();
        String password = InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_PASSWORD.getText()).get();

        MemberDTO newmember = new MemberDTO();
        newmember.setName(name);
        newmember.setAuthorityId(authority);
        newmember.setPhoneNumber(phoneNumber);
        newmember.setEmail(email);
        newmember.setId(id);
        newmember.setPassword(password);
        return newmember;
    }

    public MemberDTO updateMember(){
        MemberDTO updateMember = new MemberDTO();
        String name = InputUtil.getInput(MemberText.MEMBER_NAME.getText()).get();
        int authority = InputUtil.getMenuSelection(MemberText.MEMBER_AUTHORITYID.getText()).get();
        String phoneNumber = InputUtil.getInput(MemberText.MEMBER_PHONE.getText()).get();
        String email =InputUtil.getInput(MemberText.MEMBER_EMAIL.getText()).get();
        String address =InputUtil.getInput(MemberText.MEMBER_ADDRESS.getText()).get();
        String id = InputUtil.getInput(MemberText.MEMBER_ID.getText()).get();
        String password = InputUtil.getInput(MemberText.MEMBER_PASSWORD.getText()).get();

        updateMember.setName(name);
        updateMember.setAuthorityId(authority);
        updateMember.setPhoneNumber(phoneNumber);
        updateMember.setEmail(email);
        updateMember.setAddress(address);
        updateMember.setId(id);
        updateMember.setPassword(password);
        return updateMember;
    }

    //로그인한 회원의 회원정보 수정
    public void loginMemberUpdate(){
       int loginMember = LoginUtil.getLoginMember().getMemberNo();
       MemberDTO updateMember= updateMember();
       updateMember().setMemberNo(loginMember);
       memberService.updateMember(LoginUtil.getLoginMember().getId(),updateMember);
    }

    //로그인한 회원의 탈퇴
    public void loginMemberDelete(){
       String loginMember = LoginUtil.getLoginMember().getId();
        memberService.deleteMember(loginMember);
    }

    //로그인한 회원의 간편조회
    public void loginMemberSimpleSearch() {
        MemberText.SEARCH_MEMBER_SIMPLE_HEADER.getText();
        String loginMember = LoginUtil.getLoginMember().getId();
        if (loginMember == null) System.out.println(MemberErrorCode.MEMBER_NOT_FOUND.getText());
        else {
            MemberDTO result = memberService.searchMember(loginMember);
            System.out.println("이름 : "+result.getName() + " 아이디: " + result.getId() + " 이메일:" + result.getEmail());
        }
    }

    //로그인한 회원의 상세조회
    public void loginMemberDetailSearch(){
       String loginMember = LoginUtil.getLoginMember().getId();
        MemberDTO result  = memberService.searchMember(loginMember);
        System.out.println(result);
    }


}









