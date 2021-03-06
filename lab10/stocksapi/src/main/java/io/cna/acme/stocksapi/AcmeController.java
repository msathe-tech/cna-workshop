package io.cna.acme.stocksapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;

@RestController
@RefreshScope
public class AcmeController {
    private static Logger log = LoggerFactory.getLogger(AcmeController.class);

    private final AcmeService acme;

    public AcmeController(AcmeService acme) {
        this.acme = acme;
    }

    @Value(value = "${stocksapi.banner}")
    private String banner;

    @GetMapping("/service/greet/{name}")
    public String sayHello(@PathVariable String name) {
        return String.format("Hello %s! \n %s \n", name, banner);
    }

    @GetMapping("/service/quote")
    public String getQuote() {
        System.out.println("Calling stocks-service");
        String stock = acme.recommend();
        System.out.printf("stock form http endpoint = %s%n", stock);

        return String.format("Today's top stock: %s", stock);
    }

    @GetMapping("/kill-ai")
    public void die()
    {
        log.error("Bye bye AI, RIP!!");
        System.exit(-1);
    }
}
