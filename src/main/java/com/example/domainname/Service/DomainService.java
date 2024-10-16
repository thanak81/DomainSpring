package com.example.domainname.Service;

import com.example.domainname.Entity.ApiResponse;
import com.example.domainname.Entity.HostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface DomainService {
    ApiResponse getDomainName () throws IOException;
    ApiResponse getDomainDetail(String domainName) throws IOException;

    ApiResponse getHosts() throws JsonProcessingException;
    ApiResponse addHost(HostRequest hostRequest) throws JsonProcessingException;
}
