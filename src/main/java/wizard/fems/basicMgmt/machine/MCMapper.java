package wizard.fems.basicMgmt.machine;

import org.apache.ibatis.annotations.Mapper;
import wizard.fems.basicMgmt.machine.DTO.MCDTO;

import java.util.List;
import java.util.Map;

@Mapper
public interface MCMapper {
    List<MCDTO> getMachineList(Map<String, Object> param);
    MCDTO getMachineDetail(String mcID);
    MCDTO saveMachineDetail(MCDTO mcdto);
    MCDTO updateMachineDetail(MCDTO mcdto);
    void deleteMachine(String mcID);

}
