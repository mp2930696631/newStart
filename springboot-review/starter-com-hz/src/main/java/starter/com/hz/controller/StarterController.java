package starter.com.hz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import starter.com.hz.service.StarterService;

/**
 * @author zehua
 * @date 2020/11/24 20:32
 */
@Controller
public class StarterController {
    @Autowired
    private StarterService starterService;

    @RequestMapping("/testStarter")
    @ResponseBody
    public StarterService testStarter() {

        return starterService;
    }
}
