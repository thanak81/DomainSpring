package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class DomainDetail {
    @JsonProperty("ID")
    String ID;
    @JsonProperty("Status")
    String status;
    @JsonProperty("DomainName")
    String domainName;
    @JsonProperty("OwnerName")
    String ownerName;
//    @JsonProperty("Created")
//    String created;
//    @JsonProperty("Expires")
//    String Expires;
//    @JsonProperty("IsExpired")
//    boolean IsExpired;
//    @JsonProperty("IsLocked")
//    boolean IsLocked;
//    @JsonProperty("WhoisGuard")
//    String WhoisGuard;
//    @JsonProperty("IsPremium")
//    boolean IsPremium;
//    @JsonProperty("IsOurDNS")
//    boolean IsOurDNS;
}
