package vo.outboundVO;

import java.util.Date;

public class OutboundVO {
    private int outboundId;
    private Date outboundDate;
    private String outboundStatus;

    // 기존 레코드 조회용 생성자
    public OutboundVO(int outboundId, Date outboundDate, String outboundStatus) {
        this.outboundId = outboundId;
        this.outboundDate = outboundDate;
        this.outboundStatus = outboundStatus;
    }

    // 신규 출고 등록 시, outboundId는 아직 미정이므로 기본값(0) 사용
    public OutboundVO(Date outboundDate, String outboundStatus) {
        this.outboundId = 0;
        this.outboundDate = outboundDate;
        this.outboundStatus = outboundStatus;
    }

    public int getOutboundId() {
        return outboundId;
    }
    public Date getOutboundDate() {
        return outboundDate;
    }
    public String getOutboundStatus() {
        return outboundStatus;
    }
}
