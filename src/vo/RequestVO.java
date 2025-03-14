package vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/** 요청 테이블 */
public class RequestVO {
    /** 요청 아이디 */
    private String requestId;
    /** 요청 타입 */
    private String requestType;
    /** 요청승인 상태 */
    private String requestStatus;
    /** 요청내용 */
    private String request;
    /** 회원번호 */
    private String memberNo;
    /** 권한 아이디 */
    private String authorityId;
}
