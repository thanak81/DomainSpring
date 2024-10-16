package com.example.domainname.Service;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.domainname.Entity.ApiResponse;
import com.example.domainname.Entity.Host;
import com.example.domainname.Entity.HostRequest;
import com.example.domainname.Entity.NameCheapAPI;
import com.example.domainname.exception.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DomainServiceImp  implements DomainService{
    @Override
    public ApiResponse getDomainName() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        RestClient defaultClient = RestClient.create();
        String domains = defaultClient.get()
                .uri("https://api.namecheap.com/xml.response?ApiUser=ksga&ApiKey=e537956a6bec49a98e6e234b79bf10f2&UserName=ksga&ClientIp=58.97.230.128&Command=namecheap.domains.getList")
                .accept(MediaType.APPLICATION_XML)
                .retrieve().body(String.class);
        // Convert XML to JSON
//        JSONObject jsonpObject = XML.toJSONObject(domains);
//        String jsonString = jsonpObject.toString();
        // COnvert JSON to POJO
//        ObjectMapper mapper = new ObjectMapper();
//        ApiResponse apiResponse = mapper.readValue(jsonString, ApiResponse.class);
        JsonNode jsonNode = xmlMapper.readTree(domains);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.treeToValue(jsonNode, ApiResponse.class);
    }

    @Override
    public ApiResponse getDomainDetail(String domainName) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        RestClient defaultClient = RestClient.create();
        String domains = defaultClient.get()
                .uri("https://api.namecheap.com/xml.response?ApiUser=ksga&ApiKey=e537956a6bec49a98e6e234b79bf10f2&UserName=ksga&ClientIp=58.97.230.128&Command=namecheap.domains.getInfo&DomainName="+domainName)
                .accept(MediaType.APPLICATION_XML)
                .retrieve().body(String.class);
        JsonNode jsonNode = xmlMapper.readTree(domains);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.treeToValue(jsonNode, ApiResponse.class);
    }

    @Override
    public ApiResponse getHosts() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        RestClient defaultClient = RestClient.create();
        String domains = defaultClient.get()
                .uri("https://api.namecheap.com/xml.response?ApiUser=ksga&ApiKey=e537956a6bec49a98e6e234b79bf10f2&UserName=ksga&ClientIp=58.97.230.128&Command=namecheap.domains.dns.getHosts&SLD=virtualbiz&TLD=store")
                .accept(MediaType.APPLICATION_XML)
                .retrieve().body(String.class);
        JsonNode jsonNode = xmlMapper.readTree(domains);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.treeToValue(jsonNode, ApiResponse.class);
    }
    @Autowired
    private final WebClient webClient;
    @Override
    public ApiResponse addHost(HostRequest hostRequest) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        String uri = "/xml.response";
        ArrayList<Host> getAllHost = getHosts().getCommandResponse().getDomainDNSGetHostsResult().getHost();
        MultiValueMap<String,String> hostData = new LinkedMultiValueMap<>();
        NameCheapAPI nameCheapAPI = new NameCheapAPI();
        hostData.add("ApiKey", nameCheapAPI.getApiKey());
        hostData.add("ApiUser", nameCheapAPI.getApiUser());
        hostData.add("UserName", nameCheapAPI.getApiUser());
        hostData.add("ClientIp", nameCheapAPI.getClientIp());
        hostData.add("Command", "namecheap.domains.dns.setHosts");
        hostData.add("SLD", nameCheapAPI.getSLD());
        hostData.add("TLD", nameCheapAPI.getTLD());

        ArrayList<Host> allHost = new ArrayList<>();
        Host newHost = new Host();
        newHost.setName(hostRequest.getName());

        for (Host host : getAllHost){
                if(newHost.getName().equals(host.getName())){
                   throw new BadRequestException("Host name already exist");
                }
                allHost.add(host);
        }
        allHost.add(newHost);
        System.out.println("realData"+allHost);

        MultiValueMap<String, String> hashMap = convertArrayListToMultiValueMap(allHost);
        hostData.addAll(hashMap);

        System.out.println("FormData"+hostData);
        String postHost = webClient.post()
        .uri(uri)
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .bodyValue(hostData)
        .retrieve()
        .bodyToMono(String.class) 
        .block();
        JsonNode jsonNode = xmlMapper.readTree(postHost);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.treeToValue(jsonNode, ApiResponse.class);
    }
    private static MultiValueMap<String,String> convertArrayListToMultiValueMap(ArrayList<Host> allHostList){
        MultiValueMap<String, String> hostMap = new LinkedMultiValueMap<>();
        for (int i=1;i<allHostList.size()+1;i++) {
            hostMap.add("HostName"+i, allHostList.get(i-1).getName());
            hostMap.add("RecordType"+i, "A");
            hostMap.add("Address"+i, "110.74.194.125");
            hostMap.add("TTL"+i, "1900");

        }
        return hostMap;
    }

}


