package repository.outboundRepo;

import vo.outboundVO.OutboundDetailVO;
import vo.outboundVO.OutboundVO;

public interface OutboundRepo {
    String saveOutbound(OutboundVO outbound);
    void saveOutboundDetail(OutboundDetailVO detail);
}
