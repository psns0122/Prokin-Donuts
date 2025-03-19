package common.inbound;

/*
    예외 처리에 사용되는 에러 발생 text를 미리 정의하는 enum 클래스입니다.
 */
public enum InboundErrorCode {
    DATE_FORMATTER("[올바른 날짜를 입력하세요] ex) 2025-03-21"),

    NUMBER_NOT_FOUND("[Service]: 직원 번호를 찾을 수 없습니다");

    private final String text;

    InboundErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
