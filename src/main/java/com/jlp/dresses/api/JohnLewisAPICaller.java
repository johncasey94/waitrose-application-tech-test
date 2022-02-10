package com.jlp.dresses.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;

@Component
public class JohnLewisAPICaller {

    @Value("${john.lewis.api.url}")
    private String JOHN_LEWIS_API_URL;

    private final RestTemplate restTemplate;

    public JohnLewisAPICaller(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String callJohnLewisAPI() {
        // return restTemplate.getForObject(JOHN_LEWIS_API_URL, String.class);

        return readStringFromFile();
    }

    private String readStringFromFile() {
        try {
            URL resource = getClass().getClassLoader().getResource("response.json");
            File file = new File(resource.toURI());
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

}
