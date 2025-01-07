package com.optimus.service;

import com.optimus.dao.VendorHistoryDao;
import com.optimus.model.VendorHistory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VendorHistoryServiceImpl implements VendorHistoryService {

    @Resource VendorHistoryDao vendorHistoryDao;

    @Override
    public VendorHistory saveVendorHistory(VendorHistory vendorHistory) {
        return vendorHistoryDao.save(vendorHistory);
    }
}
