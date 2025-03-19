package service;

import dto.franchise.FranchiseDTO;
import dto.franchise.ProductCategoryDTO;
import dto.franchise.ProductDTO;
import dto.memberDTO.MemberDTO;
import dto.warehouse.WarehouseDTO;
import repository.FranchiseRepo;
import repository.WarehouseRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FranchiseServiceImpl implements FranchiseService {
    private final FranchiseRepo franchiseRepo;
    public FranchiseServiceImpl(FranchiseRepo franchiseRepo) {
        this.franchiseRepo = franchiseRepo;
    }

    @Override
    public String insertFranchise(FranchiseDTO franchise) {
        Optional<String> result = this.franchiseRepo.insertFranchise(franchise);
        return result.orElse("[Service] Insert Franchise Failed");
    }

    @Override
    public String updateFranchise(FranchiseDTO franchise) {
        Optional<String> result = this.franchiseRepo.updateFranchise(franchise);
        return result.orElse("[Service] update Franchise Failed");
    }

    @Override
    public String deleteFranchise(int franchiseId) {
        Optional<String> result = this.franchiseRepo.deleteFranchise(franchiseId);
        return result.orElse("[Service] delete Franchise Failed");
    }

    @Override
    public List<FranchiseDTO> viewFranchises() {
        Optional<List<FranchiseDTO>> result = franchiseRepo.showAllFranchise();
        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<FranchiseDTO> viewFranchiseById(int franchiseId) {
        Optional<List<FranchiseDTO>> result = franchiseRepo.showOneFranchise(franchiseId);
        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<MemberDTO> viewManagerHaveNoFranchise() {
        Optional<List<MemberDTO>> result = franchiseRepo.showHaveNoFranchiseFM();
        return result.orElse(Collections.emptyList());
    }

    @Override
    public String insertProduct(ProductDTO product) {
        Optional<String> result = this.franchiseRepo.insertProduct(product);
        return result.orElse("[Service] Insert Product Failed");
    }

    @Override
    public String insertProductCategory(ProductCategoryDTO productCategory) {
        Optional<String> result = this.franchiseRepo.insertProductCategory(productCategory);
        return result.orElse("[Service] update Product Failed");
    }

    @Override
    public String updateProduct(ProductDTO product) {
        Optional<String> result = this.franchiseRepo.updateProduct(product);
        return result.orElse("[Service] update Product Failed");
    }

    @Override
    public String deleteProduct(int productId) {
        Optional<String> result = this.franchiseRepo.deleteProduct(productId);
        return result.orElse("[Service] delete Product Failed");
    }

    @Override
    public List<ProductDTO> viewProducts() {
        Optional<List<ProductDTO>> result = franchiseRepo.showAllProduct();
        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<ProductDTO> viewProductByCategory(int categoryId) {
        Optional<List<ProductDTO>> result = franchiseRepo.showAllProductByCategory(categoryId);
        return result.orElse(Collections.emptyList());
    }

    @Override
    public List<ProductDTO> viewProductById(int productId) {
        Optional<List<ProductDTO>> result = franchiseRepo.showOneProduct(productId);
        return result.orElse(Collections.emptyList());
    }
}
