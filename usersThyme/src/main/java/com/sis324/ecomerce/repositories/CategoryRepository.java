package com.sis324.ecomerce.repositories;

import com.sis324.ecomerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
