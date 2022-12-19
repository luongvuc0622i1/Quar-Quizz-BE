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
@RequestMapping("/user/examTest")
public class ExamTestController {

    @Autowired
    private IExamTestService examTestService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<ExamTest>> getAllExamTest (){
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

    @GetMapping("/findExamTestsById/{id}")
    public ResponseEntity<ExamTest> findExamTestsById(@PathVariable Long id) {
        Optional<ExamTest> examTestOptional = examTestService.findById(id);
        return examTestOptional.map(ExamTest -> new ResponseEntity<>(ExamTest, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<ExamTest> createExamTest(@RequestBody ExamTest examTest) {
        examTestService.save(examTest);
        return new ResponseEntity<>(examTest, HttpStatus.CREATED);
    }

    @PutMapping("/edit")
    public ResponseEntity<ExamTest> editExamQuiz(@PathVariable Long id, @RequestBody ExamTest examTest) {
        Optional<ExamTest> examTestOptional = examTestService.findById(id);
        if (!examTestOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        examTest.setId(id);
        examTestService.save(examTest);
        return new ResponseEntity<>(examTest, HttpStatus.OK);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ExamTest> deleteExamTest(@PathVariable Long id) {
        Optional<ExamTest> examTestOptional = examTestService.findById(id);
        if (!examTestOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        examTestService.remove(id);
        return new ResponseEntity<>(examTestOptional.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/setNumberAnswer/{id}")
    public ResponseEntity<ExamTest> setNumberAnswer(@PathVariable Long id) {
        Optional<ExamTest> examTestOptional = examTestService.findById(id);
        examTestService.changeNumberAnswer(examTestOptional);
        return examTestOptional.map(ExamTest -> new ResponseEntity<>(ExamTest, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
