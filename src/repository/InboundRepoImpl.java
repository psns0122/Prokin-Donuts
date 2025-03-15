package repository;

import dto.InboundDTO;

import java.util.List;

public class InboundRepoImpl implements InboundRepo {
    @Override
    public List<InboundDTO> findByApprovedStatus() {
        return null;
    }

    @Override
    public void updateCompletedStatus(int inboundId) {

    }
}
