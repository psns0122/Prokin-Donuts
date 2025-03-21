package dto.franchise;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FranchiseDTO {
    /** 가맹점 아이디 */
    private int franchiseId;
    /** 가맹점 위치 */
    private String franchiseLocation;
    /** 회원번호 */
    private int managerNo;
}
