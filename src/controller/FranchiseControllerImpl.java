package controller;

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
    public void showFranchiseMenu() {

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
