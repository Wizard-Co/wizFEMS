package wizard.fems.common.DTO;

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
public enum UseYN {
    Y("", true),
    N("*", false);

    private String strValue;
    private boolean boolValue;

    private UseYN(String strValue, boolean boolValue){
        this.strValue = strValue;
        this.boolValue = boolValue;
    }

}
