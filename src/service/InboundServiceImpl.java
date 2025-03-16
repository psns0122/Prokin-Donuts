package service;

import dto.InboundDTO;
import dto.ProductDTO;
import repository.InboundRepo;

import java.util.List;

public class InboundServiceImpl implements InboundService {
    private final InboundRepo inboundRepo;

    public InboundServiceImpl(InboundRepo inboundRepo) {
        this.inboundRepo = inboundRepo;
    }


    @Override
    public List<InboundDTO> getInboundList() {
        return null;
    }

    @Override
    public void updateInboundInfo(int inboundId, List<ProductDTO> inboundList) {

    }

    @Override
    public boolean checkInboundDate(int inboundId) {
        return false;
    }

    @Override
    public void deleteInboundInfo(int inboundId) {

    }

    @Override
    public List<InboundDTO> getApprovalInboundList() {
        return null;
    }

    @Override
    public void completedInbound(int inboundId) {

    }

    @Override
    public List<ProductDTO> getProductMenu() {
        return null;
    }

    @Override
    public void registerInbound(List<ProductDTO> inboundList) {

    }
}
