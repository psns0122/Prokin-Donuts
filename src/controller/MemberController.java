package controller;

import common.member.MemberErrorCode;
import common.member.MemberText;
import common.util.InputUtil;
import common.util.LoginUtil;
import common.util.MenuUtil;
import dto.memberDTO.MemberDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface MemberController {

    public Map<Integer,Runnable>  setMainMenu();

    public Map<Integer,Runnable> setHQMenu();
    public Map<Integer,Runnable> setHQAddMenu();


    public Map<Integer,Runnable>  setHQSearchMenu();

    public Map<Integer,Runnable> setWMMenu();

    public Map<Integer,Runnable> setWMSearchMenu();

    public Map<Integer,Runnable> setFMMenu();

    public Map<Integer,Runnable> setFMSearchMenu();

    public void MainMune();


    public void HQMenu();

    public void WMMenu();

    public void FMMenu();


    public void addMenu();


    public void approve();



    public void deleteMenu();

    public void updateMenu();

    public void searchSimpleMenu();
    public void searchDitailMenu();
    public void searchAuthorityMenu();
    public void searchALLMenu();


    public MemberDTO newMember();

    public MemberDTO updateMember();

    //로그인한 회원의 회원정보 수정
    public void loginMemberUpdate();

    //로그인한 회원의 탈퇴
    public void loginMemberDelete();

    //로그인한 회원의 간편조회
    public void loginMemberSimpleSearch();
    //로그인한 회원의 상세조회
    public void loginMemberDetailSearch();
}
