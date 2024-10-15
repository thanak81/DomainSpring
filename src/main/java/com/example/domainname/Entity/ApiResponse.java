package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName("ApiResponse")
@JacksonXmlRootElement(localName = "ApiResponse")
public class ApiResponse {
    @JsonProperty("Status")
    private String Status;
    @JsonProperty("Errors")
    private JsonNode Errors;
    @JsonProperty("Warnings")
    private String Warning;
    @JsonProperty("RequestedCommand")
    private String RequestedCommand;
    @JsonProperty("CommandResponse")
    private CommandResponse CommandResponse;
    @JsonProperty("Server")
    private String server;
    @JsonProperty("GMTTimeDifference")
    private String GMTTimeDifference;
    @JsonProperty("ExecutionTime")
    private String time;
}
