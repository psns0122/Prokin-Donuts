package vo.outboundVO;

import java.util.Date;

public class OutboundVO {
    private String outboundId;
    private Date outboundDate;
    private String outboundStatus;

    public OutboundVO(String outboundId, Date outboundDate, String outboundStatus) {
        this.outboundId = outboundId;
        this.outboundDate = outboundDate;
        this.outboundStatus = outboundStatus;
    }
    public String getOutboundId() { return outboundId; }
    public Date getOutboundDate() { return outboundDate; }
    public String getOutboundStatus() { return outboundStatus; }
}
