package com.test.databaseapi.controllers;

import com.test.databaseapi.models.AddressKLADR;
import com.test.databaseapi.repository.KladrRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class DBApiRestController {
    private final KladrRepo kladrRepo;

    public DBApiRestController(KladrRepo kladrRepo) {
        this.kladrRepo = kladrRepo;
    }

    @GetMapping("/get")
    public AddressKLADR serviceInstancesByApplicationName(
            @RequestParam(defaultValue = "-1") Long kladr ) {
        return kladrRepo.findAddressKLADRByKladrId(kladr);
    }

    @GetMapping("/add")
    public String add(
            @RequestParam Long kladr,
            @RequestParam String address) {
        AddressKLADR addressKLADR = new AddressKLADR(kladr,address);
        kladrRepo.save(addressKLADR);
        return "успешно";
    }
}
