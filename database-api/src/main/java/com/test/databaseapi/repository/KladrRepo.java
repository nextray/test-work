package com.test.databaseapi.repository;

import com.test.databaseapi.models.AddressKLADR;
import org.springframework.data.repository.CrudRepository;

public interface KladrRepo extends CrudRepository<AddressKLADR, Long> {
    AddressKLADR findAddressKLADRByKladrId(Long kladrId);
}
