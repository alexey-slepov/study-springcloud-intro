package epam.slepov.study.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/sentence")
public class SentenceController {
    private static final Logger log = LoggerFactory.getLogger(SentenceController.class);

    //private @Autowired DiscoveryClient discoveryClient;
    private @Autowired LoadBalancerClient loadBalancerClient;

    @RequestMapping("/")
    public @ResponseBody String getSentence() {
        try {
            return "Sentence: "
                    + getWord("study-springcloud-words-server-subject")
                    + " " + getWord("study-springcloud-words-server-adjective")
                    ;
        } catch (Exception e) {
            log.error("Failed to asssemble a sentence", e);
            return "505. Ask admin to see logs";
        }
    }

    private String getWord(String serviceName) throws URISyntaxException {
        /*
        List<ServiceInstance> services = discoveryClient.getInstances(serviceName);
        if(services != null && services.size() > 0) {
            URI uri = new URI(services.get(0).getUri().toString() + "/word");
            log.info("Service [" + serviceName + "] uri: " + uri.toString());
            return (new RestTemplate()).getForObject(uri, String.class);
        }
        */
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceName);

        if(serviceInstance == null) {
            log.error("No service [" + serviceName + "] provided by a load balancer");
            return null;
        }
        URI uri = new URI(serviceInstance.getUri().toString() + "/word");
        log.info("Service [" + serviceName + ", ID=" + serviceInstance.getServiceId() + "] uri: " + uri.toString());
        return (new RestTemplate()).getForObject(uri, String.class);
    }
}
