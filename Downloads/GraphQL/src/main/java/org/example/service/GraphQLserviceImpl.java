package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

@Service
@Slf4j
public class GraphQLserviceImpl implements GraphQLservice{

    @Value("${adapter.base.url}")
    private String getBaseURL;

    private final RestTemplate restTemplate;

    public GraphQLserviceImpl(){
        this.restTemplate=new RestTemplate();
    }

    @Override
    public String executeQuery() throws IOException {
        ClassPathResource resource = new ClassPathResource("GraphQL/" + "getCharacterByID.graphql");
        String query = new String(Files.readAllBytes(Paths.get(resource.getURI())));
        String requestjson="{\"query\":\""+query.replace("\"","\\\"")+"\"}";
        log.info("Request GraphQL : {}", requestjson);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =new HttpEntity<>(requestjson, headers);
        String response= restTemplate.postForObject(getBaseURL,request,String.class);
        log.info("Response json : {}",response);
        return response;
    }
}
