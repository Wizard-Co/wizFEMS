package wizard.fems.systemMgmt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import wizard.fems.basicMgmt.machine.DTO.MCDTO;
import wizard.fems.common.dto.CMCode;
import wizard.fems.common.security.domain.LoginUser;
import wizard.fems.common.security.enums.Role;

import java.util.List;
import java.util.Map;

/**
 * packageName      : wizard.fems.serviceMgmt.user
 * fileName         : UserController
 * author           : sooJeong
 * date             : 2024-09-13
 * ======================================================
 * DATE             AUTHOR               NOTE
 * ------------------------------------------------------
 * 2024-09-13         sooJeong             최초 생성
 */
@Controller
@RequestMapping("/systemMgmt/user")
public class UserController {

    @Autowired
    UserService userService;

    @ModelAttribute
    public void setting(Model model) {
        Role[] cboUserType = Role.values();

        model.addAttribute("user", new User());
        model.addAttribute("cboUserType", cboUserType);
    }

    @GetMapping("")
    public String userHome(@LoginUser User user){
        return "/pages/systemMgmt/user/user";
    }

    @PostMapping(value = "/search")
    @ResponseBody
    public List<User> getMcData(@RequestBody Map<String, Object> param) {
        List<User> data = userService.getUserList(param);
        return data;
    }

    @PostMapping("/detail")
    public String searchDetail(@RequestParam(name = "userID", required = true) String userID,
                               Model model) {

        User user = userService.getUserDetail(userID);
        model.addAttribute("user", user);
        return "/pages/systemMgmt/user/userDetail";
    }

    @PostMapping("/add")
    public String add() {
        return "/pages/systemMgmt/user/userDetail";
    }

    @PostMapping("/save")
    @ResponseBody
    public void save(@ModelAttribute User user) {
        user.setCreateUserID("sooJeong");
        userService.saveUserDetail(user);
    }

    @PostMapping("/update")
    @ResponseBody
    public void update(@ModelAttribute User user) {

        user.setLastUpdateUserID("soo");
        userService.updateUserDetail(user);
    }

    @GetMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam(name = "userID") String userID) {
        userService.deleteUser(userID);
    }

}
