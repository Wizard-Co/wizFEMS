package wizard.fems.common;

import org.apache.ibatis.annotations.Mapper;
import wizard.fems.common.DTO.CMCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface commonMapper {
    List<CMCode> getCmCode(String groupCode);
}
