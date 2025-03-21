package vo.outboundVO;

import java.util.Date;

public class OutboundVO {
    private Date outboundDate;
    private String outboundStatus;

    public OutboundVO(Date outboundDate, String outboundStatus) {
        this.outboundDate = outboundDate;
        this.outboundStatus = outboundStatus;
    }

    public Date getOutboundDate() {
        return outboundDate;
    }

    public String getOutboundStatus() {
        return outboundStatus;
    }
}