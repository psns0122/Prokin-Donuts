package controller;

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

    }
    public void findId(){
    }
    public void findPassword(){}
    public void logout(){}
}
