package controller;

import common.login.LoginErrorCode;
import common.login.LoginText;
import common.util.InputUtil;
import common.util.LoginUtil;
import dto.memberDTO.MemberDTO;
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
        mainMenu.put(2,()->findId());
        mainMenu.put(3,()->findPassword());
        mainMenu.put(4,()->logout());
    }

    public void login(){
        System.out.println(LoginText.LOGIN_HEADER.getText());

        String id =InputUtil.getInput(LoginText.LOGIN_NO.getText()).get();
        String password =InputUtil.getInput(LoginText.LOGIN_PASSWORD.getText()).get();

        MemberDTO result = memberService.logIn(id,password);
        if(result == null) System.out.println(LoginErrorCode.MEMBERINFO_NOT_FOUND.getText());
        else if(result.equals("login")) System.out.println(LoginErrorCode.LOGIN_FAIL.getText());
        else {
            LoginUtil.setLoginMember(result);
            System.out.println(LoginText.LOGIN_SUCCESS.getText());
        }
    }

    public void findId(){

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
}
