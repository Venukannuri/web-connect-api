package com.optimus.model;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
public class Category extends AbstractUuidPersistable {

  @Column(name = "NAME")
  private String name;

  @OneToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "CATEGORY_STATUS_ID")
  @JsonIgnore
  private CategoryStatus categoryStatus;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CategoryStatus getCategoryStatus() {
    return categoryStatus;
  }

  public void setCategoryStatus(CategoryStatus categoryStatus) {
    this.categoryStatus = categoryStatus;
  }
}
