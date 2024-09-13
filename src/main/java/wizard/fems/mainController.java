package wizard.fems;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wizard.fems.basicMgmt.machine.DTO.MCDTO;

/**
 * packageName      : wizard.fems
 * fileName         : mainController
 * author           : sooJeong
 * date             : 2024-08-28
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-08-28         WizUser11             최초 생성
 */

@Controller
public class mainController {

    @GetMapping("/")
    public String main(Model model){
        return "pages/main";
    }

    @GetMapping("/login")
    public String login(){
        return "/pages/common/login";
    }

}
