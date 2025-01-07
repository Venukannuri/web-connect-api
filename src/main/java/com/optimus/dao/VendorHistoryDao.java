package com.optimus.dao;

import com.optimus.model.VendorHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VendorHistoryDao
        extends JpaRepository<VendorHistory, String>, JpaSpecificationExecutor {}
