package repository;

import dto.orderDTO.OrderDTO;
import dto.franchise.FranchiseDTO;
import dto.franchise.ProductCategoryDTO;
import dto.franchise.ProductDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FranchiseRepoImpl implements FranchiseRepo{
    @Override
    public Optional<FranchiseDTO> insertFranchise(FranchiseDTO franchise) {
        return Optional.empty();
    }

    @Override
    public Optional<FranchiseDTO> updateFranchise(FranchiseDTO franchise) {
        return Optional.empty();
    }

    @Override
    public Optional<FranchiseDTO> deleteFranchise(int franchiseId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<FranchiseDTO>> showAllFranchise() {
        return Optional.empty();
    }

    @Override
    public Optional<List<FranchiseDTO>> showOneFranchise(int franchiseId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<FranchiseDTO>> showHaveNoFranchiseFM() {
        return Optional.empty();
    }

    @Override
    public Optional<ProductDTO> insertProduct(ProductDTO productDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductCategoryDTO> insertProductCategory(ProductCategoryDTO productCategoryDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<List<ProductDTO>> showAllProduct() {
        return Optional.empty();
    }

    @Override
    public Optional<Map<Integer, ProductDTO>> showAllProductByCategory() {
        return Optional.empty();
    }

    @Override
    public Optional<List<ProductDTO>> showOneProduct(int productId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderDTO>> showAllOrder() {
        return Optional.empty();
    }

    @Override
    public Optional<List<OrderDTO>> showAllOrderByFranchise() {
        return Optional.empty();
    }

    @Override
    public boolean approveOrder(int orderId) {
        return false;
    }

    @Override
    public boolean approveOrderCancel(int orderId) {
        return false;
    }
}
