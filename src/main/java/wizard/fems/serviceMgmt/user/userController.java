package wizard.fems.serviceMgmt.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import wizard.fems.common.security.enums.Role;

/**
 * packageName      : wizard.fems.serviceMgmt.user
 * fileName         : userController
 * author           : sooJeong
 * date             : 2024-09-13
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-13         sooJeong             최초 생성
 */
@Controller
@RequestMapping("/systemMgmt/user")
public class userController {

    @ModelAttribute("cboUserType")
    public Role[] cboUserType(){
        return Role.values();
    }

    @GetMapping("")
    public String userHome(){
        return "/pages/systemMgmt/user/user";
    }
}
