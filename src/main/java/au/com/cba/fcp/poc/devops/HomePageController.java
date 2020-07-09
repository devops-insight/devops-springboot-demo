package au.com.cba.fcp.poc.devops;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class HomePageController {

    @Value("${spring.env}")
    private String env;

    @Value("${spring.env_id}")
    private String env_id;

    //@GetMapping("/home")
    @GetMapping("/login")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("env", env+"_"+env_id);
        return "home_page";
    }

}