package com.example.domainname.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.domainname.Entity.ApiResponse;
import com.example.domainname.Entity.Host;
import com.example.domainname.Entity.HostRequest;
import com.example.domainname.Response.ApiVersion;
import com.example.domainname.Service.DomainService;
import com.example.domainname.exception.BadRequestException;
import com.example.domainname.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/domains/")
public class DomainController {
    private final DomainService domainService;
    @GetMapping("/")
    ResponseEntity<?> getDomainName() throws IOException{
        ApiResponse namecheapApiResponse = domainService.getDomainName();
        if(!namecheapApiResponse.getErrors().isEmpty()){
            System.out.println("Error");
            throw new NotFoundException("Error From NameCheap API "+namecheapApiResponse.getErrors());
        }
        ApiVersion<?> apiVersion;
        apiVersion = ApiVersion.builder()
                .message("Successfully GET ALL domain")
//                .payload(objectNode.get("CommandResponse"))
                .payload(namecheapApiResponse)
                .status(HttpStatus.FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiVersion);
    }
    @GetMapping("/getDetail")
    ResponseEntity<?> getDetailDomain(@RequestParam String domainName) throws IOException{
        ApiResponse domainDetail = domainService.getDomainDetail(domainName);
        if(!domainDetail.getErrors().isEmpty()){
            System.out.println("Error");
            throw new NotFoundException("Error From NameCheap API "+domainDetail.getErrors());
        }
        ApiVersion<?> apiVersion;
        apiVersion = ApiVersion.builder()
                .message("Successfully Get Domain detail")
                .payload(domainDetail)
                .status(HttpStatus.FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiVersion);
    }

    @GetMapping("/getHost")
    ResponseEntity<?> getHost() throws IOException{
        ApiResponse hostDetail = domainService.getHosts();
        if(!hostDetail.getErrors().isEmpty()){
            System.out.println("Error");
            throw new NotFoundException("Error From NameCheap API "+hostDetail.getErrors());
        }
        ArrayList<Host> allHost = hostDetail.getCommandResponse().getDomainDNSGetHostsResult().getHosts();
        if(allHost == null){
            throw new NotFoundException("Sub Domain is empty");
        }
        ApiVersion<?> apiVersion;
        apiVersion = ApiVersion.builder()
                .message("Successfully GET ALL Host for "+ hostDetail.getCommandResponse().getDomainDNSGetHostsResult().getDomain())
                .payload(allHost)
                .status(HttpStatus.FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiVersion);
    }
    @PostMapping("/host")
    ResponseEntity<?> addHost(@RequestBody HostRequest hostRequest) throws JsonProcessingException {
        ApiResponse hostResponse = domainService.addHost(hostRequest);
        if (!hostResponse.getErrors().isEmpty()){
            throw new BadRequestException("Error From NameCheap API "+hostResponse.getErrors());
        }
        ApiVersion<?> apiVersion;
        apiVersion = ApiVersion.builder()
                .message("New Host add successfully")
                .payload(hostResponse)
                .status(HttpStatus.FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiVersion);
    }

    @DeleteMapping("/host")
    ResponseEntity<?> removeHost(@RequestBody HostRequest hostRequest) throws JsonProcessingException{
        ApiResponse hostResponse = domainService.removeHost(hostRequest);
        if (!hostResponse.getErrors().isEmpty()){
            throw new BadRequestException("Error From NameCheap API "+hostResponse.getErrors());
        }
        ApiVersion<?> apiVersion;
        apiVersion = ApiVersion.builder()
                .message("Host delete successfully")
                .payload(hostResponse)
                .status(HttpStatus.FOUND)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiVersion);
    }

}
