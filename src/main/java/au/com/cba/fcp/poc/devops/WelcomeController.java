package au.com.cba.fcp.poc.devops;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WelcomeController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from DevOps Team. Get Set Go..!";
    }

}