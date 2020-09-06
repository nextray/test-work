package com.test.databaseapi.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class AddressKLADR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long kladrId;
    private String address;

   public AddressKLADR(Long kladrId, String address){
       this.kladrId = kladrId;
       this.address = address;
   }
}
