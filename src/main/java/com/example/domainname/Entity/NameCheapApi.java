package com.example.domainname.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "ApiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameCheapApi {
    @XmlElement(name = "CommandResponse")
    private CommandResponse commandResponse;

    // Getters and setters

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CommandResponse {
        @XmlElement(name = "DomainGetListResult")
        private DomainGetListResult domainGetListResult;

        // Getters and setters
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class DomainGetListResult {
        @XmlElement(name = "Domain")
        private List<Domain> domains;

        // Getters and setters
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Domain {
        @XmlAttribute
        private String Name;
        @XmlAttribute
        private String ID;
        @XmlAttribute
        private String User;
        @XmlAttribute
        private String Created;
        @XmlAttribute
        private String Expires;
        @XmlAttribute
        private String IsExpired;
        @XmlAttribute
        private String IsLocked;
        @XmlAttribute
        private String AutoRenew;
        @XmlAttribute
        private String WhoisGuard;

        // Getters and setters
    }
}
