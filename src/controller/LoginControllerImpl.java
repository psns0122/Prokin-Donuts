package controller;

import common.login.LoginErrorCode;
import common.login.LoginText;
import common.member.MemberText;
import common.util.InputUtil;
import common.util.LoginUtil;
import dto.memberDTO.MemberDTO;
import dto.memberDTO.MemberRequestDTO;
import service.MemberService;

import java.util.HashMap;
import java.util.Map;

public class LoginControllerImpl implements LoginController{
    MemberService memberService;
    public LoginControllerImpl(MemberService memberService) {
        this.memberService = memberService;
    }

    Map<Integer,Runnable> mainMenu = new HashMap<>();

    public void setMainMenu(){
        mainMenu.put(1,()->login());
        mainMenu.put(2,()->memberRequest());
        mainMenu.put(3,()->findId());
        mainMenu.put(4,()->findPassword());
        mainMenu.put(5,()->logout());
    }

    public void login(){
        System.out.println(LoginText.LOGIN_HEADER.getText());

        String id =InputUtil.getInput(LoginText.LOGIN_NO.getText()).get();
        String password =InputUtil.getInput(LoginText.LOGIN_PASSWORD.getText()).get();

        MemberDTO result = memberService.logIn(id,password);
        if(result == null) System.out.println(LoginErrorCode.LOGIN_NOT_FOUND.getText());
        else if(result.equals("login")) System.out.println(LoginErrorCode.LOGIN_FAIL.getText());
        else {
            LoginUtil.setLoginMember(result);
            System.out.println(LoginText.LOGIN_SUCCESS.getText());
        }
    }

    public void memberRequest(){
        System.out.println(LoginText.REQUEST_HEADER.getText());
        MemberRequestDTO memberRequest = newMemberRequest();
        MemberRequestDTO result = memberService.requestMember(memberRequest);
        if(result == null) System.out.println(LoginErrorCode.REQUEST_NOT_FOUND.getText());
        else System.out.println(LoginText.LOGIN_SUCCESS.getText());
    }

    public void findId(){
        System.out.println(LoginText.SEARCH_ID_HEADER.getText());

    }
    public void findPassword(){

    }


    public void logout(){
        String id = LoginUtil.getLoginMember().getId();
        String result = memberService.logOut(id);
        if(result == null) System.out.println(LoginErrorCode.LOGOUT_FAIL.getText());
        else if(result.equals("logout")) System.out.println(LoginErrorCode.LOGIN_FAIL_OUT.getText());
        else {
            System.out.println(LoginText.LOGOUT_SUCCESS.getText());
            LoginUtil.setLoginMember(null);
        }
    }


    public MemberRequestDTO newMemberRequest(){
        MemberRequestDTO newMemberRequest = new MemberRequestDTO();
        newMemberRequest.setName(InputUtil.getInput(LoginText.REQUEST.getText()+MemberText.MEMBER_NAME.getText()).get());
        newMemberRequest.setAuthorityId(InputUtil.getMenuSelection(LoginText.REQUEST.getText()+MemberText.MEMBER_AUTHORITYID.getText()).get());
        newMemberRequest.setPhoneNumber(InputUtil.getInput(LoginText.REQUEST.getText()+MemberText.MEMBER_PHONE.getText()).get());
        newMemberRequest.setEmail(InputUtil.getInput(LoginText.REQUEST.getText()+MemberText.MEMBER_EMAIL.getText()).get());
        newMemberRequest.setAddress(InputUtil.getInput(LoginText.REQUEST.getText()+MemberText.MEMBER_ADDRESS.getText()).get());
        newMemberRequest.setId(InputUtil.getInput(LoginText.REQUEST.getText()+MemberText.MEMBER_ID.getText()).get());
        newMemberRequest.setPassword(InputUtil.getInput(LoginText.REQUEST.getText()+MemberText.MEMBER_PASSWORD.getText()).get());
       return newMemberRequest;
    }
}
