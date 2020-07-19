package au.com.cba.fcp.poc.devops;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class WelcomeController {
    @Value("${spring.env}")
    private String env;

    @RequestMapping("/welcome")
    public String index() {
        String message =  String.format("Greetings from DevOps Team. You are in '%s' Environment :: Get Set Go..!", env);
        return message;
    }

}