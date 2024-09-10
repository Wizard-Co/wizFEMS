package wizard.fems.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class commonController {
    int nLarge = 0;

    @Autowired
    private commonService service;

}
