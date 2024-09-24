package wizard.fems.common;

import org.apache.ibatis.annotations.Mapper;
import wizard.fems.common.dto.CMCode;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CMMapper {
    List<CMCode> getCmCode(String groupCode);
    List<HashMap<String, Object>> getPlusFinder(HashMap<String, Object> param);
}
