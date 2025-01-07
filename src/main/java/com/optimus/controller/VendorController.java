package com.optimus.controller;

import com.optimus.dto.VendorDto;
import com.optimus.model.AbstractUuidPersistable;
import com.optimus.model.Deal;
import com.optimus.model.UserLookupInformation;
import com.optimus.service.DealService;
import com.optimus.service.VendorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/vendor")
public class VendorController extends AbstractUuidPersistable {

    private final transient VendorService vendorService;

    private final transient DealService dealService;

    private static final Integer NO_LOCAL_DEAL_FOUND = 551;

    public VendorController(VendorService vendorService, DealService dealService) {
        this.vendorService = vendorService;
        this.dealService = dealService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VendorController that = (VendorController) o;
        return Objects.equals(vendorService, that.vendorService) &&
                Objects.equals(dealService, that.dealService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendorService, dealService);
    }

    @GetMapping
    public List<VendorDto> getAllLocation() {
        return vendorService.findByUserLocation();
    }

    @PostMapping("/user")
    public List<VendorDto> getAllLocationByLocation(@RequestBody UserLookupInformation user) {
        return vendorService.getAllLocationByLocationAndUserValidation(
                user.getLatitude(), user.getLongitude());
    }

    @PostMapping("/userRequest")
    public ResponseEntity<Map<String, Object>> getAllLocationByUser(
            @RequestBody UserLookupInformation userLookupInformation) {
        List<Deal> deals =
                dealService.findActiveDeals(
                        userLookupInformation.getLatitude(), userLookupInformation.getLongitude());
        List<VendorDto> vendors =
                dealService.findActiveVendorsByDeal(
                        userLookupInformation.getLatitude(), userLookupInformation.getLongitude());
        Map<String, Object> result = new HashMap<>();
        if (!deals.isEmpty()) {
            result.put("vendors", vendors);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            result.put(
                    "message",
                    "Oops! Looks like there aren't any participating vendors within 5kms. Check back in another area of the city.");
            return ResponseEntity.status(NO_LOCAL_DEAL_FOUND).body(result);
        }
    }
}
