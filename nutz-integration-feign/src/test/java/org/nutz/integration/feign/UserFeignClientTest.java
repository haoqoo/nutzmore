package org.nutz.integration.feign;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.util.List;
import java.util.Map;

/**
 *
 */
public class UserFeignClientTest {

    public static void main(String[] args) {
        UserFeignClient userFeignClient = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(UserFeignClient.class, "http://localhost:8080");
        // Fetch and print a list of the contributors to this library.
        Map index = userFeignClient.index();
        List<Map> list = userFeignClient.list("1");
        System.out.println("");
    }
}
