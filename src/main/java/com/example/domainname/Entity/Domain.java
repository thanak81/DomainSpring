package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Domain {
    String ID;
    String Name;
    String User;
    String created;
    String Expires;
    boolean IsExpired;
    boolean IsLocked;
    String WhoisGuard;
    boolean IsPremium;
    boolean IsOurDNS;
}
