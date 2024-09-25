package wizard.fems.enMonit.alarm.DTO;

import lombok.Data;

@Data
public class alarmDTO {

    public String logID;
    public String alarmCodeID;
    public String alarmInfo;
    public String alarmType;
    public String mcID;
    public String mcName;
    public String workDate;
    public String workTime;
    public String comments;

    public String createUserID;
    public String lastupdateUserID;
}
