package wizard.fems.energyMon.alarm;

import org.apache.ibatis.annotations.Mapper;
import wizard.fems.enMonit.alarm.DTO.alarmDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface alarmMapper {

    List<HashMap<String,Object>> getCustom();
    List<HashMap<String,Object>> getMachine();
    List<alarmDTO> getAlarmList(Map<String,Object> param);
}
