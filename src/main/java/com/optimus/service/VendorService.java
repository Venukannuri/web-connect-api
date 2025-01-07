package com.optimus.service;

import com.optimus.dto.VendorDto;
import com.optimus.model.Vendor;

import java.util.List;

public interface VendorService {

    List<VendorDto> findByUserLocation();

    Vendor findById(final String vendorId);

    List<VendorDto> getAllLocationByLocationAndUserValidation(
            final double latitude, final double longitude);
}
