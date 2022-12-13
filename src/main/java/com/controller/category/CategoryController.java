package com.controller.category;

import com.model.Category;
import com.model.Quiz;
import com.service.category.CategoryService;
import com.service.quiz.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/manager/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> FormCategory (){
        List<Category> categoryList= (List<Category>) categoryService.findAll();
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category>  createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.save(category),HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        return category.map(Category -> new ResponseEntity<>(Category, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<List> deleteCategory(@PathVariable Long id){
        List<Long> categoryList=categoryService.findQuizIdByCategoryId(id);
            if (categoryList.isEmpty()) {
                categoryService.remove(id);
                return new ResponseEntity<>(categoryList, HttpStatus.NO_CONTENT);
            }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        return categoryOptional.map(category -> new ResponseEntity<>(category,HttpStatus.OK)).orElseGet(() ->new ResponseEntity<>(HttpStatus.NOT_FOUND));
}
