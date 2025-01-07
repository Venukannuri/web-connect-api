package com.optimus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_STATUS")
public class CategoryStatus extends AbstractUuidPersistable {
  public static final String CODE_CATEGORY_STATUS_ACTIVE = "ACTIVE";
  public static final String CODE_CATEGORY_STATUS_INACTIVE = "INACTIVE";

  @Column(name = "NAME")
  private String name;

  @Column(name = "CODE")
  private String code;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
