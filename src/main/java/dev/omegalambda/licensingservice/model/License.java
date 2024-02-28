package dev.omegalambda.licensingservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class License {
    private Long id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
