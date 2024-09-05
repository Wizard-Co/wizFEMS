package wizard.fems.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wizard.fems.common.DTO.CMCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class commonService {
    @Autowired
    private commonMapper mapper;

    public List<CMCode> getCmCode(String groupCode){
        return mapper.getCmCode(groupCode);
    }

}
