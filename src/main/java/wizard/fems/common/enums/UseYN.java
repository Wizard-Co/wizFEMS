package wizard.fems.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName      : wizard.fems.common.DTO
 * fileName         : UseYN
 * author           : sooJeong
 * date             : 2024-08-30
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-08-30         sooJeong             최초 생성
 */
@Getter
@AllArgsConstructor
public enum UseYN {
    Y("", true),
    N("*", false);

    private String strValue;
    private boolean boolValue;

}
