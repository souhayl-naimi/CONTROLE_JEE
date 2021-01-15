package io.naimi.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableHystrix
public class GatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

      @Bean
    RouteLocator staticRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r-> r
                        .path("/publicCountries/**")
                        .filters(f-> f
                                .addRequestHeader("x-rapidapi-host","restcountries-v1.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key","5967648c04mshd3233a1a36780c5p130f7fjsn1e2545b6f454")
                                .addRequestHeader("useQueryString","true")
                                .rewritePath("publicCountries/(?<segment>/?.*)","/${segment}")
                        )
                        .uri("https://restcountries-v1.p.rapidapi.com")
                        .id("r1"))
                .route(r-> r
                        .path("/muslim/**")
                        .filters(f-> f
                                .addRequestHeader("x-rapidapi-host","muslimsalat.p.rapidapi.com")
                                .addRequestHeader("x-rapidapi-key","5967648c04mshd3233a1a36780c5p130f7fjsn1e2545b6f454")
                                .addRequestHeader("useQueryString","true")
                                .rewritePath("muslim/(?<segment>/?.*)","/${segment}")
                                .hystrix(h->h.setName("countries").setFallbackUri("forward:/default"))
                        )
                        .uri("https://muslimsalat.p.rapidapi.com/")
                        .id("r2"))
                .build();

    }

    @Bean
    DiscoveryClientRouteDefinitionLocator dynamicRoutes(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }
}

@RestController
class HystrixCircuitBreakerController{
    @RequestMapping(value = "default",method = RequestMethod.GET)
    public Map<String, String> countries(){
        Map<String,String> data = new HashMap<>();
        data.put("message","default response after error occurs");
        return data;
    }
}