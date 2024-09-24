package wizard.fems.enMonit.alarm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class alarmController {

    @GetMapping("/enMonit/alarm")
    public String custom() {

        return "/pages/enMonit/alarm/alarm";
    }
}
