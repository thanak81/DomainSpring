package com.example.domainname.Service;

import com.example.domainname.Entity.ApiResponse;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public interface DomainService {
    ApiResponse getDomainName () throws IOException;
    JsonNode getDomainDetail() throws IOException;
}
