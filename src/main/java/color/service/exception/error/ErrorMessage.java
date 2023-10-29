package color.service.exception.error;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessage {
    COMPANY_NOT_FOUND_ERROR("존재하지 않는 회사입니다."),
    USER_NOT_FOUND_ERROR("존재하지 않는 사용자입니다."),
    BOARD_NOT_FOUND_ERROR("존재하지 않는 게시판입니다."),
    UNAUTHORIZED_ERROR("권한이 없습니다."),
    USER_ALREADY_EXISTS_ERROR("이미 존재하는 계정입니다"),
    USER_INFO_NOT_CORRECT("아이디 또는 비밀번호가 올바르지 않습니다");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @JsonValue
    public String getMessage() {
        return message;
    }
}
