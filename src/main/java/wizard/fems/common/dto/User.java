package wizard.fems.common.dto;

import lombok.Data;

/**
 * packageName      : wizard.fems.common.security.DTO
 * fileName         : User
 * author           : sooJeong
 * date             : 2024-09-09
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-09         sooJeong             최초 생성
 */

@Data
public class User {
    public String id;
    public String name;
    public String password;
    public String departID;
    public String authTypeID;
    public String authType;
    public String phoneNumber;
    public String email;
    public String startDate;
    public String endDate;
    public String useYN;
    public String createUserID;
    public String createDate;
    public String updateUserID;
    public String updateDate;

}
