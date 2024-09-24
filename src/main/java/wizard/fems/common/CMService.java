package wizard.fems.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wizard.fems.common.dto.CMCode;

import java.util.HashMap;
import java.util.List;

@Service
public class CMService {
    @Autowired
    private CMMapper mapper;

    public List<CMCode> getCmCode(String groupCode){
        return mapper.getCmCode(groupCode);
    }
    public List<HashMap<String, Object>> getPlusFinder(HashMap<String, Object> param) {
        List<HashMap<String, Object>> lstpf = mapper.getPlusFinder(param);
        return lstpf;
    }
}
