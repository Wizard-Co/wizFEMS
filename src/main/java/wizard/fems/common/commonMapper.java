package wizard.fems.common;

import org.apache.ibatis.annotations.Mapper;
import wizard.fems.common.dto.CMCode;
import wizard.fems.common.dto.User;

import java.util.List;

@Mapper
public interface commonMapper {
    List<CMCode> getCmCode(String groupCode);
    User getUser(String id);
}
