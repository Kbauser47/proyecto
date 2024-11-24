// CategoryService.java
package com.sis324.ecomerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sis324.ecomerce.models.Category;
import com.sis324.ecomerce.repositories.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Obtener todas las categorías
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Guardar una categoría
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Obtener una categoría por su ID
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElse(null); // Si no se encuentra la categoría, devolver null
    }

    // Eliminar una categoría por su ID
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
