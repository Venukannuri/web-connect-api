package com.optimus.dao;

import com.optimus.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, String>, JpaSpecificationExecutor {


  @Query(
      "select v from Vendor v where (v.latitude between :latitudeMin and :latitudeMax) and (v.longitude between :longitudeMin and :longitudeMax)")
  List<Vendor> getAllLocationByLocations(
      @Param("latitudeMin") final double latitudeMin,
      @Param("latitudeMax") final double latitudeMax,
      @Param("longitudeMin") final double longitudeMin,
      @Param("longitudeMax") final double longitudeMax);
}
