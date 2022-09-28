package com.springpageable.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.springpageable.serialization.LocalDateTimeMsDeserializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@EqualsAndHashCode
public abstract class Auditable {

  @CreatedDate
  // JsonDeserialize is being used here only for using fixtures in the unit tests
  @JsonDeserialize(using = LocalDateTimeMsDeserializer.class)
  protected LocalDateTime createdAt;

  @CreatedBy
  @Column(updatable = false)
  protected String createdBy;

  @LastModifiedDate protected LocalDateTime updatedAt;

  @LastModifiedBy protected String updatedBy;
}
