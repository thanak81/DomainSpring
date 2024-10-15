package com.example.domainname.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandResponse {
    @JsonProperty("type")
    String type;
    @JsonProperty("domainGetListResult")
    DomainGetListResult domainGetListResult;
}
