package wizard.fems.enMonit.alarm;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface alarmMapper {

    List<HashMap<String,Object>> getCustom();
    List<HashMap<String,Object>> getMachine();
}
