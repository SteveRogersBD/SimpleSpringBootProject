package com.ecommerce.sb_ecom.service;

import com.ecommerce.sb_ecom.model.Category;
import com.ecommerce.sb_ecom.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{
    //private List<Category>categories=new ArrayList<>();
    private Long nextId = 1L;
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    public String deleteCategory(Long categoryId){
        //List<Category>categories=categoryRepository.findAll();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Resource not found!!!"));
//        Category category = categories.stream().
//                filter(c->c.getCategoryId().equals(categoryId))
//                .findFirst().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found"));

        categoryRepository.delete(category);
        return "Category with categoryId: "+ categoryId+" was deleted!!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        //List<Category>categories=categoryRepository.findAll();
        Category saveCategory = categoryRepository.findById(categoryId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Resource not found!!!"));
        category.setCategoryId(categoryId);
        saveCategory = categoryRepository.save(category);
        return saveCategory;
//        Optional<Category> optionalCategory = categories.stream().
//                filter(c->c.getCategoryId().equals(categoryId))
//                .findFirst();
//        if(optionalCategory.isPresent()){
//            Category existingCategory = optionalCategory.get();
//            existingCategory.setCategoryName(category.getCategoryName());
//            Category savedCategory = categoryRepository.save(existingCategory);
//            return existingCategory;
//        }
//        else{
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not Found");
//        }

    }

}
