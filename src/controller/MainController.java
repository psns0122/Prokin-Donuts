package controller;

import common.util.LoginUtil;

import java.util.Scanner;

public class MainController {
    InboundController inboundController;
    FranchiseController franchiseController;
    InventoryController inventoryController;
    MemberController memberController;
    OrderController orderController;
    WarehouseController warehouseController;
    LoginController loginController;

    Scanner scanner = new Scanner(System.in);

    public MainController(InboundController inboundController, FranchiseController franchiseController, InventoryController inventoryController, MemberController memberController, OrderController orderController, WarehouseController warehouseController, LoginController loginController) {
        this.inboundController = inboundController;
        this.franchiseController = franchiseController;
        this.inventoryController = inventoryController;
        this.memberController = memberController;
        this.orderController = orderController;
        this.warehouseController = warehouseController;
        this.loginController = loginController;
    }

    public void run() {

        // 로그인 메뉴
        memberController.MainMune();

        // 로그인 후 권한 가져오기
        int authority = LoginUtil.getLoginMember().getAuthorityId();

        // 로그인 후 각 권한별 메뉴 (아직 while문 안 돌림)
        switch (authority) {
            case 1: // 본사관리자
                System.out.println("" +
                        "1. 회원관리" +
                        "2. 창고관리" +
                        "3. 재고관리" +
                        "4. 가맹점 관리" +
                        "5. 출고관리" +
                        "6. 입고관리" +
                        "7. 로그아웃");
                int input = scanner.nextInt();
                scanner.nextLine();

            case 2: // 창고관리자
                System.out.println("" +
                        "1. 회원관리" +
                        "2. 창고관리" +
                        "3. 재고관리" +
                        "5. 출고관리" +
                        "6. 입고관리" +
                        "7. 로그아웃");

            case 2: // 점주
                System.out.println("" +
                        "1. 회원관리" +
                        "2. 발주관리" +
                        "3. 로그아웃");


                // 인풋
                warehouseController.showWarehouseMenu(authority);


        }



    }
}