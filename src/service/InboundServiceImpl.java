package service;

import dto.InboundDTO;
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
    public void completedInbound(int inboundId) {

    }
}
