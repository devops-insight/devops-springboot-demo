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

    @Value("${spring.release_no}")
    private String release_no;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("env", env);
        model.addAttribute("release_no", release_no);
        return "home_page";
    }
}