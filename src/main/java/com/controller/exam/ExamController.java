package com.controller.exam;

import git com.model.Category;
import com.model.ExamTest;
import com.model.jwt.AppUser;
import com.service.category.CategoryService;
import com.service.exam.IExamTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private IExamTestService examTestService;
@Autowired
private CategoryService categoryService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<?>> getAllExamTest (){
        List<ExamTest> examTestList= (List<ExamTest>) examTestService.findAll();
        if(examTestList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(examTestList,HttpStatus.OK);
    }

    @GetMapping("/findExamTestByUserId/{id}")
    public ResponseEntity<Iterable<ExamTest>> findExamTestByUserId(@PathVariable Long id) {
        Iterable<ExamTest> examTests = examTestService.findExamTestsByUserId(id);
        return new ResponseEntity<>(examTests, HttpStatus.OK);
    }


}
