package service;

import dto.franchise.FranchiseDTO;
import dto.franchise.ProductCategoryDTO;
import dto.franchise.ProductDTO;
import dto.memberDTO.MemberDTO;
import dto.warehouse.WarehouseDTO;

import java.util.List;

public interface FranchiseService {

    /** 가맹점 등록 */
    String insertFranchise(FranchiseDTO franchise);

    /** 가맹점 수정 */
    String updateFranchise(FranchiseDTO franchise);

    /** 가맹점 삭제 */
    String deleteFranchise(int franchiseId);



    List<FranchiseDTO> viewFranchises();

    List<FranchiseDTO> viewFranchiseById(int franchiseId);

    List<MemberDTO> viewManagerHaveNoFranchise();



    /** 제품 등록 */
    String insertProduct(ProductDTO product);

    /** 카테고리 등록 */
    String insertProductCategory(ProductCategoryDTO productCategory);

    /** 제품 수정 */
    String updateProduct(ProductDTO product);

    /** 제품 삭제 */
    String deleteProduct(int productId);



    List<ProductDTO> viewProducts();

    List<ProductDTO> viewProductByCategory(int categoryId);

    List<ProductDTO> viewProductById(int productId);

}
