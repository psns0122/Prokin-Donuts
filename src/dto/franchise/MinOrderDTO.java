package dto.franchise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinOrderDTO {
    /** 가맹점 아이디 */
    private int franchiseId;
    /** 도넛 최소발주량 */
    private int dountMinOrder;
    /** 도넛 외 최소발주량 */
    private int etcMinOrder;
}
