package wizard.fems.enMonit.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class alarmService {

    @Autowired
    alarmMapper mapper;

    public List<HashMap<String, Object>> getCustom(){
        return mapper.getCustom();
    }
    public List<HashMap<String,Object>> getMachine(){return mapper.getMachine();}
}
