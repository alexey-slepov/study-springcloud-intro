package epam.slepov.study;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class App {
    private @Value("${eureka.instance.instanceId}") String instanceId;

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
        (new App()).info();
    }

    void info() {
        System.out.println("Instance ID: " + instanceId);
    }
}