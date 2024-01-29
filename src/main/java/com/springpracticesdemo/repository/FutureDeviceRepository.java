package com.springpracticesdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.springpracticesdemo.model.FutureDevice;

public interface FutureDeviceRepository extends CrudRepository<FutureDevice, Long> {

    @Query("SELECT fd FROM FutureDevice fd"
            + " WHERE (lower(fd.serialNumber) LIKE lower(CONCAT('%',:searchParameter, '%'))"
            + " OR lower(fd.productId) LIKE lower(CONCAT('%',:searchParameter, '%')))")
    Page<FutureDevice> findFutureDevices(Pageable p, String searchParameter);
}
