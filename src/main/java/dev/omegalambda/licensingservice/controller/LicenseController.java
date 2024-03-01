package dev.omegalambda.licensingservice.controller;

import dev.omegalambda.licensingservice.model.License;
import dev.omegalambda.licensingservice.service.LicenseService;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping("/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("licenseId") String licenseId,
            @PathVariable("organizationId") String organizationId
    ) {
        val license = licenseService.getLicense(licenseId, organizationId);
        return ResponseEntity.ok(license);
    }

    @PutMapping()
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license
    ) {
        val responseMsg = licenseService.updateLicense(license, organizationId);
        return ResponseEntity.ok(responseMsg);
    }

    @PostMapping()
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license
    ) {
        val responseMsg = licenseService.createLicense(license, organizationId);
        return ResponseEntity.ok(responseMsg);
    }

    @DeleteMapping("/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("licenseId") String licenseId,
            @PathVariable("organizationId") String organizationId
    ) {
        val responseMsg = licenseService.deleteLicense(licenseId, organizationId);
        return ResponseEntity.ok(responseMsg);
    }
}
