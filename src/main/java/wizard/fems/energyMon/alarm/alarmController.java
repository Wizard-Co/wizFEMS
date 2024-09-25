package wizard.fems.energyMon.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import wizard.fems.common.CMService;
import wizard.fems.enMonit.alarm.DTO.alarmDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class alarmController {

    @Autowired
    alarmService alarmservice;

    @Autowired
    CMService   cmService;

    @ModelAttribute
    public void getCboCustom(Model model) {

        List<HashMap<String, Object>> cboCustom = alarmservice.getCustom();
        List<HashMap<String, Object>> cboMachine = alarmservice.getMachine();
        List<wizard.fems.common.dto.CMCode> cboAlarmType = cmService.getCmCode("alarmType");

        model.addAttribute("cboCustom", cboCustom);
        model.addAttribute("cboMachine", cboMachine);
        model.addAttribute("cboAlarmType", cboAlarmType);

    }


    @GetMapping("/enMonit/alarm")
    public String alarm() {

        return "/pages/enMonit/alarm/alarm";
    }

    @PostMapping(value = "/enMonit/alarm/search")
    @ResponseBody
    public List<alarmDTO> getAlarmList(@RequestBody Map<String, Object> param) {
        List<alarmDTO> data = alarmservice.getAlarmList(param);
        return data;
    }

}
