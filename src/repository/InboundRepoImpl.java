package repository;

import dto.InboundDTO;
import dto.ProductDTO;
import vo.ProductVO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class InboundRepoImpl implements InboundRepo {
    @Override
    public Optional<List<InboundDTO>> findByApprovedStatus() {
        return Optional.empty();
    }

    @Override
    public void updateCompletedStatus(int inboundId) {

    }

    @Override
    public Optional<List<ProductDTO>> getProductInfo() {
        return Optional.empty();
    }

    @Override
    public void registerInboundInfo(List<ProductVO> inboundList) {

    }

    @Override
    public Optional<String> getInboundStatus(int inboundId) {
        return Optional.empty();
    }

    @Override
    public void updateInboundInfo(int inboundId, List<ProductVO> inboundList) {

    }

    @Override
    public void deleteInboundInfo(int inboundId) {

    }

    @Override
    public Optional<List<InboundDTO>> getInboundRequest() {
        return Optional.empty();
    }

    @Override
    public void updateInboundStatus(int inboundId) {

    }

    @Override
    public Optional<List<InboundDTO>> getAllInboundInfo(int warehouseId) {
        return Optional.empty();
    }

    @Override
    public Optional<List<InboundDTO>> getAllInbound() {
        return Optional.empty();
    }

    @Override
    public Optional<List<InboundDTO>> getInboundByDate(Date start_date, Date end_date) {
        return Optional.empty();
    }


}
