package common.login;

/*
    예외 처리에 사용되는 에러 발생 text를 미리 정의하는 enum 클래스입니다.
 */
public enum LoginErrorCode {
    REQUEST_NOT_FOUND("회원 가입 요청 실패"),
    REQUEST_FAIL("이미 존재하는 아이디"),
    LOGIN_FAIL("이미 로그인 중입니다."),
    LOGIN_FAIL_OUT("이미 로그아웃 상태입니다."),
    LOGIN_FAIL_REQUEST("회원가입 승인 대기 상태 입니다."),
    LOGOUT_FAIL("로그아웃 실패"),
    LOGIN_NOT_FOUND("아이디 또는 비밀번호가 올바르지 않습니다."),
    RANDOM_NUM_CHECK_F("인증번호가 일치하지 않습니다."),
;

    private final String text;

    LoginErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
