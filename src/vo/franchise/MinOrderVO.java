package vo.franchise;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 최소발주량 테이블 */
public class MinOrderVO {
    /** 가맹점 아이디 */
    private int franchiseId;
    /** 도넛 최소발주량 */
    private int dountMinOrder;
    /** 도넛 외 최소발주량 */
    private int etcMinOrder;
}
