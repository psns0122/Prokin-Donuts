package service;

import dto.InboundDTO;
import dto.ProductDTO;
import repository.InboundRepo;

import java.util.Date;
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
    public List<ProductDTO> getInboundDetail(int inboundId) {
        return null;
    }

    @Override
    public List<InboundDTO> getAllInboundInfo() {
        return null;
    }

    @Override
    public List<InboundDTO> getDateInboundInfo(Date start_date, Date end_date) {
        return null;
    }

    @Override
    public List<InboundDTO> getInboundRequest() {
        return null;
    }

    @Override
    public void updateInboundStatus(int inbound) {

    }

    @Override
    public List<InboundDTO> getAllInboundInfo(int warehouseId) {
        return null;
    }

    @Override
    public List<InboundDTO> getAllInbound() {
        return null;
    }

    @Override
    public List<InboundDTO> getInboundByDate(Date start_date, Date end_date) {
        return null;
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
