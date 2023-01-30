package com.springpageable.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class FutureDevice extends Auditable implements Serializable {

  private static final long serialVersionUID = 2613900115897578605L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String serialNumber;

  private String productId;

  private Long customerId;

  private int healthStatus;
}
