package com.optimus.controller;

import com.optimus.dto.DealDto;
import com.optimus.model.AbstractUuidPersistable;
import com.optimus.model.UserLookupInformation;
import com.optimus.model.Vendor;
import com.optimus.model.VendorHistory;
import com.optimus.service.DealService;
import com.optimus.service.VendorHistoryService;
import com.optimus.service.VendorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/deals")
public class DealController extends AbstractUuidPersistable {

    private final transient DealService dealServices;
    private final transient VendorService vendorService;
    private final transient VendorHistoryService vendorHistoryService;

    public DealController(DealService dealServices, VendorService vendorService, VendorHistoryService vendorHistoryService) {
        this.dealServices = dealServices;
        this.vendorService = vendorService;
        this.vendorHistoryService = vendorHistoryService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DealController that = (DealController) o;
        return Objects.equals(dealServices, that.dealServices) &&
                Objects.equals(vendorService, that.vendorService) &&
                Objects.equals(vendorHistoryService, that.vendorHistoryService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealServices, vendorService, vendorHistoryService);
    }

    @PostMapping
    public List<DealDto> getAllDeals(@RequestBody UserLookupInformation user) {
        addVendorSearchHistory(user);
        return dealServices.findByVendorId(user.getVendorId());
    }

    private void addVendorSearchHistory(UserLookupInformation user) {
        VendorHistory history = new VendorHistory();
        Vendor vendor = vendorService.findById(user.getVendorId());
        history.setEmail(user.getUserEmail());
        history.setSearchDate(ZonedDateTime.now());
        history.setVendor(vendor);
        vendorHistoryService.saveVendorHistory(history);
    }
}