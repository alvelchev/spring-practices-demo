package com.springpageable.repository;

import com.springpageable.model.FutureDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FutureDeviceRepository extends JpaRepository<FutureDevice, Long> {

  @Query(
      "SELECT fd FROM FutureDevice fd"
          + " WHERE (lower(fd.serialNumber) LIKE lower(CONCAT('%',:searchParameter, '%'))"
          + " OR lower(fd.productId) LIKE lower(CONCAT('%',:searchParameter, '%')))")
  Page<FutureDevice> findFutureDevices(Pageable p, String searchParameter);
}
