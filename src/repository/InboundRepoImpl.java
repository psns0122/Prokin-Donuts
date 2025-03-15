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


}
