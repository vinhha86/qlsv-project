package com.niit.backend.dao.repository;

import com.niit.backend.dao.customRepository.CustomClassRepository;
import com.niit.backend.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long>, CustomClassRepository {
	Class findClassById(Long id);
}
