package com.niit.backend.dao.repository;

import com.niit.backend.entities.address.District;
import com.niit.backend.entities.address.Province;
import com.niit.backend.entities.address.Ward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AddressRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Province> findAllProvince() {
        TypedQuery<Province> query = entityManager.createQuery("select p from Province p", Province.class);
        List<Province> provinces = query.getResultList();
        return provinces;
    }

    public Province findProvinceById(String matp) {
        TypedQuery<Province> query = entityManager.createQuery("select p from Province p where p.matp = :matp ", Province.class);
        query.setParameter("matp", matp);
        return query.getSingleResult();
    }

    public List<District> findDistrictByProvinceId(String matp) {
        TypedQuery<District> query = entityManager.createQuery(
                "select d from District d " +
                        "where d.matp = :matp ",
                District.class);
        query.setParameter("matp", matp);
        List<District> districts = query.getResultList();
        return districts;
    }

    public List<Ward> findWardByDistrictId(String maqh) {
        TypedQuery<Ward> query = entityManager.createQuery(
                "select w from Ward w " +
                        "where w.maqh = :maqh ",
                Ward.class);
        query.setParameter("maqh", maqh);
        List<Ward> wards = query.getResultList();
        return wards;
    }
}
