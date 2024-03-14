package dev.omegalambda.licensingservice.service;

import java.util.Locale;
import java.util.Random;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import dev.omegalambda.licensingservice.model.License;

@Service
public class LicenseService {

    private final MessageSource messageSource;

    public LicenseService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @SuppressWarnings("null")
    public String createLicense(License license, String organizationId, Locale locale) {
        if (license != null) {
            license.setOrganizationId(organizationId);
            return String.format(messageSource.getMessage("license.create.message", null, locale), license);
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
