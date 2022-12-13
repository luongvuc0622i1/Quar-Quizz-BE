package com.repository.category;

import com.model.Category;
import com.model.jwt.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends PagingAndSortingRepository<Category,Long> {
    @Query(value = "select quiz_id  from quiz_category where category_id = :categoryId", nativeQuery = true)
    List<Long> findQuizsIdByCategoryId(Long categoryId);


}
