package com.kep.library.repository;

import com.kep.library.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CategoryRepository")
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
  List<CategoryEntity> findByNameLike(String name);
}
