package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomainDNSGetHostsResult {
    @JsonProperty("host")
    ArrayList<Host> host;
    @JsonProperty("IsUsingOurDNS")
    boolean isUsingOurDNS;
    @JsonProperty("Domain")
    String domain;
    @JsonProperty("EmailType")
    String emailType;
}
