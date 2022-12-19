package com.controller.exam;

import com.model.ExamTest;
import com.service.exam.IExamTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/examHistory")
public class ExamController {

    @Autowired
    private IExamTestService examTestService;

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
    @GetMapping("/{id}")
    public ResponseEntity<ExamTest> findById(@PathVariable Long id) {
        Optional<ExamTest> optional = examTestService.findById(id);
        if (!optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
