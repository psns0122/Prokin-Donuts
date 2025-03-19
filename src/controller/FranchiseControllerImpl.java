package controller;

import common.franchise.FranchiseErrorCode;
import common.franchise.FranchiseText;
import common.warehouse.WarehouseErrorCode;
import common.warehouse.WarehouseText;
import service.FranchiseService;
import service.WarehouseService;

import java.util.Scanner;

public class FranchiseControllerImpl implements FranchiseController {
    private final FranchiseService franchiseService;
    private final Scanner scanner;

    public FranchiseControllerImpl(FranchiseService franchiseService) {
        this.franchiseService = franchiseService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void showFranchiseMenu(int authorityId) {
        if (authorityId == 1) {
            while (true) {
                System.out.print(FranchiseText.HQ_MENU.getText());
                int choice = scanner.nextInt();
                scanner.nextLine(); // 버퍼 클리어

                switch (choice) {
                    case 1 -> FranchiseSubMenuFranchise();
                    case 2 -> FranchiseSubMenuProduct();
                    case 3 -> FranchiseSubMenuOrder();
                    case 5 -> {
                        System.out.println(WarehouseText.BACK_ACTION.getText());
                        return;
                    }
                    default -> System.out.println(WarehouseErrorCode.INPUT_ERROR.getText());
                }
            }
        }
    }

    void FranchiseSubMenuFranchise(){
        while (true) {
            System.out.print(FranchiseText.HQ_FRANCHISE_MENU.getText());
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 클리어

            switch (choice) {
                case 1 -> insertFranchise();
                case 2 -> updateFranchise();
                case 3 -> deleteFranchise();
                case 4 -> viewFranchiseAll();
                case 5 -> viewFranchiseOne();
                case 6 -> viewManagerHaveNoFranchise();
                case 7 -> {
                    System.out.println(FranchiseText.BACK_ACTION.getText());
                    return;
                }
                default -> System.out.println(FranchiseErrorCode.INPUT_ERROR.getText());
            }
        }
    }
    void FranchiseSubMenuProduct(){
        System.out.print(FranchiseText.HQ_PRODUCT_MENU.getText());
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        switch (choice) {
            case 1 -> FranchiseSubMenuFranchiseProductSubMenu1();
            case 2 -> updateProduct();
            case 3 -> deleteProduct();
            case 4 -> FranchiseSubMenuFranchiseProductSubMenu2();
            case 5 -> {
                System.out.println(FranchiseText.BACK_ACTION.getText());
                return;
            }
            default -> System.out.println(FranchiseErrorCode.INPUT_ERROR.getText());
        }
    }

    void FranchiseSubMenuFranchiseProductSubMenu1(){
        System.out.print(FranchiseText.HQ_PRODUCT_INSERT_MENU.getText());
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        switch (choice) {
            case 1 -> insertProduct();
            case 2 -> insertProductCategory();
            case 3 -> {
                System.out.println(FranchiseText.BACK_ACTION.getText());
                return;
            }
            default -> System.out.println(FranchiseErrorCode.INPUT_ERROR.getText());
        }
    }

    void FranchiseSubMenuFranchiseProductSubMenu2(){
        System.out.print(FranchiseText.HQ_PRODUCT_VIEW_MENU.getText());
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        switch (choice) {
            case 1 -> viewProductAll();
            case 2 -> viewProductByCategory();
            case 3 -> viewProductById();
            case 4 -> {
                System.out.println(FranchiseText.BACK_ACTION.getText());
                return;
            }
            default -> System.out.println(FranchiseErrorCode.INPUT_ERROR.getText());
        }
    }

    void FranchiseSubMenuOrder(){
        System.out.print(FranchiseText.HQ_ORDER_MENU.getText());
        int choice = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        switch (choice) {
            case 1 -> viewOrderAll();
            case 2 -> viewOrderById();
            case 3 -> setOrderApproval();
            case 4 -> setOrderCancelApproval();
            case 5 -> {
                System.out.println(FranchiseText.BACK_ACTION.getText());
                return;
            }
            default -> System.out.println(FranchiseErrorCode.INPUT_ERROR.getText());
        }
    }

    @Override
    public void insertFranchise() {

    }

    @Override
    public void updateFranchise() {

    }

    @Override
    public void deleteFranchise() {

    }

    @Override
    public void viewFranchiseAll() {

    }

    @Override
    public void viewFranchiseOne() {

    }

    @Override
    public void viewManagerHaveNoFranchise() {

    }

    @Override
    public void insertProduct() {

    }

    @Override
    public void insertProductCategory() {

    }

    @Override
    public void updateProduct() {

    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public void viewProductAll() {

    }

    @Override
    public void viewProductByCategory() {

    }

    @Override
    public void viewProductById() {

    }

    @Override
    public void viewOrderAll() {

    }

    @Override
    public void viewOrderById() {

    }

    @Override
    public void setOrderApproval() {

    }

    @Override
    public void setOrderCancelApproval() {

    }
}
