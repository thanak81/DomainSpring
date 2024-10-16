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

public class Domain {
    @JsonProperty("ID")
    String ID;
    @JsonProperty("Name")
    String Name;
    @JsonProperty("User")
    String User;
    @JsonProperty("Created")
    String created;
    @JsonProperty("Expires")
    String Expires;
    @JsonProperty("IsExpired")
    boolean IsExpired;
    @JsonProperty("IsLocked")
    boolean IsLocked;
    @JsonProperty("WhoisGuard")
    String WhoisGuard;
    @JsonProperty("IsPremium")
    boolean IsPremium;
    @JsonProperty("IsOurDNS")
    boolean IsOurDNS;
}
