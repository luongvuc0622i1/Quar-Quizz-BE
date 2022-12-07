package com.repository;

import com.model.Category;
import com.model.Level;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILevelRepository extends CrudRepository<Level,Long> {
    Page<Level> findAll(Pageable pageable);
}
