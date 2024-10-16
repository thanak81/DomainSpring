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
import com.example.domainname.Entity.NameCheapAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
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
    private WebClient webClient;
    @Override
    public String addHost() throws JsonProcessingException {
        String uri = "/xml.response";
        ArrayList<Host> getAllHost = getHosts().getCommandResponse().getDomainDNSGetHostsResult().getHost();
        MultiValueMap<String,String> formData = new LinkedMultiValueMap<>();
        formData.add("ApiUser","ksga");
        formData.add("ApiKey","e537956a6bec49a98e6e234b79bf10f2");
        formData.add("UserName","ksga");
        formData.add("ClientIp", "58.97.230.128");
        formData.add("Command", "namecheap.domains.dns.setHosts");
        formData.add("SLD", "virtualbiz");
        formData.add("TLD", "store");
        formData.add("HostName1", "@");
        formData.add("RecordType1", "A");
        formData.add("Address1", "110.74.194.125");
        formData.add("TTL1", "1900");
        formData.add("HostName2", "api");
        formData.add("RecordType2", "A");
        formData.add("Address2", "110.74.194.125");
        formData.add("TTL2", "1900");
        formData.add("HostName3", "messijr");
        formData.add("RecordType3", "A");
        formData.add("Address3", "110.74.194.125");
        formData.add("TTL3", "1900");
        System.out.println(formData);
        ArrayList<Host> allHost = new ArrayList<>();
        for (Host host : getAllHost){
            allHost.add(host);
            System.out.println(host);
        }
        MultiValueMap<String, String> hashMap = convertArrayListToMultiValueMap(allHost,"namecheap.domains.dns.setHosts");

        for (String entry : hashMap.keySet()){
            System.out.println(entry + " = " + hashMap.get(entry));
        }
        return webClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .bodyValue(formData)
                    .retrieve()
                    .bodyToMono(String.class) // Adjust to your expected response type
                    .block();
    }
    private static MultiValueMap<String,String> convertArrayListToMultiValueMap(ArrayList<Host> allHostList,String command){
        MultiValueMap<String, String> hostMap = new LinkedMultiValueMap<>();
        NameCheapAPI nameCheapAPI = new NameCheapAPI();
        for (int i=1;i<allHostList.size()+1;i++) {
            hostMap.add("ApiUser", nameCheapAPI.getApiUser());
            hostMap.add("UserName", nameCheapAPI.getApiUser());
            hostMap.add("ClientIp", nameCheapAPI.getClientIp());
            hostMap.add("Command", command);
            hostMap.add("SLD", nameCheapAPI.getSLD());
            hostMap.add("TLD", nameCheapAPI.getTLD());
            hostMap.add("HostName"+i, allHostList.get(i-1).getName());
            hostMap.add("RecordType"+i, "A");
            hostMap.add("Address"+i, "110.74.194.125");
        }
        return hostMap;
    }

}


