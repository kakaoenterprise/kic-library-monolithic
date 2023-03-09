package com.kep.library.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "category")
public class CategoryEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @JsonProperty
  private String id;

  @Column
  @JsonProperty
  private String name;
}
