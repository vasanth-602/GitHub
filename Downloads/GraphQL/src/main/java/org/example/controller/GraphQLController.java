package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.GraphQLservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/graphql")
@Slf4j
public class GraphQLController {
    private GraphQLservice graphQLService;

    @Autowired
    public GraphQLController(GraphQLservice graphQLService) {
        this.graphQLService=graphQLService;
    }

    @GetMapping("/character")
    public ResponseEntity<String> getCharacter() throws IOException {
        //String query = "query{ character(characterId:2137){ name type gender status location { name dimension } } }";
        String response = graphQLService.executeQuery();
        return ResponseEntity.ok(response);
    }
}
