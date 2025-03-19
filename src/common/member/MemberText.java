package common.member;

/*
    사용자에게 출력할 모든 text를 미리 정의하는 enum 클래스입니다.
 */
public enum MemberText {
    MENU_HEADER("\n====================================\n[회원 관리 시스템]\n===================================="),

    /*MEMBER FILED */
    MEMBER_NO("번호 : "),
    MEMBER_NAME("이름 : "),
    MEMBER_AUTHORITYID("권한ID : "),
    MEMBER_PHONE("전화번호 : "),
    MEMBER_EMAIL("이메일 : "),
    MEMBER_ADDRESS("주소 : "),
    MEMBER_ID("ID : "),
    MEMBER_PASSWORD("비밀번호 : "),
    MEMBER_LOGSTATUS("로그인 상태 : "),

    /*MEMBER REQUEST FILED*/
    MEMBER_REQUEST("회원가입 요청 상태 : "),

    /*회원 등록*/
    INSERT_MEMBER_HEADER("=== 회원 등록 ==="),
    INSERT_MEMBER_NEW_HEADER("=== 신규회원 등록 ==="),
    INSERT_MEMBER_APPROVE_HEADER("=== 가입 신청 목록 ==="),
    INSERT_MEMBER("등록할 회원 "),
    INSERT_MEMBER_SUCCESS("회원 등록 성공"),

    /*회원 삭제*/
    DELETE_MEMBER_HEADER("=== 회원 삭제 ==="),
    DELETE_MEMBER("삭제할 회원"),
    DELETE_MEMBER_SUCCESS("회원 정보 삭제 성공"),

    /*회원 수정*/
    UPDATE_MEMBER_HEADER("=== 회원 정보 수정 ==="),
    UPDATE_MEMBER_SUCCESS("회원 정보 수정 성공"),
    UPDATE_MEMBER("수정할 회원 "),


    /*회원 조회*/
    SEARCH_MEMBER_SIMPLE_HEADER("=== 회원 정보 간편 조회==="),
    SEARCH_MEMBER_DETAIL_HEADER("=== 회원 정보 상세 조회==="),
    SEARCH_MEMBER_ALL_HEADER("===전체 회원 정보 조회==="),
    SEARCH_MEMBER_AUTHORITY_HEADER("=== 권한별 회원 정보 조회==="),
    SEARCH_MEMBER_ID("조회할 ID : "),
    SEARCH_MEMBER_AUTHORITY("검색할 권한 : "),

    /** 총관리자 회원관리 메인메뉴 */
    HQ_MEMBER_MENU("\n[본사 관리자 > 회원 관리]\n" +
            "1. 회원 등록\n" +
            "2. 회원 수정\n" +
            "3. 회원 삭제\n" +
            "4. 회원 조회\n" +
            "\n메뉴를 선택하세요(\"exit\"입력 시 종료): "),


    /** 창고관리자 회원관리 메인메뉴 */
    WM_MEMBER_MENU("\n[창고 관리자 > 회원 관리]\n" +
            "1. 회원 조회\n" +
            "2. 회원 수정\n" +
            "\n메뉴를 선택하세요(\"exit\"입력 시 종료): "),

  /** 가맹점주 회원관리 메인메뉴 */
    FM_MEMBER_MENU("\n[가맹점주 > 회원 관리]\n" +
            "1. 내 정보 조회\n" +
            "2. 내 정보 수정\n" +
            "3. 회원 탈퇴 \n"+
          "5. 뒤로 가기(\"exit\"입력)"+
                  "\n메뉴를 선택하세요(\"exit\"입력 시 종료): "),


    /** 본사 관리자 조회 메뉴 */
    HQ_MEMBER_SEARCH_MENU("\n[회원 관리 > 회원조회]\n" +
                           "1. 간편 조회\n" +
                           "2. 상세 조회\n" +
                           "3. 권한별 회원 조회 \n" +
                           "4. 전체회원 조회\n" +
            "\n메뉴를 선택하세요(\"exit\"입력 시 종료): "),

    /** 본사 관리자 조회 메뉴 */
    WM_MEMBER_SEARCH_MENU("\n[창고 관리자 > 회원 관리 > 회원조회]\n" +
                                  "1. 간편 조회\n" +
                                  "2. 상세 조회\n" +
                                  "3. 전체회원 조회\n" +
            "\n메뉴를 선택하세요(\"exit\"입력 시 종료): "),


    /** 본사 관리자 조회 메뉴 */
    HQ_MEMBER_ADD_MENU("\n[본사 관리자> 회원 관리 > 회원등록]\n" +
                                  "1 신규관리자 등록\n" +
                                  "2. 가맹점주 회원가입 승인\n" +
                                  "\n메뉴를 선택하세요(\"exit\"입력 시 종료):"),


    /** 가맹점주 조회 메뉴 */
    FM_MEMBER_SEARCH_MENU("\n[가맹점주> 회원 관리 > 회원조회]\n" +
                                "1. 간편 조회\n" +
                                "2. 상세 조회\n" +
                                "\n메뉴를 선택하세요(\"exit\"입력 시 종료): ");


    private final String text;

    MemberText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
