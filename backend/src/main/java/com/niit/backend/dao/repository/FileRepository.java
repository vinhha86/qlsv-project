package com.niit.backend.dao.repository;

import com.niit.backend.entities.FileResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileResponse, Long> {
    FileResponse findFileResponseById(Long id);
}
