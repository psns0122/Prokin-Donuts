package repository;

import dto.InboundDTO;
import dto.ProductDTO;
import vo.ProductVO;

import java.util.List;

public class InboundRepoImpl implements InboundRepo {
    @Override
    public List<InboundDTO> findByApprovedStatus() {
        return null;
    }

    @Override
    public void updateCompletedStatus(int inboundId) {

    }

    @Override
    public List<ProductDTO> getProductInfo() {
        return null;
    }

    @Override
    public void registerInboundInfo(List<ProductVO> inboundList) {

    }

    @Override
    public String getInboundStatus(int inboundId) {
        return null;
    }

    @Override
    public void updateInboundInfo(int inboundId, List<ProductVO> inboundList) {

    }

    @Override
    public void deleteInboundInfo(int inboundId) {

    }

    @Override
    public List<InboundDTO> getInboundRequest() {
        return null;
    }

    @Override
    public void updateInboundStatus(int inboundId) {

    }

    @Override
    public List<InboundDTO> getAllInboundInfo(int warehouseId) {
        return null;
    }


}
