package controller;

import common.member.MemberText;
import common.util.InputUtil;
import common.util.MenuUtil;
import dto.memberDTO.MemberDTO;
import service.MemberService;

import java.util.HashMap;
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
    Map<Integer,Runnable> WMMenu = new HashMap<>();
    Map<Integer,Runnable> FMMenu = new HashMap<>();

    public void setMainMenu(){
        mainMenu.put(1,()-> HQMenu());
        mainMenu.put(2,()-> WMMenu());
        mainMenu.put(3,()-> FMMenu());
    }

    public void setHQMenu(){
        HQMenu.put(1,()-> memberService.addMember(newMember()));
        HQMenu.put(2,()-> memberService.updateMember(updateMember()));
        HQMenu.put(3,()-> memberService.deleteMember(InputUtil.getInput(MemberText.DELETE_MEMBER.getText()+MemberText.MEMBER_ID.getText()).get()));
        HQMenu.put(4,()->MenuUtil.handleMenuSelection(MemberText.HQ_MEMBER_SEARCH_MENU.getText(),HQSearchMenu) );

    }

    public void setHQSearchMenu(){
        mainMenu.put(1,()-> memberService.searchSimple(InputUtil.getInput(MemberText.SEARCH_MEMBER_ID.getText()).get()));
        mainMenu.put(2,()-> memberService.searchDitail(InputUtil.getInput(MemberText.SEARCH_MEMBER_ID.getText()).get()));
        mainMenu.put(3,()-> memberService.searchAuthority(InputUtil.getInput(MemberText.SEARCH_MEMBER_AUTHORITY.getText()).get()));
        mainMenu.put(4,()-> memberService.searchAll());
    }

    public void setWMMenu(){}
    public void setFMMenu(){}

    public void MainMune(int authorityId){
        MemberText.MENU_HEADER.getText();
        mainMenu.get(authorityId);
    }


    public void HQMenu(){
        MenuUtil.handleMenuSelection(MemberText.HQ_MEMBER_MENU.getText(),HQMenu);
    }

    public void WMMenu(){
    }

    public void FMMenu(){
    }

    public MemberDTO newMember(){
        MemberDTO newmember = new MemberDTO();
        newmember.setName(InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_NAME.getText()).get());
        newmember.setAuthorityId(InputUtil.getMenuSelection(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_AUTHORITYID.getText()).get());
        newmember.setPhoneNumber(InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_PHONE.getText()).get());
        newmember.setEmail(InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_EMAIL.getText()).get());
        newmember.setAddress(InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_ADDRESS.getText()).get());
        newmember.setId(InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_ID.getText()).get());
        newmember.setPassword(InputUtil.getInput(MemberText.INSERT_MEMBER.getText()+MemberText.MEMBER_PASSWORD.getText()).get());
        return newmember;
    }

    public MemberDTO updateMember(){
        MemberDTO updateMember = new MemberDTO();
        updateMember.setName(InputUtil.getInput(MemberText.UPDATE_MEMBER.getText()+MemberText.MEMBER_NAME.getText()).get());
        updateMember.setAuthorityId(InputUtil.getMenuSelection(MemberText.UPDATE_MEMBER.getText()+MemberText.MEMBER_AUTHORITYID.getText()).get());
        updateMember.setPhoneNumber(InputUtil.getInput(MemberText.UPDATE_MEMBER.getText()+MemberText.MEMBER_PHONE.getText()).get());
        updateMember.setEmail(InputUtil.getInput(MemberText.UPDATE_MEMBER.getText()+MemberText.MEMBER_EMAIL.getText()).get());
        updateMember.setAddress(InputUtil.getInput(MemberText.UPDATE_MEMBER.getText()+MemberText.MEMBER_ADDRESS.getText()).get());
        updateMember.setId(InputUtil.getInput(MemberText.UPDATE_MEMBER.getText()+MemberText.MEMBER_ID.getText()).get());
        updateMember.setPassword(InputUtil.getInput(MemberText.UPDATE_MEMBER.getText()+MemberText.MEMBER_PASSWORD.getText()).get());
        return updateMember;
    }

}









