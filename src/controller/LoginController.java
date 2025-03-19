package controller;

import common.login.LoginErrorCode;
import common.login.LoginText;
import common.member.MemberText;
import common.util.InputUtil;
import common.util.LoginUtil;
import dto.memberDTO.MemberDTO;
import dto.memberDTO.MemberRequestDTO;

import java.util.Map;

public interface LoginController {

    public void mainMenu();
    public Map<Integer,Runnable> setMainMenu();

    public void login();


    public void memberRequest();

    public void findId();
    public void findPassword();


    public void logout();

    public MemberRequestDTO newMemberRequest();

}
