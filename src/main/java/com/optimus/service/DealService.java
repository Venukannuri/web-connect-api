package com.optimus.service;

import com.optimus.dto.DealDto;
import com.optimus.dto.VendorDto;
import com.optimus.model.Deal;

import java.util.List;

public interface DealService {

  List<DealDto> findByVendorId(final String vendorId);

  List<Deal> findActiveDeals(double latitude, double longitude);

  List<VendorDto> findActiveVendorsByDeal(double latitude, double longitude);
}
