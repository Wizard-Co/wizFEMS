package wizard.fems.common.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName      : wizard.fems.common.security.DTO
 * fileName         : Role
 * author           : sooJeong
 * date             : 2024-09-09
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-09         sooJeong             최초 생성
 */

@Getter
@AllArgsConstructor
public enum Role {
    USER("01","사용자"),
    ADMIN("02","관리자");

    private String authTypeID;
    private String authType;

}
