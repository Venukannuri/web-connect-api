package com.optimus.dao;

import com.optimus.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface DealRepository extends JpaRepository<Deal, String>, JpaSpecificationExecutor {

  @Query(
      value =
          "select dl from Deal dl where upper(dl.vendor.id) = upper(:vendorId) and (dl.expiryDate IS NULL OR dl.expiryDate >= :asOf) and (dl.effectiveDate <= :asOf)")
  List<Deal> findByVendorId(
      @Param("vendorId") final String vendorId, @Param("asOf") ZonedDateTime now);

  @Query(
      "select dl from Deal dl "
          + "inner join dl.vendor v "
          + "where (dl.expiryDate IS NULL OR dl.expiryDate >= :asOf) "
          + "and (v.latitude between :latitudeMin and :latitudeMax) and (v.longitude between :longitudeMin and :longitudeMax)")
  List<Deal> findActiveVendorsByDeal(
      @Param("latitudeMin") final double latitudeMin,
      @Param("latitudeMax") final double latitudeMax,
      @Param("longitudeMin") final double longitudeMin,
      @Param("longitudeMax") final double longitudeMax,
      @Param("asOf") ZonedDateTime now);
}
