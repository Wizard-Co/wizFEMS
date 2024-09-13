package wizard.fems.basicMgmt.machine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wizard.fems.basicMgmt.machine.DTO.MCDTO;
import wizard.fems.common.dto.CMCode;
import wizard.fems.common.commonService;
import wizard.fems.common.dto.User;
import wizard.fems.common.security.domain.LoginUser;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basicMgmt/machine")
public class mcController {
    @Autowired
    private mcService mcService;
    @Autowired
    private commonService cmService;

    @ModelAttribute
    public void getCboMCType(Model model) {
        List<CMCode> cboMcType = cmService.getCmCode("mcType");
        List<CMCode> cboUnit = cmService.getCmCode("mcUnit");

        model.addAttribute("mcdto", new MCDTO());
        model.addAttribute("cboMcType", cboMcType);
        model.addAttribute("cboUnit", cboUnit);

        System.out.println(cboMcType);
    }

    @GetMapping("")
    public String mc(@LoginUser User user) {

        return "/pages/basicMgmt/machine/machine";
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public List<MCDTO> getMcData(@RequestBody Map<String, Object> param) {
        List<MCDTO> data = mcService.getMachineList(param);
        return data;
    }

    @PostMapping("/detail")
    public String searchDetail(@RequestParam(name = "mcID", required = true) String mcID,
                               Model model) {

        MCDTO mcdto = mcService.getMachineDetail(mcID);
        model.addAttribute("mcdto", mcdto);
        return "/pages/basicMgmt/machine/machineDetail";
    }

    @PostMapping("/add")
    public String add() {
        return "/pages/basicMgmt/machineDetail";
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@ModelAttribute MCDTO mcdto) {
        mcdto.setCreateUserID("sooJeong");
        mcService.saveMachineDetail(mcdto);
    }

    @PostMapping("/update")
    @ResponseBody
    public void update(@ModelAttribute MCDTO mcdto) {

        mcdto.setUpdateUserID("soo");
        mcService.updateMachineDetail(mcdto);
    }

    @GetMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam(name = "mcID") String mcID) {
        mcService.deleteMachine(mcID);
    }
}
