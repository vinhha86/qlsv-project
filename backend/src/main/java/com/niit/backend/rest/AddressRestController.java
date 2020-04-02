package com.niit.backend.rest;

import com.niit.backend.entities.address.District;
import com.niit.backend.entities.address.Province;
import com.niit.backend.entities.address.Ward;
import com.niit.backend.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressRestController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/province/all")
    public ResponseEntity<List<Province>> findAllProvince() {
        return ResponseEntity.ok(addressService.findAllProvince());
    }

    @GetMapping("/district/all")
    public ResponseEntity<List<District>> findAllDistrictByProvinceId(@RequestParam String matp) {
        return ResponseEntity.ok(addressService.findAllDistrictByProvinceId(matp));
    }

    @GetMapping("/ward/all")
    public ResponseEntity<List<Ward>> findAllWardByDistrictId(@RequestParam String maqh) {
        return ResponseEntity.ok(addressService.findAllWardByDistrictId(maqh));
    }

    @GetMapping("/province")
    public ResponseEntity<Province> findProvinceById(@RequestParam String matp) {
        return ResponseEntity.ok(addressService.findProvinceById(matp));
    }
}
