package wizard.fems.energyMon.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wizard.fems.energyMon.alarm.DTO.alarmDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class alarmService {

    @Autowired
    wizard.fems.energyMon.alarm.alarmMapper mapper;

    public List<HashMap<String, Object>> getCustom(){
        return mapper.getCustom();
    }
    public List<HashMap<String,Object>> getMachine(){return mapper.getMachine();}

    public List<alarmDTO> getAlarmList(Map<String, Object> param){
        List<alarmDTO> list = mapper.getAlarmList(param);
        return list;
    }
}
