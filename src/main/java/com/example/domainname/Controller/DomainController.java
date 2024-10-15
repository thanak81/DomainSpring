package com.example.domainname.Controller;

import com.example.domainname.Entity.ApiResponse;
import com.example.domainname.Response.ApiVersion;
import com.example.domainname.Service.DomainService;
import com.example.domainname.exception.NotFoundException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Locale;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/domains/")
public class DomainController {
    private final DomainService domainService;
    @GetMapping("/")
    ResponseEntity<?> getDomainName() throws IOException{
        ApiResponse namecheapApiResponse = domainService.getDomainName();
        if(!namecheapApiResponse.getErrors().findValue("").asText().isEmpty()){
            System.out.println("Errorr");
            throw new NotFoundException("Error From NameCheap API "+namecheapApiResponse.getErrors().findValue("").asText());
        }
        ApiVersion<?> apiVersion;
        apiVersion = ApiVersion.builder()
                .message("Successfully GET ALL domain")
//                .payload(objectNode.get("CommandResponse"))
                .payload(namecheapApiResponse.getErrors().findValue(""))
                .status(HttpStatus.FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiVersion);
    }
    @GetMapping("/getDetail")
    ResponseEntity<?> getDetailDomain() throws IOException{
        JsonNode detail = domainService.getDomainDetail();
        ApiVersion<?> apiVersion;
        apiVersion = ApiVersion.builder()
                .message("Successfully GET ALL domain")
                .payload(detail)
                .status(HttpStatus.FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiVersion);
    }
}
