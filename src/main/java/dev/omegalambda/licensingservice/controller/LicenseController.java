package dev.omegalambda.licensingservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.omegalambda.licensingservice.model.License;
import dev.omegalambda.licensingservice.service.LicenseService;
import lombok.val;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @SuppressWarnings("null")
    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("licenseId") String licenseId,
            @PathVariable("organizationId") String organizationId) {
        val license = licenseService.getLicense(licenseId, organizationId);

        if (Objects.isNull(license)) {
            return ResponseEntity.notFound().build();
        }

        license.add(
                linkTo(methodOn(LicenseController.class)
                        .getLicense(organizationId, license.getLicenseId())).withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .createLicense(organizationId, license, null)).withSelfRel(),
                linkTo(methodOn(LicenseController.class)
                        .updateLicense(organizationId, license)).withSelfRel(),
                linkTo(methodOn(LicenseController.class).deleteLicense(licenseId, organizationId)).withSelfRel());

        return ResponseEntity.ok(license);
    }

    @PutMapping()
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license) {
        val responseMsg = licenseService.updateLicense(license, organizationId);
        return ResponseEntity.ok(responseMsg);
    }

    @PostMapping()
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        val responseMsg = licenseService.createLicense(license, organizationId, locale);
        return ResponseEntity.ok(responseMsg);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("licenseId") String licenseId,
            @PathVariable("organizationId") String organizationId) {
        val responseMsg = licenseService.deleteLicense(licenseId, organizationId);
        return ResponseEntity.ok(responseMsg);
    }

    public static void main(String[] args) {
        dumb();
    }

    private static void dumb() {
        val list = new ArrayList<>(List.of("bruno", "referrer", "oliveira"));
        list.forEach(System.out::println);
    }
}
