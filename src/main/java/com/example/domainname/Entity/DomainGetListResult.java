package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)

public class DomainGetListResult {
    @JsonProperty("Domain")
    ArrayList<Domain> getDomainList;
//    @JsonProperty("DomainGetListResult")
//    Domain getDomainDetail;
}
