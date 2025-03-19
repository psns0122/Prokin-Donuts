package common.member;

/*
    예외 처리에 사용되는 에러 발생 text를 미리 정의하는 enum 클래스입니다.
 */
public enum MemberErrorCode {
    DB_INSERT_ERROR("[DB]: 정보를 저장할 수 없습니다."),
    DB_UPDATE_ERROR("[DB]: 정보를 수정할 수 없습니다."),
    DB_DELETE_ERROR("[DB]: 정보를 삭제할 수 없습니다."),
    INSERT_FAIL("회원 등록 실패"),
    DELETE_FAIL("회원 등록 실패"),
    ID_NOT_FOUND("[Service]: 존재하지 않는 ID입니다. "),
    ID_FOUND("[Service]: 이미 존재하는 ID입니다."),

    MEMBER_NOT_FOUND("[Service]: 해당 회원이 존재하지 않습니다. "),;


    private final String text;

    MemberErrorCode(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
