package com.niit.backend.service;

import com.niit.backend.dao.repository.AddressRepository;
import com.niit.backend.entities.address.District;
import com.niit.backend.entities.address.Province;
import com.niit.backend.entities.address.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Province> findAllProvince() {
        List<Province> provinces = addressRepository.findAllProvince();
        return provinces;
    }

    public List<District> findAllDistrictByProvinceId(String matp) {
        return addressRepository.findDistrictByProvinceId(matp);
    }

    public List<Ward> findAllWardByDistrictId(String maqh) {
        return addressRepository.findWardByDistrictId(maqh);
    }

    public Province findProvinceById(String matp) {
        return addressRepository.findProvinceById(matp);
    }

}
