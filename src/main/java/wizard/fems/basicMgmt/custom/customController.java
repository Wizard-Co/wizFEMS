/**
*패키지명 : wizard.fems.basicMgmt.custom
*파일명: customController.java
*작성일: 2024-09-23
*개발자: daehyun
***************************************************
*변경일자            변경자             변경내용
***************************************************
 * 2024-09-19       최대현            최초작성
*/

package wizard.fems.basicMgmt.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import wizard.fems.basicMgmt.custom.DTO.customDTO;
import wizard.fems.common.commonService;
import wizard.fems.common.security.domain.LoginUser;

import java.util.List;
import java.util.Map;

@Controller
public class customController {

    @Autowired
    private customService customService;
    @Autowired
    private commonService cmService;

    @ModelAttribute
    public void getCboCustom(Model model) {
        List<wizard.fems.common.dto.CMCode> cboElecType = cmService.getCmCode("EleLTYPID");
        List<wizard.fems.common.dto.CMCode> cboElecSelect = cmService.getCmCode("ElesTYPID");
        List<wizard.fems.basicMgmt.custom.DTO.customDTO> cboAgentName = customService.getAgentName();

        model.addAttribute("customdto", new customDTO());
        model.addAttribute("cboEleLType", cboElecType);
        model.addAttribute("cboElesSelect", cboElecSelect);
        model.addAttribute("cboAgentName", cboAgentName);

    }

    @GetMapping("/basicMgmt/custom")
    public String custom(@LoginUser wizard.fems.common.dto.User user) {

        return "/pages/basicMgmt/custom/custom";
    }

    @PostMapping(value = "/basicMgmt/custom/search")
    @ResponseBody
    public List<customDTO> getCustomList(@RequestBody Map<String, Object> param) {
        List<customDTO> data = customService.getCustomList(param);
        return data;
    }


    @PostMapping("/basicMgmt/custom/add")
    public String add(Model model) {
        customDTO customdto = new customDTO();
        customdto.setBusinessTypeCode("01"); // 기본값 설정
        customdto.setUseYN("Y");
        model.addAttribute("customdto", customdto);
        return "/pages/basicMgmt/custom/customDetail";
    }


    @PostMapping("/basicMgmt/custom/detail")
    public String searchDetail(@RequestParam(name = "customID", required = true) String customID,
                               Model model) {

        customDTO customdto = customService.getCustomDetail(customID);
        model.addAttribute("customdto", customdto);

        return "/pages/basicMgmt/custom/customDetail";
    }

    @PostMapping("/basicMgmt/custom/save")
    @ResponseBody
    public void save(@ModelAttribute customDTO customdto) {
        customdto.setCreateUserID("admin");
        customService.saveCustomDetail(customdto);
    }

    @PostMapping("/basicMgmt/custom/update")
    @ResponseBody
    public void update(@ModelAttribute customDTO customdto) {
        customdto.setLastUpdateUserID("admin");
        customService.updateCustomDetail(customdto);
    }

    @GetMapping("/basicMgmt/custom/delete")
    @ResponseBody
    public String delete(@RequestParam(name = "customID") String customID) {
        try {
            int result = customService.deleteCustomID(customID);
            if(result == 0) return "대리점으로 등록한 업체가 있습니다. 삭제 할 수 없습니다.";
            else if(result == -1) return "업체정보를 찾을 수 없습니다. 삭제 할 수 없습니다.";
            else return "삭제 되었습니다.";
        } catch (IllegalStateException e) {
            return e.getMessage();
        }
    }
}
