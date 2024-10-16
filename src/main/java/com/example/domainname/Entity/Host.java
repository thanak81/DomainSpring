package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class Host {
    @JsonProperty("HostId")
    private String hostId;
    @JsonProperty("Name")
    private String Name;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Address")
    private String address;
    @JsonProperty("MXPref")
    private String mxPref;
    @JsonProperty("TTL")
    private String TTL;
    @JsonProperty("IsActive")
    private boolean isActive;
    @JsonProperty("IsUsingOurDNS")
    private boolean isUsingOurDNS;
}
