package dto.franchise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FranchiseDTO {
    /** 가맹점 아이디 */
    private int franchiseId;
    /** 가맹점 위치 */
    private String franchiseIdLocation;
    /** 회원번호 */
    private int managerNo;
}
