package io.cna.acme.monolith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AcmeController {
    private static Logger log = LoggerFactory.getLogger(AcmeController.class);


    @GetMapping("/service/greet/{name}")
    @ResponseBody
    public String sayHello(@PathVariable String name, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return String.format("Hello %s! \n Use ACME's predictive stock recommendation and retire early. \n", name);
    }

    @GetMapping("/service/quote")
    @ResponseBody
    public String sayHello(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        return String.format("Today's top stock: PVTL");
    }

    @GetMapping("/kill-ai")
    public void die()
    {
        log.error("Bye bye AI, RIP!!");
        System.exit(-1);
    }
}
