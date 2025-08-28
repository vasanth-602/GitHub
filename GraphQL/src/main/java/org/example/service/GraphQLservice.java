package org.example.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface GraphQLservice {
    String executeQuery() throws IOException;
}
