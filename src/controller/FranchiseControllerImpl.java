package controller;

import common.franchise.FranchiseErrorCode;
import common.franchise.FranchiseText;
import common.warehouse.WarehouseErrorCode;
import common.warehouse.WarehouseText;
import dto.franchise.FranchiseDTO;
import dto.franchise.ProductCategoryDTO;
import dto.franchise.ProductDTO;
import dto.warehouse.WarehouseDTO;
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
        System.out.println(FranchiseText.HQ_INSERT_FRANCHISE_HEADER.getText());

        System.out.print(FranchiseText.HQ_INSERT_FRANCHISE_LOCATION.getText());
        String franchiseLocation = scanner.nextLine();
        System.out.print(FranchiseText.HQ_INSERT_FRANCHISE_MANAGER_ID.getText());
        int memberId = scanner.nextInt();
        scanner.nextLine();

        FranchiseDTO franchise = new FranchiseDTO();
        franchise.setManagerNo(memberId);
        franchise.setFranchiseLocation(franchiseLocation);
        System.out.println(franchiseService.insertFranchise(franchise));
    }

    @Override
    public void updateFranchise() {
        System.out.println(FranchiseText.HQ_UPDATE_FRANCHISE_HEADER.getText());
        System.out.print(FranchiseText.HQ_UPDATE_FRANCHISE_MANAGER_ID.getText());
        int memberId = scanner.nextInt();
        scanner.nextLine();

        FranchiseDTO franchise = new FranchiseDTO();
        franchise.setManagerNo(memberId);
        System.out.println(franchiseService.updateFranchise(franchise));
    }

    @Override
    public void deleteFranchise() {
        System.out.println(FranchiseText.HQ_DELETE_FRANCHISE_HEADER.getText());
        System.out.print(FranchiseText.HQ_DELETE_FRANCHISE_ID.getText());
        int franchiseId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(franchiseService.deleteFranchise(franchiseId));
    }

    @Override
    public void viewFranchiseAll() {
        System.out.print(FranchiseText.HQ_SHOW_FRANCHISE_BY_ALL_HEADER.getText());
        franchiseService.viewFranchises().forEach(System.out::println);
    }

    @Override
    public void viewFranchiseOne() {
        System.out.println(FranchiseText.HQ_SHOW_FRANCHISE_BY_ID_HEADER.getText());
        System.out.print(FranchiseText.HQ_SHOW_FRANCHISE_ID.getText());
        int franchiseId = scanner.nextInt();
        scanner.nextLine();

        franchiseService.viewFranchiseById(franchiseId).forEach(System.out::println);
    }

    @Override
    public void viewManagerHaveNoFranchise() {
        System.out.print(FranchiseText.HQ_SHOW_MANAGER_BY_HAVE_NO_FRANCHISE_HEADER.getText());
        franchiseService.viewManagerHaveNoFranchise().forEach(System.out::println);
    }

    @Override
    public void insertProduct() {
        System.out.println(FranchiseText.HQ_INSERT_PRODUCT_HEADER.getText());

        System.out.print(FranchiseText.HQ_INSERT_PRODUCT_CATEGORY_NAME.getText());
        int category = scanner.nextInt();
        scanner.nextLine();

        System.out.print(FranchiseText.HQ_INSERT_PRODUCT_NAME.getText());
        String name = scanner.nextLine();
        System.out.print(FranchiseText.HQ_INSERT_PRODUCT_PRICE.getText());
        int price = scanner.nextInt();
        scanner.nextLine();

        System.out.print(FranchiseText.HQ_INSERT_PRODUCT_TYPE.getText());
        String type = scanner.nextLine();

        ProductDTO product = new ProductDTO();
        product.setCategoryId(category);
        product.setProductName(name);
        product.setProductPrice(price);
        product.setProductType(type);
        System.out.println(franchiseService.insertProduct(product));
    }

    @Override
    public void insertProductCategory() {
        System.out.println(FranchiseText.HQ_INSERT_PRODUCT_CATEGORY_HEADER.getText());

        System.out.print(FranchiseText.HQ_INSERT_PRODUCT_CATEGORY_MID.getText());
        String type1 = scanner.nextLine();
        System.out.print(FranchiseText.HQ_INSERT_PRODUCT_CATEGORY_LAST.getText());
        String type2 = scanner.nextLine();

        ProductCategoryDTO productCategory = new ProductCategoryDTO();
        productCategory.setCategoryMid(type1);
        productCategory.setCategorySub(type2);
        System.out.println(franchiseService.insertProductCategory(productCategory));
    }

    @Override
    public void updateProduct() {
        System.out.println(FranchiseText.HQ_UPDATE_PRODUCT_HEADER.getText());
        System.out.print(FranchiseText.HQ_UPDATE_PRODUCT_MANAGER_ID.getText());
        int productId = scanner.nextInt();
        scanner.nextLine();
        System.out.print(FranchiseText.HQ_UPDATE_PRODUCT_MANAGER_ID1.getText());
        int productCategoryId = scanner.nextInt();
        scanner.nextLine();
        System.out.print(FranchiseText.HQ_UPDATE_PRODUCT_MANAGER_ID2.getText());
        String name = scanner.nextLine();
        System.out.print(FranchiseText.HQ_UPDATE_PRODUCT_MANAGER_ID3.getText());
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print(FranchiseText.HQ_UPDATE_PRODUCT_MANAGER_ID4.getText());
        String type = scanner.nextLine();

        ProductDTO product = new ProductDTO();
        product.setProductId(productId);
        product.setProductName(name);
        product.setProductPrice(price);
        product.setProductType(type);
        product.setCategoryId(productCategoryId);
        System.out.println(franchiseService.updateProduct(product));
    }

    @Override
    public void deleteProduct() {
        System.out.println(FranchiseText.HQ_DELETE_PRODUCT_HEADER.getText());
        System.out.print(FranchiseText.HQ_DELETE_PRODUCT_ID.getText());
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.println(franchiseService.deleteProduct(productId));
    }

    @Override
    public void viewProductAll() {
        System.out.print(FranchiseText.HQ_SHOW_PRODUCT_BY_ALL_HEADER.getText());
        franchiseService.viewFranchises().forEach(System.out::println);
    }

    @Override
    public void viewProductByCategory() {
        System.out.println(FranchiseText.HQ_SHOW_PRODUCT_BY_CATEGORY_HEADER.getText());
        System.out.print(FranchiseText.HQ_SHOW_CATEGORY_ID.getText());
        int categoryId = scanner.nextInt();
        scanner.nextLine();

        franchiseService.viewProductByCategory(categoryId).forEach(System.out::println);
    }

    @Override
    public void viewProductById() {
        System.out.println(FranchiseText.HQ_SHOW_PRODUCT_BY_ID.getText());
        System.out.print(FranchiseText.HQ_SHOW_PRODUCT_ID.getText());
        int productId = scanner.nextInt();
        scanner.nextLine();

        franchiseService.viewProductById(productId).forEach(System.out::println);
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
