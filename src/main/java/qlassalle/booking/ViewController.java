package qlassalle.booking;


import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class ViewController {

    private String appMode;

    public ViewController(Environment environment) {
        this.appMode = environment.getProperty("app-mode");
    }

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("datetime", new Date());
        model.addAttribute("username", "Quentin Lassalle");
        model.addAttribute("mode", appMode);

        return "index";
    }
}
