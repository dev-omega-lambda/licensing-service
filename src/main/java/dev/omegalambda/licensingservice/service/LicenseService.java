package dev.omegalambda.licensingservice.service;

import dev.omegalambda.licensingservice.model.License;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class LicenseService {
    public License getLicense(String licenseId, String organizationId) {
        return License.builder()
                .id(new Random().nextLong(1000))
                .licenseId(licenseId)
                .organizationId(organizationId)
                .description("Software Product")
                .productName("O-stock")
                .licenseType("full")
                .build();
    }

    public String createLicense(License license, String organizationId) {
        if (license != null) {
            license.setOrganizationId(organizationId);
            return String.format("This is the post and the object is: %s", license);
        }
        return null;
    }

    public String updateLicense(License license, String organizationId) {
        if (license != null) {
            license.setOrganizationId(organizationId);
            return String.format("This is the put and the object is: %s", license);
        }
        return null;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        return String.format("Deleting license with id %s for the organization id %s", licenseId, organizationId);
    }
}
