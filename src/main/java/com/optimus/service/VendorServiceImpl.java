package com.optimus.service;

import com.optimus.dao.VendorRepository;
import com.optimus.dto.CategoryDto;
import com.optimus.dto.VendorDto;
import com.optimus.model.Vendor;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    @Value("${distance.in.kms}")
    private double distanceInKms;

    @Value("${radius.of.earth.in.kms}")
    private double radiusOfEarthInKms;

    @Autowired
    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDto> findByUserLocation() {
        return vendorRepository.findAll().stream()
                .map(this::convertToVendorDto)
                .collect(Collectors.toList());
    }

    @Override
    public Vendor findById(String vendorId) {
        return vendorRepository
                .findById(vendorId)
                .orElseThrow(() -> new IllegalStateException("Invalid vendorId."));
    }

    @Override
    public List<VendorDto> getAllLocationByLocationAndUserValidation(double latitude, double longitude) {
        double latitudeMin = Precision.round(latitude - Math.toDegrees(distanceInKms / radiusOfEarthInKms), 6);
        double latitudeMax = Precision.round(latitude + Math.toDegrees(distanceInKms / radiusOfEarthInKms), 6);
        double longitudeMin = Precision.round(longitude - Math.toDegrees(distanceInKms / radiusOfEarthInKms / Math.cos(Math.toRadians(latitude))), 6);
        double longitudeMax = Precision.round(longitude + Math.toDegrees(distanceInKms / radiusOfEarthInKms / Math.cos(Math.toRadians(latitude))), 6);

        List<Vendor> vendors = vendorRepository.getAllLocationByLocations(latitudeMin, latitudeMax, longitudeMin, longitudeMax);

        return vendors.isEmpty() ? Collections.emptyList() : vendors.stream().map(this::convertToVendorDto).collect(Collectors.toList());
    }

    private VendorDto convertToVendorDto(Vendor vendor) {
        VendorDto vendorDto = new VendorDto();
        vendorDto.setId(vendor.getId());
        vendorDto.setName(vendor.getName());
        vendorDto.setAddress(vendor.getAddress());
        vendorDto.setEmail(vendor.getEmail());
        vendorDto.setPhoneNumber(vendor.getPhoneNumber());
        vendorDto.setWebsite(vendor.getWebsite());
        vendorDto.setDescription(vendor.getDescription());
        vendorDto.setStatus(vendor.getStatus());
        vendorDto.setLogo(vendor.getLogo());
        vendorDto.setStartDate(vendor.getStartDate());
        CategoryDto categoryDto = new CategoryDto();
        vendorDto.setCategoryDescription(categoryDto.setName(vendor.getCategory().getName()));
        return vendorDto;
    }
}
