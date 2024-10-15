package com.example.domainname.Service;

import com.example.domainname.Entity.ApiResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.json.XML;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import org.json.JSONObject;
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
        ApiResponse apiResponse = objectMapper.treeToValue(jsonNode, ApiResponse.class);
        return apiResponse;
    }

    @Override
    public JsonNode getDomainDetail() throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        RestClient defaultClient = RestClient.create();
        String domains = defaultClient.get()
                .uri("https://api.namecheap.com/xml.response?ApiUser=ksga&ApiKey=e537956a6bec49a98e6e234b79bf10f2&UserName=ksga&ClientIp=58.97.230.128&Command=namecheap.domains.getInfo&DomainName=virtualbiz.store")
                .accept(MediaType.APPLICATION_XML)
                .retrieve().body(String.class);
        assert domains != null;
        return xmlMapper.readTree(domains.getBytes());
    }
}


