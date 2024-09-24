package wizard.fems.basicMgmt.machine.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class MCDTO {
    public String mcID;
    public String mcName;
    public String mcNo;
    public String mcTypeID;
    public String mcType;
    public String installDate;
    public String vendorName;
    public String manufactureName;
    public String contacts;
    public float elecCapacity;
    public String elecCapacityUnit;
    public String elecCapacityUnitClss;
    public float steamCapacity;
    public String steamCapacityUnit;
    public String steamCapacityUnitClss;
    public String installCustomID;
    public String installCustom;
    public String createUserID;
    public String createDate;
    public String lastUpdateUserID;
    public String lastUpdateDate;
    public String userName;

}
