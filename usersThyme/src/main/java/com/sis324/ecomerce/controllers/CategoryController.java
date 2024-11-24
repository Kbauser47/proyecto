// CategoryController.java
package com.sis324.ecomerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sis324.ecomerce.models.Category;
import com.sis324.ecomerce.services.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Mostrar la lista de categorías
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }

    // Mostrar formulario para crear nueva categoría
    @GetMapping("/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "create-category"; // Vista para crear la categoría
    }

    // Crear nueva categoría
    @PostMapping
    public String createCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    // Mostrar formulario para editar una categoría
    @GetMapping("/edit/{id}")
    public String editCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id); // Buscar categoría por ID
        if (category != null) {
            model.addAttribute("category", category);
            return "edit-category"; // Vista de edición
        } else {
            return "redirect:/categories"; // Si la categoría no se encuentra, redirigir a la lista
        }
    }

    // Actualizar una categoría
    @PostMapping("/edit/{id}")
    public String updateCategory(@PathVariable Long id, @ModelAttribute Category category) {
        Category existingCategory = categoryService.getCategoryById(id);
        if (existingCategory != null) {
            existingCategory.setName(category.getName());
            categoryService.saveCategory(existingCategory); // Guardar categoría actualizada
        }
        return "redirect:/categories";
    }

    // Eliminar una categoría
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id); // Eliminar la categoría por ID
        return "redirect:/categories"; // Redirigir a la lista de categorías
    }
}


