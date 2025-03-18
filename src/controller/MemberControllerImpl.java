package controller;

import common.member.MemberText;
import common.util.InputUtil;
import common.util.MenuUtil;
import dto.memberDTO.MemberDTO;
HQimport lombok.Data;
import service.MemberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
@Data
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


    public Map<Integer,Runnable>  setMainMenu(){
        mainMenu.put(1,()-> HQMenu());
        mainMenu.put(2,()-> WMMenu());
        mainMenu.put(3,()-> FMMenu());
        return mainMenu;
    }

    public void setHQMenu(){
        HQMenu.put(1,()-> addMenu());
        HQMenu.put(2,()-> deleteMenu());
        HQMenu.put(3,()-> updateMenu());
        HQMenu.put(4,()-> MenuUtil.handleMenuSelection(MemberText.HQ_MEMBER_SEARCH_MENU.getText(),HQSearchMenu) );

    }

    public void setHQSearchMenu(){
        HQSearchMenu.put(1,()-> searchSimpleMenu());
        HQSearchMenu.put(2,()-> searchDitailMenu());
        HQSearchMenu.put(3,()-> searchAuthorityMenu());
        HQSearchMenu.put(4,()-> searchALLMenu());

    }

    public void setWMMenu(){}
    public void setFMMenu(){}

    public void MainMune(int authorityId){
        MemberText.MENU_HEADER.getText();
        mainMenu = setMainMenu();
        Runnable action = mainMenu.get(authorityId);
        action.run();
    }


    public void HQMenu(){
        MenuUtil.handleMenuSelection(MemberText.HQ_MEMBER_MENU.getText(),HQMenu);
    }

    public void WMMenu(){
    }

    public void FMMenu(){
    }


    public void addMenu(){
        MemberDTO result = memberService.addMember(newMember());
        System.out.println(result.getId()+MemberText.INSERT_MEMBER_SUCCESS);
    }
    public void deleteMenu(){
        MemberDTO result = memberService.deleteMember(InputUtil.getInput(MemberText.DELETE_MEMBER.getText()+MemberText.MEMBER_ID.getText()).get()));
        System.out.println(result.getId()+MemberText.DELETE_MEMBER_SUCCESS);
    }
    public void updateMenu(){
        MemberDTO result = memberService.updateMember(updateMember());
        System.out.println(result.getId()+MemberText.UPDATE_MEMBER_SUCCESS);
    }

    public void searchSimpleMenu(){
        MemberDTO result = memberService.searchSimple(InputUtil.getInput(MemberText.SEARCH_MEMBER_ID.getText()).get());
        System.out.println(result.getName()+" "+result.getId()+" "+result.getEmail());
    }
    public void searchDitailMenu(){
        MemberDTO result = memberService.searchDitail(InputUtil.getInput(MemberText.SEARCH_MEMBER_ID.getText()).get());
        System.out.println(result);
    }
    public void searchAuthorityMenu(){
        MemberDTO result = memberService.searchAuthority(InputUtil.getInput(MemberText.SEARCH_MEMBER_AUTHORITY.getText()).get()));
        System.out.println(result);
    }
    public void searchALLMenu(){
        List<MemberDTO> result = memberService.searchAll();
        result.forEach(System.out::println);
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









