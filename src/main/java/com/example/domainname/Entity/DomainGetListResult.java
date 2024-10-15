package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DomainGetListResult {
    @JsonProperty("getDomainList")
    ArrayList<Domain> getDomainList;
}
