package controller;

import common.member.MemberText;
import common.util.MenuUtil;
import common.warehouse.WarehouseErrorCode;
import config.DBUtil;
import service.MemberService;
import service.MemberServiceImpl;

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
    Map<Integer,Runnable> WMMenu = new HashMap<>();
    Map<Integer,Runnable> FMMenu = new HashMap<>();

    public void setMainMenu(){
        mainMenu.put(1,()-> HQMenu());
        mainMenu.put(2,()-> WMMenu());
        mainMenu.put(3,()-> FMMenu());
    }

    public void setHQMenu(){}
    public void setWMMenu(){}
    public void setFMMenu(){}

    public void MainMune(int authorityId){
        MemberText.MENU_HEADER.getText();
        mainMenu.get(authorityId);
    }


    public void HQMenu(){
        MemberText.HQ_MEMBER_MENU.getText();

    }

    public void WMMenu(){
        MemberText.WM_MEMBER_MENU.getText();
    }

    public void FMMenu(){
        MemberText.FM_MEMBER_MENU.getText();
    }










}
