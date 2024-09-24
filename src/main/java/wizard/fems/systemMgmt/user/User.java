package wizard.fems.systemMgmt.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public String userID;
    public String personID;
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
    public String lastUpdateUserID;
    public String lastUpdateDate;
    public String cellPhoneNumber;
    public String comments;

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
