package com.controller.exam;

import com.model.Category;
import com.model.ExamTest;
import com.service.exam.ExamTestService;
import com.service.exam.IExamTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private IExamTestService examTestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<ExamTest>> FormCategory (){
        List<ExamTest> categoryList= (List<ExamTest>) examTestService.findAll();
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

}
