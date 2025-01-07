package com.optimus.service;

import com.optimus.dao.DealRepository;
import com.optimus.dto.CategoryDto;
import com.optimus.dto.DealDto;
import com.optimus.dto.VendorDto;
import com.optimus.model.Deal;
import com.optimus.model.Vendor;

import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;

    @Value("${distance.in.kms}")
    private double distanceInKms;

    @Value("${radius.of.earth.in.kms}")
    private double radiusOfEarthInKms;

    @Autowired
    public DealServiceImpl(DealRepository dealRepository) {
        this.dealRepository = dealRepository;
    }

    @Override
    public List<DealDto> findByVendorId(String vendorId) {
        List<Deal> deals = dealRepository.findByVendorId(vendorId, ZonedDateTime.now());
        return deals.stream()
                .map(this::convertToDealDto)
                .collect(Collectors.toList());
    }

    private DealDto convertToDealDto(Deal deal) {
        DealDto dealDto = new DealDto();
        dealDto.setDealtitle(deal.getTitle());
        dealDto.setDealdescription(deal.getDescription());
        dealDto.setCouponCode(deal.getCouponCode());
        dealDto.setExpirydate(deal.getExpiryDate());
        return dealDto;
    }

    private double[] calculateBoundaries(double latitude, double longitude) {
        double latitudeMin = Precision.round(latitude - Math.toDegrees(distanceInKms / radiusOfEarthInKms), 6);
        double latitudeMax = Precision.round(latitude + Math.toDegrees(distanceInKms / radiusOfEarthInKms), 6);
        double longitudeMin = Precision.round(longitude - Math.toDegrees(distanceInKms / radiusOfEarthInKms / Math.cos(Math.toRadians(latitude))), 6);
        double longitudeMax = Precision.round(longitude + Math.toDegrees(distanceInKms / radiusOfEarthInKms / Math.cos(Math.toRadians(latitude))), 6);
        return new double[]{latitudeMin, latitudeMax, longitudeMin, longitudeMax};
    }

    @Override
    public List<Deal> findActiveDeals(double latitude, double longitude) {
        double[] boundaries = calculateBoundaries(latitude, longitude);
        return dealRepository.findActiveVendorsByDeal(boundaries[0], boundaries[1], boundaries[2], boundaries[3], ZonedDateTime.now());
    }

    @Override
    public List<VendorDto> findActiveVendorsByDeal(double latitude, double longitude) {
        List<Deal> deals = findActiveDeals(latitude, longitude);
        return deals.isEmpty() ? Collections.emptyList() : deals.stream().map(this::convertToVendorDto).collect(Collectors.toList());
    }

    private VendorDto convertToVendorDto(Deal deal) {
        VendorDto vendorDto = new VendorDto();
        Vendor vendor = deal.getVendor();
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