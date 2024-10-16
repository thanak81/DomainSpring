package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommandResponse {
    @JsonProperty("Type")
    String type;
    @JsonProperty("DomainGetListResult")
    DomainGetListResult domainGetListResult;
    @JsonProperty("DomainGetInfoResult")
    DomainDetail domainGetInfoResult;
    @JsonProperty("DomainDNSGetHostsResult")
    DomainDNSGetHostsResult domainDNSGetHostsResult;
}
